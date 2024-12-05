package day3

val regex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
val dontRegex = """don't\(\).*?(?:do\(\)|$)""".toRegex()

fun main() {

    val computerMemory = FileUtils.readFile("day3.txt").readText()

    println(part1(computerMemory))
    println(part2(computerMemory))
}


fun part1(input : String) =
        regex
            .findAll(input)
            .sumOf {
                it.groupValues[1].toInt() * it.groupValues[2].toInt()
            }

fun part2(input : String) : Int{

    val newInput = input
        .replace("\n", "|")
        .replace(dontRegex, "")

    return part1(newInput)
}