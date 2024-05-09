private fun readln() = readLine()!!
private fun parseInts(str: String): List<Int> = Regex("[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun main() {
  val v = mutableListOf<MutableList<Long>>()
  val tst = mutableListOf<Int>()
  val tru = mutableListOf<Int>()
  val fls = mutableListOf<Int>()
  val op = mutableListOf<(Long) -> Long>()
  var mod = 1
  generateSequence(::readLine).forEach {
    val ints = parseInts(it)
    if (!it.isEmpty()) {
      if (it[2] == 'S') {
        v.add(ints.map { it.toLong() }.toMutableList())
      } else if (it[2] == 'O') {
        val f = { x: Long, y: Long -> if (it.indexOf('*') != -1) x * y else x + y }
        op.add({ x -> if (ints.isEmpty()) f(x, x) else f(x, ints[0].toLong()) })
      } else if (it[2] == 'T') {
        tst.add(ints[0])
        mod *= ints[0]
      } else if (it[4] == 'I' && it[7] == 't') {
        tru.add(ints[0])
      } else if (it[4] == 'I' && it[7] == 'f') {
        fls.add(ints[0])
      }
    }
  }    
  val n = v.size
  val ROUNDS = 10000
  val ans = MutableList<Long>(n) { 0 }
  repeat(ROUNDS) {
    for (i in 0..n - 1) {
      ans[i] += v[i].size.toLong()
      for (k in v[i]) {
        val newK = op[i](k) % mod
        val ind = if (newK % tst[i] == 0L) tru[i] else fls[i]
        v[ind].add(newK)
      }
      v[i].clear()
    }
  }
  println(ans.sortedDescending().subList(0, 2).reduce(Long::times))
}
