private fun readln() = readLine()!!
private fun parseInts(str: String): List<Int> = Regex("[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun main() {
    val v = mutableListOf<MutableList<Int>>()
    val tst = mutableListOf<Int>()
    val tru = mutableListOf<Int>()
    val fls = mutableListOf<Int>()
    val op = mutableListOf<(Int) -> Int>()
    generateSequence(::readLine).forEach {
        val ints = parseInts(it)
        if (!it.isEmpty()) {
            if (it[2] == 'S') {
                v.add(ints.toMutableList())
            } else if (it[2] == 'O') {
                val f = { x: Int, y: Int -> if (it.indexOf('*') != -1) x * y else x + y }
                op.add({ x -> if (ints.isEmpty()) f(x, x) else f(x, ints[0]) })
            } else if (it[2] == 'T') {
                tst.add(ints[0])
            } else if (it[4] == 'I' && it[7] == 't') {
                tru.add(ints[0])
            } else if (it[4] == 'I' && it[7] == 'f') {
                fls.add(ints[0])
            }
        }
    }    
    val n = v.size
    val ROUNDS = 20
    val ans = MutableList(n) { 0 }
    repeat(ROUNDS) {
        for (i in 0..n - 1) {
            ans[i] += v[i].size
            for (k in v[i]) {
                val newK = op[i](k) / 3
                val ind = if (newK % tst[i] == 0) tru[i] else fls[i]
                v[ind].add(newK)
            }
            v[i].clear()
        }
    }
    println(ans.sortedDescending().subList(0, 2).reduce(Int::times))
}
