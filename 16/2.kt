import kotlin.math.pow
import kotlin.math.max

private fun parseInts(str: String): List<Int> = Regex("-?[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun main() {
  val input = generateSequence(::readLine).map { Triple(it.substring(6, 8), parseInts(it)[0], it.split(",").map { it.drop(it.length - 2) }) }.toList()
  val n = input.size
  val nonZeroFlows = input.filter { it.second != 0 }.map { it.second }
  var f = 0
  val flowToInd = input.map { if (it.second != 0) f++ else -1 }
  val num = input.mapIndexed { index, value -> value.first to index }.toMap()
  val g = input.map { it.third.map { num.get(it)!! } }
  val dlt = List<Int>(1 shl f) {
    ind -> (0..f - 1).map { if ((ind shr it) and 1 != 0) nonZeroFlows[it] else 0 }.sum()
  }
  val INF = 1000000000
  var dp = List(n) { List(n) { MutableList(1 shl f) { -INF } } }
  dp[num.get("AA")!!][num.get("AA")!!][0] = 0
  val ROUNDS = 26
  repeat(ROUNDS) {
    val newDp = List(n) { List(n) { MutableList(1 shl f) { -INF } } }
    for (v in 0..n - 1) {
      for (u in 0..v) {
        for (mask in 0..(1 shl f) - 1) {
          if (dp[v][u][mask] < 0) {
            continue
          }
          for (x in 0..g[v].size) {
            for (y in 0..g[u].size) {
              var nV = v
              var nU = u
              var nMask = mask
              if (x < g[v].size) {
                nV = g[v][x]
              } else {
                val ind = flowToInd[v]
                if (ind != -1 && ((mask shr ind) and 1) == 0) {
                  nMask = nMask or (1 shl ind)
                }
              }
              if (y < g[u].size) {
                nU = g[u][y]
              } else {
                val ind = flowToInd[u]
                if (ind != -1 && ((mask shr ind) and 1) == 0) {
                  nMask = nMask or (1 shl ind)
                }
              }
              if (nV < nU) {
                nV = nU.also { nU = nV }
              }
              newDp[nV][nU][nMask] = max(newDp[nV][nU][nMask], dp[v][u][mask] + dlt[mask])
            }
          }
        }
      }
    }
    dp = newDp
  }
  println(dp.map { it.map { it.max() }.max() }.max())
}
