import java.util.Queue
import java.util.LinkedList

fun main() {
  val TURN = listOf(listOf(-1, 0), listOf(1, 0), listOf(0, -1), listOf(0, 1))
  val s = generateSequence(::readLine).map { it.toMutableList() }.toMutableList()
  val n = s.size
  val m = s[0].size
  val q: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
  val d = MutableList(n) { MutableList(m) { n * m } }
  var fX = 0
  var fY = 0
  for (i in 0..n - 1) {
    for (j in 0..m - 1) {
      if (s[i][j] == 'E') {
        s[i][j] = 'z'
        fX = i
        fY = j
      }
      if (s[i][j] == 'S' || s[i][j] == 'a') {
        s[i][j] = 'a'
        d[i][j] = 0
        q.add(Pair(i, j))				
      }
    }
  }
  while (!q.isEmpty()) {
    val (x, y) = q.remove()
    TURN.forEach {
      val nX = x + it[0]
      val nY = y + it[1]
      if (nX >= 0 && nY >= 0 && nX < n && nY < m && s[nX][nY] <= s[x][y] + 1 && d[nX][nY] > d[x][y] + 1) {
        d[nX][nY] = d[x][y] + 1;
        q.add(Pair(nX, nY));
      }
    }
  }
  println(d[fX][fY])
}
