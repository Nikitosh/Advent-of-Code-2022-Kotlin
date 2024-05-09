import kotlin.math.min

fun main() {
  val fromCode = { i : Int -> if (i < 26) 'a' + i else 'A' + i - 26 }
  println(generateSequence(::readLine).windowed(3, 3, false, { strs -> (0..51).find { min(min(strs[0].indexOf(fromCode(it)), strs[1].indexOf(fromCode(it))), strs[2].indexOf(fromCode(it))) != -1}!! + 1 }).sum())
}
