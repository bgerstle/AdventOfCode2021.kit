package day1

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import java.io.File


class ExpenseReportSpec : FunSpec({
    test("returns null when input has no solution") {
        ExpenseReport(listOf(1, 2)).solution.shouldBeNull()
    }

    test("yields expected solution to the example") {
        ExpenseReport(listOf(1721, 979, 366, 299, 675, 1456)).solution shouldBe 514579
    }
})
