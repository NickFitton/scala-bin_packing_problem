package example.fits

import scala.annotation.tailrec

object BestFit {

  def calculate(weights: List[Int], binSize: Int): Int = {
    var bins = List[Int]()
    for (weight <- weights) {
      var minimumSize = binSize + 1
      val bi = tailRecBestFit(bins, weight, binSize, 0, minimumSize, 0)

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

  //  Init (minimumSize = binSize + 1)
  @tailrec
  def tailRecBestFit(bins: List[Int], weight: Int, binSize: Int, bi: Int, minimumSize: Int, counter: Int): Int = {
    bins match {
      case Nil => bi
      case head :: tail if head >= weight && head - weight < minimumSize => tailRecBestFit(tail, weight, binSize, counter, head - weight, counter + 1)
      case _ :: tail => tailRecBestFit(tail, weight, binSize, bi, minimumSize, counter + 1)
    }
  }
}
