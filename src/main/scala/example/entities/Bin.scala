package example.entities

case class Bin(size: Int, quantity: Int) {
  def getCapacity: Int = size * quantity
}
