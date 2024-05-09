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

class ComparatorNode: Comparator<Node> {
  override fun compare(l: Node, r: Node): Int {
    if (l.value != -1 && r.value != -1) {
      return l.value - r.value
    }
    if (l.value != -1) {
      return compare(Node(mutableListOf(Node(l.value))), r)
    }
    if (r.value != -1) {
      return compare(l, Node(mutableListOf(Node(r.value))))
    }
    for (lr in l.children.zip(r.children)) {
      compare(lr.first, lr.second).let { if (it != 0) return it } 
    }
    return l.children.size - r.children.size
  }
}
fun main() {
  val nodes = generateSequence(::readLine).filter { !it.isEmpty() }.map { parse(it, 0).first }.toMutableList()
  val divs = mutableListOf<Node>()
  for (value in listOf(2, 6)) {
    val node = Node(mutableListOf(Node(mutableListOf(Node(value)))))
    nodes.add(node)
    divs.add(node)
  }
  nodes.sortWith(ComparatorNode())
  println((nodes.indexOf(divs[0]) + 1) * (nodes.indexOf(divs[1]) + 1)) 
}
