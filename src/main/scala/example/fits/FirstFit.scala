package example.fits

import scala.annotation.tailrec

object FirstFit {
  def calculate(weights: List[Int], binSize: Int): Int = {
    var binRemainders = List[Int]()
    for (weight <- weights) {
      binRemainders = findFit(List(), binRemainders, binSize, weight)
    }
    binRemainders.size

    @tailrec
    def findFit(oldBins: List[Int], binRemainders: List[Int], binSize: Int, item: Int): List[Int] = binRemainders match {
      case Nil =>
        println(oldBins)
        oldBins :+ (binSize - item)
      case head :: tail if head >= item => oldBins ++ ((head - item) :: tail)
      case head :: tail => findFit(oldBins :+ head, tail, binSize, item)
    }
  }
}