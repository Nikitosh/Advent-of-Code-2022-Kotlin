import kotlin.math.abs
import kotlin.math.sign

private fun parseInts(str: String): List<Int> = Regex("-?[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun main() {
  val a = generateSequence(::readLine).map { parseInts(it)[0] }.withIndex().toMutableList()
  val oldA = a.toList()
  val n = a.size
  for (j in 0..n - 1) {
    val dlt = oldA[j].value % (n - 1)
    var ind = a.indexOfFirst { it == oldA[j] }
    repeat(abs(dlt)) {
      val nextInd = (ind + dlt.sign + n) % n
      a[ind] = a[nextInd].also { a[nextInd] = a[ind] }
      ind = nextInd
    }
  }
  var ind = a.indexOfFirst { it.value == 0 }
  println((1..3).map {
    ind = (ind + 1000) % n
    a[ind].value
  }.sum())
}
