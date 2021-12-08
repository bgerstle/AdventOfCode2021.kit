import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll

class CommonSpec : FunSpec({
    context("series sum") {
        context("small numbers are fibonacci's of themselves") {
            withData(listOf(0, 1)) { x ->
                x.seriesSum shouldBe x
            }
        }

        test("seriesSum(n) is sum of 1..n") {
            checkAll(Arb.int(min = 2, max = 200)) { x ->
                x.seriesSum shouldBe (1..x).sum()
            }
        }
    }
})
