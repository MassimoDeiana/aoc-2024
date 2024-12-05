package day3

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class day3Test {

    @Test
    fun testPart2() {
        val input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
        assertEquals(part2(input), 48)

    }

}