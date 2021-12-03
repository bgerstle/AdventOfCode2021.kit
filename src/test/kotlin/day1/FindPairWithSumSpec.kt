package day1

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.collections.shouldBeUnique
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.*
import io.kotest.property.checkAll
import io.kotest.property.forAll

val Int.factorial: Int
    get() = when {
        (this <= 0) -> 0
        else -> this + (this - 1).factorial
    }

val rangeAtLeastTwoNumbersLong = Arb.int(min = 1).map { (0..1).toList() }
val pairOfPositiveInts: Arb<Pair<Int, Int>> =
    Arb.bind(Arb.positiveInt(), Arb.positiveInt()) { x, y -> Pair(x, y) }

class FindPairWithSumSpec : FunSpec({
    context("returns null with less than 2 elements") {
        withData(listOf(), listOf(0)) { rows -> Unit
            checkAll<Int> { i ->
                rows.pairsWithSum(i).shouldBeEmpty()
            }
        }
    }

    test("findPairWithSum returns pair of numbers that sum to specified amount") {
        checkAll(Arb.list(Arb.int()), pairOfPositiveInts) { otherNumbers, pair ->
            val sum = pair.first + pair.second
            pair shouldBeIn (listOf(pair.first, pair.second) + otherNumbers).pairsWithSum(sum)
        }
    }

    context("pairPermutations") {
        context("properties") {
            test("list with size of 2 is a single pair") {
                checkAll<Int, Int> { x, y ->
                    listOf(x, y).pairPermutations shouldBe listOf(Pair(x, y))
                }
            }

            test("number of pairs is factorial of collection size - 1") {
                checkAll(rangeAtLeastTwoNumbersLong) { xs ->
                    xs.pairPermutations.size shouldBe (xs.size - 1).factorial
                }
            }

            test("all pairs unique") {
                checkAll(rangeAtLeastTwoNumbersLong) { xs ->
                    xs.pairPermutations.shouldBeUnique()
                }
            }

            test("all elements present") {
                forAll(rangeAtLeastTwoNumbersLong) { xs ->
                    val permutations = xs.pairPermutations
                    xs.all { x ->
                        permutations.find { pair -> x == pair.first || x == pair.second } != null
                    }
                }
            }
        }

    }
})
