package day1

class ExpenseReport(val rows: Collection<Int>) {
    val solution: Int?
        get() = rows.pairsWithSum(2020).firstOrNull()?.let { pair -> pair.first * pair.second }
}

fun Collection<Int>.pairsWithSum(sum: Int): Collection<Pair<Int, Int>> {
    return pairPermutations.filter { (x, y) -> x + y == sum }
}

val <T> Collection<T>.pairPermutations: Collection<Pair<T, T>>
    get() =
        if (count() < 2) emptyList<Pair<T, T>>() else {
            withIndex().fold(emptyList<Pair<T, T>>()) { acc, (index, element) ->
                acc + drop(index + 1).map { Pair(element, it) }
            }
        }
