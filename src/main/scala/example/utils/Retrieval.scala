package example.utils

import scala.io.Source

object Retrieval {
  def getItems: List[Int] = {
    val filename = "src/main/resources/items.csv"
    var items = List[Int]()
    for (line <- Source.fromFile(filename).getLines) {
      val lineItems: List[Int] = line.split(",").map(_.trim).map(_.toInt).toList
      items = List.concat(items, lineItems)
    }
    println(s"${items.size} item types found.")
    items
  }

  def getBins: List[Int] = {
    val filename = "src/main/resources/bins.csv"
    var items = List[Int]()
    for (line <- Source.fromFile(filename).getLines) {
      val lineItems: List[Int] = line.split(",").map(_.toInt).toList
      items = List.concat(items, lineItems)
    }
    println(s"${items.size} item types found.")
    items
  }
}
