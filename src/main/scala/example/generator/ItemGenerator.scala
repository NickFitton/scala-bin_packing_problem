package example.generator

object ItemGenerator {
  def generateItems(count: Int): List[Float] = {
    val r = scala.util.Random
    for (_ <- (0 to count).toList) yield scala.math.round(r.nextFloat() * 100).toFloat / 10
  }
}
