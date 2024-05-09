fun main() {
  println(generateSequence(::readLine).fold(mutableListOf(0)) { 
    acc: MutableList<Int>, next: String -> 
    if (next.isEmpty()) {
      acc.add(0)
    } else {
      acc.set(acc.size - 1, acc.get(acc.size - 1) + next.toInt())
    }
    acc
  }.sorted().reversed().subList(0, 3).sum())
}
