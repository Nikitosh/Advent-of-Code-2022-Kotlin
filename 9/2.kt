import kotlin.math.abs
import kotlin.math.roundToInt

fun main() {
  val DIRS = "UDLR"
  val TURN = listOf(listOf(-1, 0), listOf(1, 0), listOf(0, -1), listOf(0, 1))
  val LEN = 10
  val x = MutableList(LEN) { 0 }
  val y = MutableList(LEN) { 0 }
  val ts = hashSetOf(Pair(x.last(), y.last()))
  generateSequence(::readLine).forEach {
    val dir = DIRS.indexOf(it[0])
    val k = it.split(' ')[1].toInt()
    repeat(k) {
      x[0] += TURN[dir][0]
      y[0] += TURN[dir][1]
      for (j in 0..x.size - 2) {
        if (abs(x[j + 1] - x[j]) > 1 || abs(y[j + 1] - y[j]) > 1) {
          x[j + 1] = ((2 * x[j] + x[j + 1]).toDouble() / 3).roundToInt()
          y[j + 1] = ((2 * y[j] + y[j + 1]).toDouble() / 3).roundToInt()
        }
      }
      ts.add(Pair(x.last(), y.last()))
    }
  }
  println(ts.size)
}
