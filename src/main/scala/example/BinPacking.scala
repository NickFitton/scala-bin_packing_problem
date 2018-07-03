package example

import example.entities.BinPrinter
import example.fits.LinearFit
import example.generator.ItemGenerator
import example.utils.Retrieval.getBins

import scala.annotation.tailrec

object BinPacking extends App {

  //  val items: List[Float] = getItems
  val items: List[Float] = ItemGenerator.generateItems(1000)
  val bin: Float = getBins.head

  val perfectBinPacking = math.ceil(sum(items, 0) / bin).toInt
  println(s"Perfect number of bins: $perfectBinPacking")

  val bestFitBins = new LinearFit(items, bin).bestFitObjective
  println(s"Made it in ${bestFitBins.size} bins")
//  BinPrinter.printBins(bestFitBins)

  @tailrec
  def sum(list: List[Float], accumulator: Float): Float = list match {
    case Nil => accumulator
    case x :: xs => sum(xs, x + accumulator)
  }
}
