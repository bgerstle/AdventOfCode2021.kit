import java.io.InputStream

fun String.parseIntPerLine() = trimIndent().lines().map(String::toInt)

fun String.parseCSV() = trimIndent().split(",").map(String::toInt)
