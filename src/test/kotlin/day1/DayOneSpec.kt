package day1

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.checkAll

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
