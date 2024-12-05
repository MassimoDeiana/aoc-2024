package day1

import FileUtils
import kotlin.math.abs


fun main() {

    val leftList = ArrayList<Int>()
    val rightList = ArrayList<Int>()

    FileUtils.readFile("day1.txt").forEachLine {
        val splitLine = it.split("   ")
        leftList.add(splitLine[0].toInt())
        rightList.add(splitLine[1].toInt())
    }

    leftList.sort()
    rightList.sort()

    println(part1(leftList, rightList))
    println(part2(leftList, rightList))

}

fun part1(leftList: List<Int>, rightList: List<Int>) =
    leftList
        .zip(rightList)
        .sumOf {
            pair -> abs(pair.first - pair.second)
        }

fun part2(leftList: List<Int>, rightList: List<Int>) =
    leftList.sumOf {
        left -> left * rightList.count {
            right -> left == right
        }
    }


