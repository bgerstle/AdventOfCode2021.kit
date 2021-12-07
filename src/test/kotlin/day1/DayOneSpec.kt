package day1

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DayOneSpec : FunSpec({
    test("solves the example") {
        DayOne(DayOne.Inputs.example).partOneSolution shouldBe 7
    }
})
