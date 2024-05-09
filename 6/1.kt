private fun readln() = readLine()!!

fun main() {
  val s = readln()
  val LEN = 4;
  println(s.windowed(LEN, 1).map { it.toSet().size }.indexOf(LEN) + LEN)
}
