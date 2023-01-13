import kotlin.math.min
import kotlin.math.max

private fun parseInts(str: String): List<Int> = Regex("[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun main() {
	val N = 1000
	val START_X = 500
	val START_Y = 0
	val a = MutableList(N) { MutableList(N) { 0 } }
	var maxY = -1
	generateSequence(::readLine).forEach {
		val ints = parseInts(it)
		ints.windowed(4, 2).forEach {
			(x1, y1, x2, y2) -> 
				a.subList(min(x1, x2), max(x1, x2) + 1).forEach {
					it.subList(min(y1, y2), max(y1, y2) + 1).fill(1)
				}
		}
		maxY = max(maxY, ints.slice(1..ints.size step 2).max())
	}

	var ans = 0
	while (a[START_X][START_Y] == 0) {
		var x = START_X
		var y = START_Y
		var overflow = false
		while (a[x][y] == 0) {
			var was = false
			for (dx in listOf(0, -1, 1)) {
				if (a[x + dx][y + 1] == 0) {
					x += dx
					y++
					was = true
					break
				}
			}
			if (y > maxY) {
				overflow = true
				break
			}
			if (!was) {
				break
			}
		}
		if (overflow) {
			break
		}
		a[x][y] = 1
		ans++
	}
	println(ans)
}
