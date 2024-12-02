import kotlin.math.abs

class Day02(path: String) {
    private var reports: List<Report>

    init {
        val input = InputReader().readInput(path)
        val inputReports = mutableListOf<Report>()
        input.forEach { line ->
            val levels = line.trim().split("\\s+".toRegex()).map { it.toLong() }
            inputReports.add(Report(levels = levels))
        }
        reports = inputReports
    }

    fun numberOfSafeReports(): Int {
        return reports.count { it.isSafe() }
    }

    fun numberOfSafeReportsWithProblemDampener(): Int {
        return reports.count { it.isSafeWithProblemDampener() }
    }

    fun debugSafeReports() {
        reports.forEachIndexed { index, report ->
            println("Report #$index: ${report} --> ${report.isSafe()}")
        }
    }

    fun debugSafeReportsWithProblemDampener() {
        reports.forEachIndexed { index, report ->
            println("Report #$index: ${report} --> ${report.isSafeWithProblemDampener()}")
        }
    }
}

data class Report(val levels: List<Long>) {
    fun isSafe(): Boolean {
        return levels.isSafe()
    }

    fun isSafeWithProblemDampener(): Boolean {
        return levels.isSafe() || levels.indices.any {
            levels.slice(levels.indices.minus(it)).isSafe()
        }
    }
}

fun List<Long>.isSafe(): Boolean {
    return if (this == this.sorted() || this == this.sortedDescending()) {
        this.zipWithNext { a, b -> abs(a - b) }.all { it in (1..3) }
    } else false
}

private fun main() {
    val day02Sample = Day02("day02_input_sample.txt")
    val day02Task = Day02("day02_input.txt")
    println("Sample Task1: ${day02Sample.numberOfSafeReports()}")
    println("Real Task1: ${day02Task.numberOfSafeReports()}")
    println("Sample Task2: ${day02Sample.numberOfSafeReportsWithProblemDampener()}")
    println("Real Task2: ${day02Task.numberOfSafeReportsWithProblemDampener()}")
}