fun main() {
  val DIGITS = "=-012"
  var ans = generateSequence(::readLine).map {
    it.fold(0L, { res, c -> res * 5 + DIGITS.indexOf(c) - 2 })
  }.sum()
  val num = mutableListOf<Char>()
  while (ans > 0) {
    num.add(DIGITS[((ans + 2) % 5).toInt()])
    ans = (ans + 2) / 5
  }
  println(num.reversed().joinToString(""))
}