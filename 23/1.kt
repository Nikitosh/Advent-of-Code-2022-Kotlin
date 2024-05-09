fun good(x: Int, y: Int, n: Int, m: Int): Boolean {
  return x >= 0 && y >= 0 && x < n && y < m
}

fun main() {
  var b = generateSequence(::readLine).toList()
  val ROUNDS = 10
  val n = b.size + 2 * ROUNDS
  val m = b[0].length + 2 * ROUNDS
  val empty = ".".repeat(m)
  b = List(ROUNDS) { empty } + b.map { ".".repeat(ROUNDS) + it + ".".repeat(ROUNDS) } + List(ROUNDS) { empty }
  val TURN = listOf(listOf(-1, 0), listOf(1, 0), listOf(0, -1), listOf(0, 1))
  var a = b.map { it.toMutableList() }
  repeat(ROUNDS) { round ->
    val next = mutableMapOf<Pair<Int, Int>, MutableList<Pair<Int, Int>>>()
    for (i in 0..n - 1) {
      for (j in 0..m - 1) {
        if (a[i][j] == '.') {
          continue
        }
        val cnt = (-1..1).map { x ->
          (-1..1).map { y ->
            if ((x != 0 || y != 0) && good(i + x, j + y, n, m) && a[i + x][j + y] == '#') 1 else 0
          }.sum()
        }.sum()
        if (cnt == 0) {
          next.getOrPut(Pair(i, j)) { mutableListOf() }.add(Pair(i, j))
          continue
        }
        var was = false
        for (g in 0..3) {
          val dir = (round + g) % 4
          val neighbours = (-1..1).map { x -> 
            val nx = i + (if (TURN[dir][0] == 0) x else TURN[dir][0])
            val ny = j + (if (TURN[dir][1] == 0) x else TURN[dir][1])
            if (good(nx, ny, n, m) && a[nx][ny] == '#') 1 else 0						
          }.sum()
          val nx = i + TURN[dir][0]
          val ny = j + TURN[dir][1]
          if (good(nx, ny, n, m) && neighbours == 0) {
            was = true
            next.getOrPut(Pair(nx, ny)) { mutableListOf() }.add(Pair(i, j))
            break
          }
        }
        if (!was) {
          next.getOrPut(Pair(i, j)) { mutableListOf() }.add(Pair(i, j))	
        }
      }
    }	
    a = List(n) { MutableList(m) { '.' } }
    next.forEach {
      if (it.value.size > 1) {
        for (np in it.value) {
          a[np.first][np.second] = '#'
        }
      } else {
        a[it.key.first][it.key.second] = '#'
      }
    }
  }
  val sum = a.map { it.count { it == '#' } }.sum()
  val minX = a.map { it.count { it == '#' } }.indexOfFirst { it > 0 }
  val maxX = a.map { it.count { it == '#' } }.indexOfLast { it > 0 }
  val minY = a.map { if (it.indexOf('#') == -1) m else it.indexOf('#') }.min()
  val maxY = a.map { it.lastIndexOf('#') }.max()
  println((maxX - minX + 1) * (maxY - minY + 1) - sum)
}
