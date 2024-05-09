private fun readln() = readLine()!!
private fun parseInts(str: String): List<Int> = Regex("[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun main() {
  val path = mutableListOf<String>()
  val m = mutableMapOf<List<String>, Int>()
  generateSequence(::readLine).forEach { 
    if (it.startsWith("$ cd")) {
      val s = it.split(' ').last()
      if (s == "..") {
        path.removeLast()
      } else {
        path.add(s)
      }
    }
    val ints = parseInts(it)
    if (!ints.isEmpty()) {
      for (i in 0..path.size - 1) {
        m.merge(path.slice(0..i), ints[0], Int::plus)
      }
    }
  }
  val MAX_SIZE = 100000
  println(m.values.filter { it < MAX_SIZE }.sum())
}
