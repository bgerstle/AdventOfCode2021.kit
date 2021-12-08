package days

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.shuffle
import io.kotest.property.checkAll
import kotlin.math.absoluteValue


class DayTwoSpec : FunSpec({
    context("part one") {
        test("solves the example") {
            DayTwo(DayTwo.Inputs.example).partOneSolution shouldBe 2
        }

        test("picks same answer for arbitrary ordering of inputs") {
            checkAll(Arb.shuffle(DayTwo.Inputs.example)) { positions ->
                DayTwo(positions).partOneSolution shouldBe 2
            }
        }
    }

    context("costToAlignAt") {
        test("aligning to 0 is the same as the sum of positions") {
            checkAll(Arb.list(Arb.int(min = 0), 1..100)) { ints ->
                val d2 = DayTwo(ints)
                d2.costToAlignAt(0) shouldBe ints.sum
            }
        }
    }

    context("Int.distance") {
        test("is zero when position == alignAt") {
            checkAll(Arb.list(Arb.int(min = 0), 1..100)) { ints ->
                val costs = ints.map { it.distance(it) }
                costs.all { it == 0 } shouldBe true
            }
        }

        test("is the absolute distance between position and alignAt") {
            checkAll(Arb.int(min = 0), Arb.int()) { position, distance ->
                val alignAt = position + distance
                position.distance(alignAt) shouldBe distance.absoluteValue
            }
        }
    }
})
