package day4

import kotlin.test.Test
import kotlin.test.assertEquals

class day4Test {

    @Test
    fun part1Test() {

        val matrix = FileUtils
            .readFile("day4test.txt")
            .readLines()
            .map { it.toCharArray() }
            .toTypedArray()

        assertEquals(18, part1(matrix))

    }

    @Test
    fun part2Test() {
        val matrix = FileUtils
            .readFile("day4test.txt")
            .readLines()
            .map { it.toCharArray() }
            .toTypedArray()

        assertEquals(9, part2(matrix))
    }

}