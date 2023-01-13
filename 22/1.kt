private fun parseInts(str: String): List<Int> = Regex("-?[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun Int.add(b: Int, mod: Int): Int {
	val res = this + b
	if (res < 0) {
		return res + mod
	}
	return if (res >= mod) res - mod else res
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
	val m = a.map { it.length }.max()
	val n = a.size
	a = a.map { it.padEnd(m) }.toMutableList()
	val s = readLine()!!
	val v = parseInts(s)
	var x = 0
	var y = a[0].indexOf('.')
	var dir = 0
	val dirs = s.filter { it == 'L' || it == 'R' }
	val TURN = listOf(listOf(0, 1), listOf(1, 0), listOf(0, -1), listOf(-1, 0))
	for (i in 0..dirs.length) {
		for (j in 0..v[i] - 1) {
			var curX = x
			var curY = y
			do {
				curX = curX.add(TURN[dir][0], n)
				curY = curY.add(TURN[dir][1], m)
			} while (a[curX][curY] == ' ')
			if (a[curX][curY] == '.') {
				x = curX
				y = curY
			}
			else {
				break
			}
		}
		if (i != v.size - 1) {
			dir = dir.add(if (dirs[i] == 'L') -1 else 1, 4)
		}
	}
	println((x + 1) * 1000 + (y + 1) * 4 + dir)
}
