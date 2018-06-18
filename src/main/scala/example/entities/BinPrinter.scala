package example.entities

import example.utils.StringUtils

object BinPrinter {
  def printBin(bin: Bin): Unit = {
    println("+----------+")
    for (elem <- bin.getContents) {
      val line = StringUtils.center(elem.toString, 10)
      println(f"|$line|")
    }
    println("+----------+")
  }

  def printBins(bins: List[Bin]): Unit = {
    val itemWidth = 10
    val highestBinSize = bins.toStream.map(_.getContents.size).max
    printBorder(bins, itemWidth)

    for (i <- 0 to highestBinSize) {
      print("|")
      for (bin <- bins) {
        if (bin.getContents.size > i) {
          print(s"${StringUtils.center(bin.getContents(i).toString, itemWidth, ' ')}|")
        } else {
          print(s"${StringUtils.center(" ", itemWidth, ' ')}|")
        }
      }
      println()
    }
    printBorder(bins, itemWidth)
  }

  def printBorder(elements: List[Any], width: Int): Unit = {
    print("+")
    for (_ <- elements) {
      for (_ <- 1 to width) {
        print("-")
      }
      print("+")
    }
    println()
  }
}
