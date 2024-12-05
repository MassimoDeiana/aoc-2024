package day4

val aroundMatrix = listOf<Pair<Int, Int>>(
    Pair(-1, -1),
    Pair(-1, 0),
    Pair(-1, 1),
    Pair(0, -1),
    Pair(0, 1),
    Pair(1, -1),
    Pair(1, 0),
    Pair(1, 1),
)

val aroundMatrixPart2 = listOf<Pair<Int, Int>>(
    Pair(-1, -1),
    Pair(1, -1),
    Pair(-1, 1),
    Pair(1, 1)
)

val patterns = listOf<Pattern>(
    Pattern('M', 'S', 'M', 'S'),
    Pattern('M', 'M', 'S', 'S'),
    Pattern('S', 'M', 'S', 'M'),
    Pattern('S', 'S', 'M', 'M')
)

fun main() {

    val matrix = FileUtils
        .readFile("day4.txt")
        .readLines()
        .map { it.toCharArray() }
        .toTypedArray()

    println(part1(matrix))
    println(part2(matrix))

}

fun part1(matrix: Array<CharArray>) : Int {
    var count = 0

    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            if(matrix[i][j] == 'X') {
                count += applyAroundMatrix(matrix, i, j)
            }
        }
    }

    return count
}

fun part2(matrix: Array<CharArray>) : Int {
    var count = 0

    for (i in 1 until matrix.size - 1) {
        for (j in 1 until matrix[0].size - 1) {
            if(matrix[i][j] == 'A') {
                count += checkPatternMatch(matrix, i, j)
            }
        }
    }

    return count
}

fun checkPatternMatch(matrix: Array<CharArray>, i: Int, j: Int) : Int {
    return patterns.count { pattern ->
        checkTopLeft(matrix, i, j, pattern.topLeft)
        && checkTopRight(matrix, i, j, pattern.topRight)
        && checkBottomRight(matrix, i, j, pattern.bottomRight)
        && checkBottomLeft(matrix, i, j, pattern.bottomLeft)
    }
}

fun checkTopLeft(matrix: Array<CharArray>, i: Int, j: Int, letter: Char) : Boolean {
    return matrix[i - 1][j - 1] == letter
}

fun checkTopRight(matrix: Array<CharArray>, i: Int, j: Int, letter: Char) : Boolean {
    return matrix[i - 1][j + 1] == letter
}

fun checkBottomLeft(matrix: Array<CharArray>, i: Int, j: Int, letter: Char) : Boolean {
    return matrix[i + 1][j - 1] == letter
}

fun checkBottomRight(matrix: Array<CharArray>, i: Int, j: Int, letter: Char) : Boolean {
    return matrix[i + 1][j + 1] == letter
}

fun applyAroundMatrix(matrix: Array<CharArray>, i: Int, j: Int) : Int {

    var isValidCount = 0

    aroundMatrix.forEach { pair ->
        val maxRowIndices = i + (pair.first*3)
        val maxColIndices = j + (pair.second*3)

        if(isInBound(matrix, maxRowIndices, maxColIndices)) {
            if(matrix[i+pair.first][j+pair.second] == 'M') {
                if(matrix[i+(pair.first*2)][j+(pair.second*2)] == 'A') {
                    if(matrix[i+(pair.first*3)][j+(pair.second*3)] == 'S') {
                        isValidCount++
                    }
                }
            }
        }
    }

    return isValidCount;
}

fun applyAroundMatrixPart2(matrix: Array<CharArray>, i: Int, j: Int) : Int {
    var isValidCount = 0



    return isValidCount;
}

fun checkOppositeLetter(matrix: Array<CharArray>, letter: Char, i: Int, j: Int, k: Int, l: Int) : Boolean {
    return matrix[i  + k * (-1)][j + l * (-1)] == letter
}

fun isInBound(matrix: Array<CharArray>, maxRowIndices : Int, maxColIndices : Int) : Boolean {
    return maxRowIndices < matrix.size
            && maxColIndices < matrix[0].size
            && maxRowIndices >= 0
            && maxColIndices >= 0
}

data class Pattern(val topLeft: Char, val topRight: Char, val bottomLeft: Char, val bottomRight: Char)