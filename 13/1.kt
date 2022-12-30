import kotlin.math.min

data class Node(val value: Int, val children: MutableList<Node>) {
	constructor(value: Int): this(value, mutableListOf<Node>())
	constructor(children: MutableList<Node>): this(-1, children)
}

fun parse(s: String, ind: Int): Pair<Node, Int> {
	if (s[ind] == '[') {
		val children = mutableListOf<Node>()
		var curInd = ind + 1
		while (s[curInd] != ']') {
			var (child, newCurInd) = parse(s, curInd)
			curInd = newCurInd
			children.add(child)
			if (s[curInd] == ',') {
				curInd++
			}
		}
		curInd++
		return Pair(Node(children), curInd)
	}
	if (s[ind].isDigit()) {
		var endInd = ind
		while (endInd < s.length && s[endInd].isDigit())
			endInd++
		return Pair(Node(s.substring(ind, endInd).toInt()), endInd)
	}
	return Pair(Node(-1), 0)
}

fun comp(l: Node, r: Node): Boolean {
	if (l.value != -1 && r.value != -1) {
		return l.value < r.value
	}
	if (l.value != -1) {
		return comp(Node(mutableListOf(Node(l.value))), r)
	}
	if (r.value != -1) {
		return comp(l, Node(mutableListOf(Node(r.value))))
	}
	for (lr in l.children.zip(r.children)) {
		if (comp(lr.first, lr.second)) {
			return true
		}
		if (comp(lr.second, lr.first)) {
			return false
		}
	}
	return l.children.size < r.children.size
}

fun main() {
	println(generateSequence(::readLine).windowed(2, 3, false).mapIndexed { 
		index, value -> if (comp(parse(value[0], 0).first, parse(value[1], 0).first)) index + 1 else 0
	}.sum())
}