package day2

import FileUtils
import kotlin.math.absoluteValue


fun main() {

    val reports = FileUtils.readFile("day2.txt")
        .readLines()
        .map {
            line -> line
                .split(" ")
                .map { it.toInt() }
        }

    println("Part 1: ${part1(reports)}")
    println("Part 2: ${part2(reports)}")

}

fun part1(reports : List<List<Int>>) =
    reports.count {
        report -> checkReportSafety(report)
    }

fun part2(reports: List<List<Int>>) =
    reports.count {
        report -> checkReportSafetyPartTwo(report)
    }

fun checkReportSafety(report: List<Int>) : Boolean {
    val differences = report.zipWithNext { a, b -> a - b }

    return differences.all {
        it.absoluteValue > 0
        && it.absoluteValue < 4
    } && (differences.all { it > 0 } || differences.all { it < 0 })
}

fun checkReportSafetyPartTwo(report: List<Int>) : Boolean {
    for( reportIndices in report.indices ) {
        var mutableReport = report.toMutableList()

        if(checkReportSafety(mutableReport)) return true

        mutableReport.removeAt(reportIndices)

        if(checkReportSafety(mutableReport)) return true
    }
    return false

}