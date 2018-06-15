package example.fits

import scala.annotation.tailrec

object NextFit {
  def calculate(weights: List[Int], binSize: Int): Int = {
    @tailrec
    def nextFit(weights: List[Int], binSize: Int, remainingBinSpace: Int, result: Int): Int = weights match {
      case Nil => result
      case head :: tail if head > remainingBinSpace =>
        nextFit(tail, binSize, binSize - head, result + 1)
      case head :: tail =>
        nextFit(tail, binSize, remainingBinSpace - head, result)
    }

    nextFit(weights, binSize, binSize, 0)
  }
}
