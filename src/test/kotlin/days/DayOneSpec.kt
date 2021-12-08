package days

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DayOneSpec : FunSpec({
    context("part one") {
        test("solves the example") {
            DayOne(DayOne.Inputs.example).partOneSolution shouldBe 7
        }
    }

    context("part two") {
        test("solves the example") {
            DayOne(DayOne.Inputs.example).partTwoSolution shouldBe 5
        }
    }
})
