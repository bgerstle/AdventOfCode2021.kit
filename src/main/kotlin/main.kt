import day1.ExpenseReport
import java.io.File

fun main(args: Array<String>) {
    val inputPath = "src/main/resources/day1/input.txt"
    val rows: Collection<Int> = File(inputPath).readLines().map(String::toInt).toList()
    val report = ExpenseReport(rows)

    println("Solution is ${report.solution}!")
}
