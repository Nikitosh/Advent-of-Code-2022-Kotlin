private fun readln() = readLine()!!
private fun parseInts(str: String): List<Int> = Regex("[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun main() {
    val STACKS = 9;
    var v = List(STACKS) { mutableListOf<Char>() }
    while (true) {
        val s = readln()
        if (s.isEmpty()) {
            break;
        }
        for (i in 0..s.length - 1) {
            if (s.get(i).isLetter()) {
                v[i / 4].add(s[i]);
            }
        }
    }
    v.forEach(MutableList<Char>::reverse)
    generateSequence(::readLine).map(::parseInts).forEach { 
        lst -> repeat(lst[0]) {
            v[lst[2] - 1].add(v[lst[1] - 1].last())
            v[lst[1] - 1].removeLast()
        }
    }
    v.map(List<Char>::last).forEach(::print)
}
