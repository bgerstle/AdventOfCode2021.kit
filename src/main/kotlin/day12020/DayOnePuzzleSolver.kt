package day12020

import java.io.File

class ExpenseReport(val rows: Collection<Int>) {
    val solution: Int?
        get() = rows.pairsWithSum(2020).firstOrNull()?.let { pair -> pair.first * pair.second }
}

object DayOne {
    fun solve() {
        val inputPath = "src/main/resources/day1/input.txt"
        val rows: Collection<Int> = File(inputPath).readLines().map(String::toInt).toList()
        val report = ExpenseReport(rows)

        println("Solution is ${report.solution}!")
    }

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
