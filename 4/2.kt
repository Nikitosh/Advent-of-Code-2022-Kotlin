import kotlin.math.min
import kotlin.math.max

private fun parseInts(str: String) = Regex("[0-9]+").findAll(str).map(MatchResult::value).map(String::toInt).toList()

fun main() {
    println(generateSequence(::readLine).map(::parseInts).map { 
        if (max(it[0], it[2]) <= min(it[1], it[3])) 1 else 0
    }.sum())
}
