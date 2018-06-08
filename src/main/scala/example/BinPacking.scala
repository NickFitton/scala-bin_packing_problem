package example

import example.fits.{FirstFit, NextFit}
import example.utils.Retrieval.{getBins, getItems}

import scala.annotation.tailrec

object BinPacking extends App {

  val items: List[Int] = getItems
  val bin: Int = getBins.head

  val nextFitBinCount = NextFit.calculate(items, bin)
  println(s"Number of bins required in Next Fit: $nextFitBinCount")

  val scalaFirstFitBinCount = FirstFit.calculate(items, bin)
  println(s"Number of bins required in First Fit: $scalaFirstFitBinCount")


  @tailrec
  def sum(list: List[Int], accumulator: Int): Int = list match {
    case Nil => accumulator
    case x :: xs => sum(xs, x + accumulator)
  }
}

