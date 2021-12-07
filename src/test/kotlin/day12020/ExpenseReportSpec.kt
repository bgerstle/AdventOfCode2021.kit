package day12020

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldStartWith
import io.kotest.mpp.start


class ExpenseReportSpec : FunSpec({
    test("returns null when input has no solution") {
        ExpenseReport(listOf(1, 2)).partOneSolution.shouldBeNull()
    }

    test("yields expected solution to the example") {
        ExpenseReport(listOf(1721, 979, 366, 299, 675, 1456)).partOneSolution shouldBe 514579
    }

    test("yields expected solution to the part two example") {
        ExpenseReport(listOf(1721, 979, 366, 299, 675, 1456)).partTwoSolution shouldBe 241861950
    }
})
