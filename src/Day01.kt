import kotlin.math.abs

class Day01(path: String) {
    private var leftList: List<Long>
    private var rightList: List<Long>

    init {
        val input = InputReader().readInput(path)
        val left: MutableList<Long> = mutableListOf()
        val right: MutableList<Long> = mutableListOf()

        input.forEach { line ->
            val values = line.trim().split("\\s+".toRegex()).map { it.toLong() }
            // Safe check that we are getting exactly 2 values
            if (values.size == 2) {
                left.add(values.first())
                right.add(values.last())
            }
        }

        leftList = left
        rightList = right
    }

    fun calculateDistance(): Long {
        return leftList.sorted()
            .zip(rightList.sorted())
            .sumOf { abs(it.first - it.second) }
    }

    fun calculateSimilarityScore(): Long {
        val similarity = this.leftList.map { left ->
            rightList.count { it == left }
        }

        return leftList.zip(similarity).sumOf { it.first * it.second }
    }
}

private fun main() {
    val day1Sample = Day01("day01_input_sample.txt")
    val day1Task = Day01("day01_input.txt")
    println("Sample Task1: ${day1Sample.calculateDistance()}")
    println("Real Task1: ${day1Task.calculateDistance()}")
    println("Sample Task2: ${day1Sample.calculateSimilarityScore()}")
    println("Real Task2: ${day1Task.calculateSimilarityScore()}")
}
