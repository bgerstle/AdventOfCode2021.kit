package day1

import java.io.File


class DayOne(inputs: Collection<Int>) {
    val partOneSolution: Int =
        inputs
            .drop(1)
            .fold(Pair(0, inputs.firstOrNull()!!)) { (increases, lastMeasurement), nextMeasurement ->
                val updatedIncreases = if (nextMeasurement > lastMeasurement) { increases + 1 } else { increases }
                return@fold Pair(updatedIncreases, nextMeasurement)
            }.first

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
        """.trimIndent().lines().map(String::toInt)

        val problem = File("src/main/resources/day1/input.txt").readLines().map(String::toInt).toList()
    }
}
