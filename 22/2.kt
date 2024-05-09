private fun parseInts(str: String): List<Int> = Regex("-?[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun Int.norm(mod: Int): Int {
  if (this < 0) {
    return this + mod
  }
  return if (this >= mod) this - mod else this
}

fun Int.add(b: Int, mod: Int): Int {
  return (this + b).norm(mod)
}

fun main() {
  var a = mutableListOf<String>()
  while (true) {
    val s = readln()
    if (s.isEmpty()) {
      break
    }
    a.add(s)
  }
  val s = readLine()!!
  val v = parseInts(s)
  var x = 0
  var y = 0
  var dir = 0
  var side = 0
  val dirs = s.filter { it == 'L' || it == 'R' }
  val L = 50
  val TURN = listOf(listOf(0, 1), listOf(1, 0), listOf(0, -1), listOf(-1, 0))
  val ST_X = listOf(0, L, 2 * L, 2 * L, 3 * L, 0)
  val ST_Y = listOf(L, L, L, 0, 0, 2 * L)
  val GO = listOf(
    listOf(Pair(5, 0), Pair(1, 0), Pair(3, 2), Pair(4, 1)),
    listOf(Pair(5, 3), Pair(2, 0), Pair(3, 3), Pair(0, 0)),
    listOf(Pair(5, 2), Pair(4, 1), Pair(3, 0), Pair(1, 0)),
    listOf(Pair(2, 0), Pair(4, 0), Pair(0, 2), Pair(1, 1)),
    listOf(Pair(2, 3), Pair(5, 0), Pair(0, 3), Pair(3, 0)),
    listOf(Pair(2, 2), Pair(1, 1), Pair(0, 0), Pair(4, 0))
  )
  for (i in 0..dirs.length) {
    for (j in 0..v[i] - 1) {
      var nX = x + TURN[dir][0]
      var nY = y + TURN[dir][1]
      var nDir = dir
      var nSide = side
      if (nX < 0 || nY < 0 || nX >= L || nY >= L) {
        nX = nX.norm(L)
        nY = nY.norm(L)
        repeat(GO[side][dir].second) {
          nX = nY.also { nY = L - 1 - nX }
        }
        nSide = GO[side][dir].first
        nDir = dir.add(GO[side][dir].second, 4)
      }
      if (a[ST_X[nSide] + nX][ST_Y[nSide] + nY] == '#') {
        break
      }
      x = nX
      y = nY
      dir = nDir
      side = nSide
    }
    if (i != v.size - 1) {
      dir = dir.add(if (dirs[i] == 'L') -1 else 1, 4)
    }
  }
  println((ST_X[side] + x + 1) * 1000 + (ST_Y[side] + y + 1) * 4 + dir)
}
