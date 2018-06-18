package example.entities

import scala.annotation.tailrec

class Bin(size: Float) {
  var contents: List[Float] = List[Float]()

  def addItem(item: Float): Unit = {
    if (sum(contents, 0) + item > size) {
      throw new IllegalArgumentException("Item would overflow the bin")
    }
    contents = contents :+ item
  }

  def getContents: List[Float] = contents

  def getWeight: Float = {
    sum(contents, 0)
  }

  @tailrec
  private final def sum(list: List[Float], accumulator: Float): Float = list match {
    case Nil => accumulator
    case x :: xs => sum(xs, x + accumulator)
  }
}
