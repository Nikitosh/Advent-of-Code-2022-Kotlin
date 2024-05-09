fun Boolean.toInt() = if (this) 1 else 0

fun main() {
  val a = generateSequence(::readLine).toList()
  val n = a.size
  val m = a[0].length
  var ans = 0
  val TURN = listOf(listOf(-1, 0), listOf(1, 0), listOf(0, -1), listOf(0, 1))
  for (i in 0..n - 1) {
    for (j in 0..m - 1) {
      var ok = false
      for (g in 0..3) {
        var x = i + TURN[g][0]
        var y = j + TURN[g][1]
        var was = false
        while (x >= 0 && y >= 0 && x < n && y < m) {
          if (a[x][y] >= a[i][j]) {
            was = true
            break
          }
          x += TURN[g][0]
          y += TURN[g][1]
        }
        ok = ok or !was
      }
      ans += ok.toInt()
    }
  }
  println(ans)
}
