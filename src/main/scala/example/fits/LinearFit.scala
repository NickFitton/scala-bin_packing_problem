package example.fits

import example.entities.Bin

import scala.annotation.tailrec

class LinearFit(weights: List[Float], binSize: Float) {
  private val orderedWeights = weights.sortWith(_ > _)

  def bestFitObjective: List[Bin] = {
    var bins = List[Bin]()
    for (weight <- orderedWeights) {
      var minimumSize = binSize + 1
      val bi = bestFitRecursionObjective(bins, weight, binSize, 0, minimumSize, 0)

      if (bi < bins.size && (binSize - bins(bi).getWeight) >= weight && (binSize - bins(bi).getWeight) - weight < minimumSize) {
        minimumSize = bins(bi).getWeight - weight
      } else {
        minimumSize = binSize + 1
      }

      if (minimumSize == binSize + 1) {
        val newBin = new Bin(binSize)
        newBin.addItem(weight)
        bins = bins :+ newBin
      } else {
        val oldBin = bins(bi)
        oldBin.addItem(weight)
        bins = bins.patch(bi, Seq(oldBin), 1)
      }
    }
    bins
  }

  def bestFit: Int = {
    var bins = List[Float]()
    for (weight <- orderedWeights) {
      var minimumSize = binSize + 1
      val bi = bestFitRecursion(bins, weight, binSize, 0, minimumSize, 0)

      if (bi < bins.size && bins(bi) >= weight && bins(bi) - weight < minimumSize) {
        minimumSize = bins(bi) - weight
      } else {
        minimumSize = binSize + 1
      }

      if (minimumSize == binSize + 1) {
        bins = bins :+ (binSize - weight)
      } else {
        bins = bins.patch(bi, Seq(bins(bi) - weight), 1)
      }
    }
    bins.size
  }

  @tailrec
  private final def bestFitRecursion(bins: List[Float], weight: Float, binSize: Float, bi: Int, minimumSize: Float, counter: Int): Int = {
    bins match {
      case Nil => bi
      case head :: tail if head >= weight && head - weight < minimumSize => bestFitRecursion(tail, weight, binSize, counter, head - weight, counter + 1)
      case _ :: tail => bestFitRecursion(tail, weight, binSize, bi, minimumSize, counter + 1)
    }
  }

  @tailrec
  private final def bestFitRecursionObjective(bins: List[Bin], weight: Float, binSize: Float, bi: Int, minimumSize: Float, counter: Int): Int = {
    bins match {
      case Nil => bi
      case head :: tail if head.getWeight >= weight && head.getWeight - weight < minimumSize => bestFitRecursionObjective(tail, weight, binSize, counter, head.getWeight - weight, counter + 1)
      case _ :: tail => bestFitRecursionObjective(tail, weight, binSize, bi, minimumSize, counter + 1)
    }
  }
}
