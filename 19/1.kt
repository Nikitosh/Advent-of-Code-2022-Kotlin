import kotlin.math.max

private fun parseInts(str: String): List<Int> = Regex("-?[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun go(k: Int, n: Int, cnts: List<Int>, robots: List<Int>, costs: List<List<Int>>, was: MutableSet<Triple<Int, List<Int>, List<Int>>>): Int {
  if (robots[0] > max(max(costs[0][0], costs[1][0]), max(costs[2][0], costs[3][0])) || was.contains(Triple(k, cnts, robots))) {
    return 0
  }
  if (k == n) {
    return cnts[3]
  }
  var res = 0
  for (i in 3 downTo -1) {
    var newCnts = cnts
    val newRobots = robots.toMutableList()
    if (i != -1) {
      newCnts = newCnts.zip(costs[i] + List(4 - costs[i].size) { 0 }) { x, y -> x - y }
      if (newCnts.any { it < 0 }) {
        continue;
      } 
      newRobots[i]++
    }
    newCnts = newCnts.zip(robots) { x, y -> x + y }
    res = max(res, go(k + 1, n, newCnts, newRobots, costs, was))	
    if (i >= 2) {
      break
    }
  }
  was.add(Triple(k, cnts, robots))
  return res
}

fun main() {
  println(generateSequence(::readLine).map { parseInts(it) }.map {
    val costs = listOf(listOf(it[1]), listOf(it[2]), listOf(it[3], it[4]), listOf(it[5], 0, it[6]))
    val was = mutableSetOf<Triple<Int, List<Int>, List<Int>>>()
    it[0] * go(0, 24, listOf(0, 0, 0, 0), listOf(1, 0, 0, 0), costs, was)	
  }.sum())
}
