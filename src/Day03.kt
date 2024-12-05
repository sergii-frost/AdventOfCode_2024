class Day03(val path: String) {
    private val program: String

    init {
        val input = InputReader().readInput(path)
        //All the input must be read as one string
        program = input.joinToString()
    }

    fun mulItOverTask1(): Long {
        return Instruction(program).mul()
    }

    fun mulItOverTask2(): Long {
        return Instruction(program).mulCorrupted()
    }
}

private data class Instruction(val input: String) {
    private val mulRegex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")
    private val mulDoNotDoRegex = Regex("don't\\(\\).*?(?:do\\(\\)|$)")
    private val numberRegex = Regex("\\d+")

    fun mul(): Long {
        return mul(input)
    }

    //The logic behind this is that we simply should ignore/replace whatever comes between don't()...do()
    //After replacing "corrupted" matches with empty string we can simply reuse cleaned out output to calculate result as in task1

    fun mulCorrupted(): Long {
        val cleanInput = mulDoNotDoRegex.replace(input, "")
        return mul(cleanInput)
    }

    private fun mul(input: String): Long {
        return mulRegex.findAll(input).map {
            var mulResult: Long = 1
            numberRegex.findAll(it.value).forEach { num -> mulResult *= num.value.toLong() }
            mulResult
        }.sum()
    }
}

private fun main() {
    val day03Sample = Day03("day03_input_sample.txt")
    val day03Sample1 = Day03("day03_input_task2_sample.txt")
    val day03Task = Day03("day03_input.txt")
    println("Sample Task1: ${day03Sample.mulItOverTask1()}")
    println("Real Task1: ${day03Task.mulItOverTask1()}")
    println("Sample Task2: ${day03Sample1.mulItOverTask2()}")
    println("Real Task2: ${day03Task.mulItOverTask2()}")
}