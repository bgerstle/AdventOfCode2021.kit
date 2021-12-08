package days

import seriesSum
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.shuffle
import io.kotest.property.checkAll
import kotlin.math.absoluteValue


class DayTwoSpec : FunSpec({
    context("examples") {
        test("solves part one example") {
            DayTwo(DayTwo.Inputs.example).partOneSolution shouldBe 37
        }

        test("picks same answer for arbitrary ordering of inputs") {
            checkAll(Arb.shuffle(DayTwo.Inputs.example)) { positions ->
                DayTwo(positions).partOneSolution shouldBe 37
            }
        }

        test("solves part two example") {
            DayTwo(DayTwo.Inputs.example).partTwoSolution shouldBe 168
        }
    }

    context("costToAlignAt") {
        test("aligning to 0 with CONSTANT burn rate is the same as the sum of positions") {
            checkAll(Arb.list(Arb.int(min = 0, max = 100), 1..100)) { ints ->
                val d2 = DayTwo(ints)
                d2.costToAlignAt(0, DayTwo.FuelBurnRate.CONSTANT) shouldBe ints.sum()
            }
        }

        test("aligning to 0 with linear burn rate is the same as the sum of positions' seriesSum") {
            checkAll(Arb.list(Arb.int(min = 0, max = 100), 1..100)) { ints ->
                val d2 = DayTwo(ints)
                d2.costToAlignAt(0, DayTwo.FuelBurnRate.LINEAR) shouldBe ints.map(Int::seriesSum).sum()
            }
        }
    }

    context("Int.distance") {
        test("is zero when position == alignAt") {
            checkAll(Arb.list(Arb.int(min = 0), 1..100)) { ints ->
                val costs = ints.map { it.distanceTo(it) }
                costs.all { it == 0 } shouldBe true
            }
        }

        test("is the absolute distance between position and alignAt") {
            checkAll(Arb.int(min = 0), Arb.int()) { position, distance ->
                val alignAt = position + distance
                position.distanceTo(alignAt) shouldBe distance.absoluteValue
            }
        }
    }
})
