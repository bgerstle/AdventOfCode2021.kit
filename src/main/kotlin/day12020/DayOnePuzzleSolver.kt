package day12020

import java.io.File

class ExpenseReport(val rows: Collection<Int>) {
    val partOneSolution: Int?
        get() = rows
            .pairsWithSum(2020)
            .firstOrNull()
            ?.let { pair -> pair.first * pair.second }

    val partTwoSolution: Int?
        get() = rows
            .flatMap { z ->
                val subtotal = 2020 - z
                val pairs = rows.pairsWithSum(subtotal)
                pairs.map { (x, y) -> Triple(x, y, z) }
            }
            .distinct()
            .firstOrNull()
            ?.let { (x, y, z) -> x * y * z }
}

object DayOne {
    val input: Collection<Int>
        get() = File("src/main/resources/day12020/input.txt").readLines().map(String::toInt).toList()

    fun solve() {
        println("Solution is ${ExpenseReport(input).partOneSolution}!")
    }

    fun solvePartTwo() {
        println("Solution is ${ExpenseReport(input).partTwoSolution}!")
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
