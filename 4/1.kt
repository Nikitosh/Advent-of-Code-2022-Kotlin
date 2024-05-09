private fun parseInts(str: String) = Regex("[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun main() {
  println(generateSequence(::readLine).map(::parseInts).map { 
    if ((it[0] <= it[2] && it[1] >= it[3]) || (it[2] <= it[0] && it[3] >= it[1])) 1 else 0
  }.sum())
}
