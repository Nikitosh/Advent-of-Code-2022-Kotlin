import kotlin.math.abs

private fun parseInts(str: String): List<Int> = Regex("-?[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun main() {
  val m = generateSequence(::readLine).map { parseInts(it) }.toSet()
  println(m.map {
    (-1..1).map { dx ->
      (-1..1).map { dy ->
        (-1..1).map { dz ->
          if (abs(dx) + abs(dy) + abs(dz) == 1 && !m.contains(listOf(it[0] + dx, it[1] + dy, it[2] + dz))) 1 else 0
        }.sum()
      }.sum()
    }.sum()
  }.sum())
}
