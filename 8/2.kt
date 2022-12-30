import kotlin.math.max

fun main() {
	val a = generateSequence(::readLine).toList()
	val n = a.size
	val m = a[0].length
	var ans = 0
	val TURN = listOf(listOf(-1, 0), listOf(1, 0), listOf(0, -1), listOf(0, 1))
	for (i in 0..n - 1) {
		for (j in 0..m - 1) {
			ans = max(ans, TURN.map {
				var x = i + it[0]
				var y = j + it[1]
				var cnt = 0
				while (x >= 0 && y >= 0 && x < n && y < m) {
					cnt++
					if (a[x][y] >= a[i][j]) {
						break
					}
					x += it[0]
					y += it[1]
				}
				cnt
			}.reduce(Int::times))
		}
	}
	println(ans)
}
