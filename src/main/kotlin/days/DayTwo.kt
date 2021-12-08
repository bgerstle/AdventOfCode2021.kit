package days

import parseCSV
import kotlin.math.absoluteValue

fun Int.distance(to: Int): Int {
    return (to - this).absoluteValue
}

class DayTwo(val positions: List<Int>) {
    data class PositionAndCost(val position: Int, val cost: Int)

    val minPositionAndCost: PositionAndCost? =
        costsByPosition
            .entries
            .minByOrNull { it.value }
            ?.let { PositionAndCost(position = it.key, cost = it.value) }

    val partOneSolution: Int? = minPositionAndCost?.cost

    val costsByPosition: Map<Int, Int>
        get() = positions
            .fold(mapOf()) { acc, p ->
                val totalCost = costToAlignAt(p)
                mapOf(p to totalCost) + acc
            }

    fun costToAlignAt(position: Int): Int =
        positions
            .map { it.distance(position) }
            .sum

    object Inputs {
        val example: List<Int> = """
        16,1,2,0,4,2,7,1,2,14
    """.parseCSV().toList()

        val actual: List<Int> =
            javaClass.getResource("day2.txt")
                .readText()
                .trim()
                .split(",")
                .map(String::toInt)
    }
}
