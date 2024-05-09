private fun parseInts(str: String): List<Int> = Regex("-?[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun main() {
  val LEN = 40;
  var x = 1
  val v = mutableListOf<Int>()
  generateSequence(::readLine).forEach { 
    v.add(x)
    if (it[0] != 'n') {
      v.add(x)
      x += parseInts(it)[0]
    }
  }
  println(v.mapIndexed { index, value -> if (index % LEN == LEN / 2 - 1) (index + 1) * value else 0 }.sum())
}
