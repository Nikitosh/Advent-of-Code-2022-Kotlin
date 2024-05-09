private fun readln() = readLine()!!
private fun parseInts(str: String): List<Int> = Regex("[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun main() {
  val STACKS = 9;
  var v = MutableList(STACKS) { mutableListOf<Char>() }
  while (true) {
    val s = readln()
    if (s.isEmpty()) {
      break;
    }
    for (i in 0..s.length - 1) {
      if (s.get(i).isLetter()) {
        v[i / 4].add(s[i]);
      }
    }
  }
  v.forEach(MutableList<Char>::reverse)
  generateSequence(::readLine).map(::parseInts).forEach {
    v[it[2] - 1].addAll(v[it[1] - 1].takeLast(it[0]))
    v[it[1] - 1] = v[it[1] - 1].dropLast(it[0]).toMutableList()
  }
  v.map(List<Char>::last).forEach(::print)
}
