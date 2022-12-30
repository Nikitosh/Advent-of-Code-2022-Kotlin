fun main() {
    val fromCode = { i : Int -> if (i < 26) 'a' + i else 'A' + i - 26 }
    println(generateSequence(::readLine).map { line -> (0..51).find { line.indexOf(fromCode(it)) in 0..line.length / 2 - 1 
        && line.indexOf(fromCode(it), line.length / 2) != -1 }!! + 1 }.sum())
}
