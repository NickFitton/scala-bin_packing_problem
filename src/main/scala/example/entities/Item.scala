package example.entities

case class Item(size: Int, quantity: Int) {
  def getVolume: Int = size * quantity
}
