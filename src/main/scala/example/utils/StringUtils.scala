package example.utils

object StringUtils {

  def center(s: String, size: Int): String = {
    center(s, size, ' ')
  }

  def center(s: String, size: Int, pad: Char): String = {
    if (s == null || size <= s.length()) {
      s
    }
    val sb: StringBuilder = new StringBuilder(size)
    for (_ <- 0 to (size - s.length()) / 2) {
      sb.append(pad)
    }
    sb.append(s)
    while (sb.length() < size) {
      sb.append(pad)
    }
    sb.toString()
  }
}
