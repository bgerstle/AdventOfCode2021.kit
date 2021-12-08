package days

import seriesSum
import parseCSV
import kotlin.math.absoluteValue

fun Int.distanceTo(to: Int): Int {
    return (to - this).absoluteValue
}

class DayTwo(val positions: List<Int>) {
    val partOneSolution: Int? = minPositionAndCost(FuelBurnRate.CONSTANT)?.cost

    val partTwoSolution: Int? = minPositionAndCost(FuelBurnRate.LINEAR)?.cost

    data class PositionAndCost(val position: Int, val cost: Int)

    enum class FuelBurnRate {
        CONSTANT, LINEAR
    }

    fun minPositionAndCost(fuelBurnRate: FuelBurnRate = FuelBurnRate.CONSTANT): PositionAndCost? =
        costsByPosition(fuelBurnRate).minByOrNull(PositionAndCost::cost)

    val allPossiblePositions: IntRange
        get() = positions.minOrNull()!!..positions.maxOrNull()!!

    fun costsByPosition(fuelBurnRate: FuelBurnRate): List<PositionAndCost> =
        allPossiblePositions.fold(emptyList()) { acc, p ->
            val totalCost = costToAlignAt(p, fuelBurnRate)
            listOf(PositionAndCost(position = p, cost = totalCost)) + acc
        }

    fun costToAlignAt(alignmentPosition: Int, fuelBurnRate: FuelBurnRate): Int =
        positions
            .map { position ->
                val distance = position.distanceTo(alignmentPosition)
                when(fuelBurnRate) {
                    FuelBurnRate.CONSTANT -> distance
                    FuelBurnRate.LINEAR -> distance.seriesSum
                }
            }
            .sum()

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
