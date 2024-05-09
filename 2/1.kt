fun main() {
  println(generateSequence(::readLine).map {
    val a = it.get(0) - 'A'
    val b = it.get(2) - 'X'
    (b - a + 4) % 3 * 3 + b + 1
  }.sum())
}
