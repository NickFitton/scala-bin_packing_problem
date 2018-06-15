package example.fits

object BestFit {
  def calculate(weights: List[Int], binSize: Int): Int = {
    var bins = List[Int]()
    for (weight <- weights) {
      var i = 0
      var minimumSize = binSize + 1
      var bi = 0

      for (i <- bins.indices) {
        if (bins(i) >= weight && bins(i) - weight < minimumSize) {
          bi = i
          minimumSize = bins(i) - weight
        }
      }

      if (minimumSize == binSize + 1) {
        bins = bins :+ (binSize - weight)
      } else {
        bins = bins.patch(bi, Seq(bins(bi) - weight), 1)
      }
    }
    bins.size
  }
}
