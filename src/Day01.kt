// Not my original solution, solution by guys from Kotlin

fun main() {
    val myList = readInput(name = "input")

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("test_input")
    val testDay01 = Day01(testInput)
    check(testDay01.solvePart1() == 142)

    val myInput = readInput("input")
    val myDay01 = Day01(myInput)
    println(myDay01.solvePart1())

    val secondTestInput = readInput("test_second_input")
    val secondTestDay01 = Day01(secondTestInput)
    check(secondTestDay01.solvePart2() == 281)
    println(myDay01.solvePart2())

}

class Day01(private val input: List<String>) {

    private val letterToNumberMap = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
    )

    fun solvePart1(): Int {
        return input.sumOf { calibrationValue(it) }
    }

    private fun calibrationValue(row: String): Int {
        // find the first element that matches condition on the left, concatenate to first element to match condition on the right, solves the
        // problem of rows with one digit.

        val firstDigit = row.first { it.isDigit() }
        val lastDigit = row.last { it.isDigit() }
        return "$firstDigit$lastDigit".toInt()
    }

    private fun String.possibleWordsAt(startingAt: Int): List<String> {
        return (3..5).map { len ->
            substring(startingAt, (startingAt + len).coerceAtMost(length))
        }
    }

    fun solvePart2(): Int {
        return input.sumOf { row ->
            calibrationValue(
                    row.mapIndexedNotNull { index, c ->
                        if (c.isDigit()) c
                        else
                            row.possibleWordsAt(index).firstNotNullOfOrNull { candidate ->
                                letterToNumberMap[candidate]
                            }

                    }.joinToString()
            )
        }
    }

}
