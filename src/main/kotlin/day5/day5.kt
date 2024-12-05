package day5

val rules = mutableMapOf<Int, MutableList<Int>>()

fun main() {

    println(part1("day5.txt"))
    println(part2("day5.txt"))

}

fun part1(inputFile: String) : Int {

    val updates = processInput(inputFile)

    var count = 0

    updates.forEach { update ->
        if(checkIfUpdateIsInTheRightOrder(update)) {
            count += getMiddleNumber(update)
        }
    }
    return count
}

fun part2(inputFile: String) : Int {
    val updates = processInput(inputFile)

    var count = 0

    updates.forEach { pageNumbers ->
        if(!checkIfUpdateIsInTheRightOrder(pageNumbers)) {
            val orderedPageNumbers = orderPageNumbers(pageNumbers)
            count += getMiddleNumber(orderedPageNumbers)
        }
    }
    return count
}

fun orderPageNumbers(pageNumbers: List<Int>) : List<Int> {

    return pageNumbers.sortedWith(CustomComparator(rules))

}

fun getMiddleNumber(pageNumbers: List<Int>) : Int {
    return pageNumbers.get((pageNumbers.size - 1) / 2)
}

fun processInput(inputFile: String): List<List<Int>> {
    val input  = FileUtils.readFile(inputFile).readText().split("\n\n")

    input[0].split("\n").forEach{
        val pair = it.split("|")
        val key = pair[0].toInt()
        val value = pair[1].toInt()

        if(rules.containsKey(key)) {
            rules.getValue(key).add(value)
        } else {
            rules.put(key, mutableListOf(value))
        }

    }

    var updates = input[1].split("\n").map { line ->
        line.split(",").map { it.toInt() }.toList()
    }

    return updates
}

fun checkIfUpdateIsInTheRightOrder(pageNumbers: List<Int>) : Boolean {

    pageNumbers.forEach { pageNumber ->
        if(!checkIfNumberIsAtTheCorrectPlace(pageNumber, pageNumbers)) return false
    }

    return true;
}

fun checkIfNumberIsAtTheCorrectPlace(numberToCheck: Int, pageNumbers: List<Int>) : Boolean {

    pageNumbers.forEach { pageNumber ->
        if(numberToCheck != pageNumber) {
            if(pageNumbers.indexOf(numberToCheck) < pageNumbers.indexOf(pageNumber)) {
                if(!checkIfNumberNeedsToBePrintedBefore(numberToCheck, pageNumber)) return false
            }
        }
    }
    return true;
}

fun checkIfNumberNeedsToBePrintedBefore(numberToCheck: Int, pageNumber: Int) : Boolean{
    return rules.get(numberToCheck)?.contains(pageNumber) ?: false
}
