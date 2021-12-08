package days

import parseIntPerLine
import java.io.File

val Collection<Int>.numberOfIncreases
    get() =
        drop(1)
            .fold(Pair(0, firstOrNull()!!)) { (increases, lastMeasurement), nextMeasurement ->
                val updatedIncreases = if (nextMeasurement > lastMeasurement) { increases + 1 } else { increases }
                return@fold Pair(updatedIncreases, nextMeasurement)
            }
            .first

val List<Int>.sum
    get() = reduce(Int::plus)

class DayOne(inputs: Collection<Int>) {
    val partOneSolution: Int = inputs.numberOfIncreases

    val partTwoSolution: Int = inputs
        .windowed(size = 3, partialWindows = true)
        .map(List<Int>::sum)
        .numberOfIncreases

    object Inputs {
        val example = """
            199
            200
            208
            210
            200
            207
            240
            269
            260
            263
        """.parseIntPerLine()

        val actual = javaClass.getResource("day1.txt").readText().parseIntPerLine()
    }
}
