import kotlin.math.abs
import kotlin.math.roundToInt

fun main() {
  val DIRS = "UDLR"
  val TURN = listOf(listOf(-1, 0), listOf(1, 0), listOf(0, -1), listOf(0, 1))
  var hx = 0
  var hy = 0
  var tx = 0
  var ty = 0
  val ts = hashSetOf(Pair(tx, ty))
  generateSequence(::readLine).forEach {
    val dir = DIRS.indexOf(it[0])
    val k = it.split(' ')[1].toInt()
    repeat(k) {
      hx += TURN[dir][0]
      hy += TURN[dir][1]
      if (abs(tx - hx) > 1 || abs(ty - hy) > 1) {
        tx = ((2 * hx + tx).toDouble() / 3).roundToInt()
        ty = ((2 * hy + ty).toDouble() / 3).roundToInt()
      }
      ts.add(Pair(tx, ty))
    }
  }
  println(ts.size)
}
