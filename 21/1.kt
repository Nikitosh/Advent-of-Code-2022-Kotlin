private fun parseInts(str: String): List<Int> = Regex("-?[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun get(s: String, deps: Map<String, Triple<Char, String, String>>, go: MutableMap<String, Long>): Long {
  val value = go.get(s)
  if (value != null) {
    return value
  }
  val op1 = get(deps[s]!!.second, deps, go)
  val op2 = get(deps[s]!!.third, deps, go)
  val res = when(deps[s]!!.first) {
    '+' -> op1 + op2
    '-' -> op1 - op2
    '*' -> op1 * op2
    '/' -> op1 / op2
    else -> 0
  }
  go[s] = res
  return res
}

fun main() {
  val input = generateSequence(::readLine).toList()
  val deps = input.map {
    val v = parseInts(it)
    if (!v.isEmpty()) "" to Triple(' ', "", "") else it.substring(0, 4) to Triple(it[11], it.substring(6, 10), it.substring(13, 17))
  }.toMap()
  val go = input.map {
    val v = parseInts(it)
    if (v.isEmpty()) "" to 0L else it.substring(0, 4) to v[0].toLong()
  }.toMap().toMutableMap()
  println(get("root", deps, go))
}
