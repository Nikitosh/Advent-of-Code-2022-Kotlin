private fun parseInts(str: String): List<Int> = Regex("-?[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun get(s: String, deps: Map<String, Triple<Char, String, String>>, go: MutableMap<String, Pair<Long, Boolean>>): Pair<Long, Boolean> {
	val value = go.get(s)
	if (value != null) {
		return value
	}
	val op1 = get(deps[s]!!.second, deps, go)
	val op2 = get(deps[s]!!.third, deps, go)
	val res = Pair(when(deps[s]!!.first) {
		'+' -> op1.first + op2.first
		'-' -> op1.first - op2.first
		'*' -> op1.first * op2.first
		'/' -> op1.first / op2.first
		else -> 0
	}, op1.second || op2.second)
	go[s] = res
	return res
}

fun calc(s: String, value: Long, deps: Map<String, Triple<Char, String, String>>, go: MutableMap<String, Pair<Long, Boolean>>): Long {
	if (s == "humn") {
		return value
	}
	var depName = deps[s]!!.second
	var other = get(deps[s]!!.third, deps, go).first
	if (!get(deps[s]!!.second, deps, go).second) {
		depName = deps[s]!!.third
		other = get(deps[s]!!.second, deps, go).first
	}
	return when(deps[s]!!.first) {
		'+' -> calc(depName, value - other, deps, go)
		'-' -> calc(depName, if (depName == deps[s]!!.second) value + other else other - value, deps, go)
		'*' -> calc(depName, value / other, deps, go)
		'/' -> calc(depName, if (depName == deps[s]!!.second) value * other else other / value, deps, go)
		else -> 0
	}
}

fun main() {
	val input = generateSequence(::readLine).toList()
	val deps = input.map {
		val v = parseInts(it)
		if (!v.isEmpty()) "" to Triple(' ', "", "") else it.substring(0, 4) to Triple(it[11], it.substring(6, 10), it.substring(13, 17))
	}.toMap()
	val go = input.map {
		val v = parseInts(it)
		val name = it.substring(0, 4)
		if (v.isEmpty()) "" to Pair(0L, false) else name to Pair(v[0].toLong(), name == "humn")
	}.toMap().toMutableMap()
	var humnDep = deps["root"]!!.second
	var otherDep = deps["root"]!!.third
	if (!get(humnDep, deps, go).second) {
		humnDep = otherDep.also { otherDep = humnDep }
	}
	println(calc(humnDep, get(otherDep, deps, go).first, deps, go))
}
