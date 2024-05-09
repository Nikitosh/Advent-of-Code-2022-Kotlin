fun main() {
  println(generateSequence(::readLine).map {
    val a = it.get(0) - 'A'
    val r = it.get(2) - 'X'
    (r + a + 2) % 3 + 3 * r + 1
  }.sum())
}
