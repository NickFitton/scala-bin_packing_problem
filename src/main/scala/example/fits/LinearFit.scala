package example.fits

import example.entities.Bin

import scala.annotation.tailrec

class LinearFit(weights: List[Float], binSize: Float) {
  private val orderedWeights = weights.sortWith(_ > _)

  def bestFitObjective: List[Bin] = {
    bestFitObjectiveRecursive(orderedWeights, List[Bin]())
  }

  def bestFit(weights: List[Float], _bins: List[Float]): Int = {
    var bins = _bins
    var minimumSize = binSize + 1
    val bi = bestFitRecursion(bins, weights.head, binSize, 0, minimumSize, 0)

    if (bi < bins.size && bins(bi) >= weights.head && bins(bi) - weights.head < minimumSize) {
      minimumSize = bins(bi) - weights.head
    }

    if (minimumSize == binSize + 1) {
      bins = bins :+ (binSize - weights.head)
    } else {
      bins = bins.patch(bi, Seq(bins(bi) - weights.head), 1)
    }
    if (weights.tail == Nil) {
      bins.size
    } else {
      bestFit(weights.tail, bins)
    }
  }

  @tailrec
  private final def bestFitRecursion(bins: List[Float], weight: Float, binSize: Float, bi: Int, minimumSize: Float, counter: Int): Int = bins match {
    case Nil => bi
    case head :: tail if head >= weight && head - weight < minimumSize => bestFitRecursion(tail, weight, binSize, counter, head - weight, counter + 1)
    case _ :: tail => bestFitRecursion(tail, weight, binSize, bi, minimumSize, counter + 1)
  }

  @tailrec
  private def bestFitObjectiveRecursive(weights: List[Float], _bins: List[Bin]): List[Bin] = {
    var minimumSize = binSize + 1
    val bi = bestFitRecursionObjective(_bins, weights.head, binSize, 0, minimumSize, 0)

    if (bi < _bins.size && (binSize - _bins(bi).getWeight) >= weights.head && (binSize - _bins(bi).getWeight) - weights.head < minimumSize) {
      minimumSize = _bins(bi).getWeight - weights.head
    }

    if (weights.tail.isEmpty) {
      getBestBin(minimumSize, _bins, bi, weights.head)
    } else {
      bestFitObjectiveRecursive(weights.tail, getBestBin(minimumSize, _bins, bi, weights.head))
    }
  }

  def getBestBin(minimumSize: Float, bins: List[Bin], pointer: Int, newItem: Float): List[Bin] = {
    if (minimumSize == binSize + 1) {
      val newBin = new Bin(binSize)
      newBin.addItem(newItem)
      bins :+ newBin
    } else {
      val oldBin = bins(pointer)
      oldBin.addItem(newItem)
      bins.patch(pointer, Seq(oldBin), 1)
    }
  }

  @tailrec
  private final def bestFitRecursionObjective(bins: List[Bin], weight: Float, binSize: Float, bi: Int, minimumSize: Float, counter: Int): Int = bins match {
    case Nil => bi
    case head :: tail if head.getWeight >= weight && head.getWeight - weight < minimumSize => bestFitRecursionObjective(tail, weight, binSize, counter, head.getWeight - weight, counter + 1)
    case _ :: tail => bestFitRecursionObjective(tail, weight, binSize, bi, minimumSize, counter + 1)
  }
}
