fun Int.norm(mod: Int): Int {
  if (this < 0) {
    return this + mod
  }
  return if (this >= mod) this - mod else this
}

fun main() {
  val a = generateSequence(::readLine).toList()
  val n = a.size
  val m = a[0].length
  var poss = a.mapIndexed { idx, lst -> lst.toList().map { "^>v<".indexOf(it) }.withIndex().filter { it.value != -1 }.map { Triple(idx, it.index, it.value) } }.flatten().toSet()
  var ts = setOf(Pair(0, 1))
  var timer = 0
  val TURN = listOf(listOf(-1, 0), listOf(0, 1), listOf(1, 0), listOf(0, -1))
  val dests = listOf(Pair(n - 1, m - 2), Pair(0, 1), Pair(n - 1, m - 2))
  var destIt = 0
  while (destIt < 3) {
    timer++
    val nextPoss = mutableSetOf<Triple<Int, Int, Int>>()
    val nextTs = mutableSetOf<Pair<Int, Int>>()
    for (pos in poss) {
      val nx = (pos.first + TURN[pos.third][0] - 1).norm(n - 2) + 1
      val ny = (pos.second + TURN[pos.third][1] - 1).norm(m - 2) + 1
      nextPoss.add(Triple(nx, ny, pos.third))		
    }
    for (pos in ts) {
      for (g in 0..4) {
        val nx = pos.first + if (g < 4) TURN[g][0] else 0
        val ny = pos.second + if (g < 4) TURN[g][1] else 0
        if (nx >= 0 && ny >= 0 && nx < n && ny < m && a[nx][ny] != '#') {
          val nextPossIt = nextPoss.find { it.first == nx && it.second == ny }
          if (nextPossIt == null) {
            nextTs.add(Pair(nx, ny))
          }
        }
      }
    }
    poss = nextPoss.toSet()
    ts = nextTs.toSet()
    if (ts.contains(dests[destIt])) {
      ts = setOf(dests[destIt++])
    }
  }
  println(timer)
}
