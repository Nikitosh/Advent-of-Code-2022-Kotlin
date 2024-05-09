import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

private fun parseInts(str: String): List<Int> = Regex("-?[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun dfs(x: Int, y: Int, z: Int, used: MutableSet<List<Int>>, figure: Set<List<Int>>): Int {
  val MAX = 50
  if (min(x, min(y, z)) < -1 || max(x, max(y, z)) >= MAX)
    return 0
  used.add(listOf(x, y, z))
  return (-1..1).map { dx ->
    (-1..1).map { dy ->
      (-1..1).map { dz ->
        if (abs(dx) + abs(dy) + abs(dz) != 1)
          0
        else if (figure.contains(listOf(x + dx, y + dy, z + dz)))
          1
        else if (!used.contains(listOf(x + dx, y + dy, z + dz))) 
          dfs(x + dx, y + dy, z + dz, used, figure)
        else 0
      }.sum()
    }.sum()
  }.sum()
}

fun main() {
  val figure = generateSequence(::readLine).map { parseInts(it) }.toSet()
  var used = figure.toMutableSet()
  println(dfs(-1, -1, -1, used, figure))
}
