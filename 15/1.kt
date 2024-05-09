import kotlin.math.abs

private fun parseInts(str: String): List<Int> = Regex("-?[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun main() {
  val v = generateSequence(::readLine).map { parseInts(it) }.toList()
  val MAX = 2000000
  val y = MAX
  println(IntRange(-5 * MAX, 5 * MAX).filter {
    x -> v.any {
      abs(x - it[0]) + abs(y - it[1]) <= abs(it[2] - it[0]) + abs(it[3] - it[1])
    }
  }.toSet().subtract(v.filter { it[3] == y }.map { it[2] }.toSet()).size)
}
