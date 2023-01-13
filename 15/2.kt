import kotlin.math.abs

private fun parseInts(str: String): List<Int> = Regex("-?[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun main() {
	val v = generateSequence(::readLine).map { parseInts(it) }.toList()
	val MAX = 4000000
	val ans = v.map {
		lst -> 
			val dst = abs(lst[2] - lst[0]) + abs(lst[3] - lst[1]) + 1
			IntRange(-dst, dst).map { listOf<Pair<Int, Int>>(Pair(lst[0] + it, lst[1] + dst - abs(it)), Pair(lst[0] + it, lst[1] - (dst - abs(it)))) }.flatten().filter {
				(x, y): Pair<Int, Int> -> x >= 0 && y >= 0 && x <= MAX && y <= MAX && v.all {
					abs(x - it[0]) + abs(y - it[1]) > abs(it[2] - it[0]) + abs(it[3] - it[1])
				}
			}
	}.flatten().first()
	println(ans.first.toLong() * MAX + ans.second)
}
