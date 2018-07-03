package example.utils

import scala.io.Source

object Retrieval {
  def getItems: List[Float] = {
    val filename = "src/main/resources/items.csv"
    var items = List[Float]()
    for (line <- Source.fromFile(filename).getLines) {
      val lineItems: List[Float] = line.split(",").map(_.trim).map(_.toFloat).toList
      items = List.concat(items, lineItems)
    }
    println(s"${items.size} item types found.")
    items
  }

  def getBins: List[Float] = {
    val filename = "src/main/resources/bins.csv"
    var bins = List[Float]()
    for (line <- Source.fromFile(filename).getLines) {
      val lineItems: List[Float] = line.split(",").map(_.toFloat).toList
      bins = List.concat(bins, lineItems)
    }
    println(s"${bins.size} bin types found.")
    bins
  }
}
