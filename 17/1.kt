import kotlin.math.abs

fun findFirstEmpty(a: List<MutableList<Int>>): Int {
	return a.indexOfFirst { it.sum() == 0 } 
}

fun main() {
	val figures = listOf(
		listOf("1111"),
		listOf("010", "111", "010"),
		listOf("001", "001", "111"),
		listOf("1", "1", "1", "1"),
		listOf("11", "11")
	)
	val s = readLine()!!
	var curF = 0
	var curS = 0
	val ROUNDS = 2022
	val W = 7
	val a = List(ROUNDS * W) { MutableList(W) { 0 } }
	repeat(ROUNDS) {
		var y = findFirstEmpty(a) + 3
		var x = 2
		val h = figures[curF].size
		val w = figures[curF][0].length
		var run = true
		while (run) {
			for (g in 0..1) {
				val oldX = x
				val oldY = y
				if (g == 0) {
					if (s[curS] == '<') {
						x--
					} else {
						x++
					}
					curS = (curS + 1) % s.length
				} else {
					y--
				}
				var ok = (x >= 0 && x + w <= W && y >= 0)
				if (ok) {
					for (i in 0..h - 1) {
						for (j in 0..w - 1) {
							if (figures[curF][i][j] == '1' && a[y + h - 1 - i][x + j] == 1) {
								ok = false
								break
							}
						}
					}
				}
				if (!ok) {
					x = oldX
					y = oldY
					if (g == 1) {
						run = false
					}
				}
			}
		}
		for (i in 0..h - 1) {
			for (j in 0..w - 1) {
				if (figures[curF][i][j] == '1') {
					a[y + h - 1 - i][x + j] = 1
				}
			}
		}
		curF = (curF + 1) % figures.size
	}
	println(findFirstEmpty(a))
}
