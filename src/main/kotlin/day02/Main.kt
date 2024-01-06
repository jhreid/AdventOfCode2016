package day02

import java.io.File

fun main() {
    val input = File("./day02.txt").readLines(Charsets.UTF_8)

    val keypad = arrayOf(
        arrayOf('x', 'x', 'x', 'x', 'x', 'x', 'x'),
        arrayOf('x', 'x', 'x', '1', 'x', 'x', 'x'),
        arrayOf('x', 'x', '2', '3', '4', 'x', 'x'),
        arrayOf('x', '5', '6', '7', '8', '9', 'x'),
        arrayOf('x', 'x', 'A', 'B', 'C', 'x', 'x'),
        arrayOf('x', 'x', 'x', 'D', 'x', 'x', 'x'),
        arrayOf('x', 'x', 'x', 'x', 'x', 'x', 'x')
        )
    var current = Point(1, 3)

    input.forEach { line ->
        line.forEach {
            when (it) {
                'U' -> {
                    if (keypad[current.y - 1][current.x] != 'x') current = Point(current.x, current.y - 1)
                }

                'D' -> {
                    if (keypad[current.y + 1][current.x] != 'x') current = Point(current.x, current.y + 1)
                }

                'L' -> {
                    if (keypad[current.y][current.x - 1] != 'x') current = Point(current.x - 1, current.y)
                }

                'R' -> {
                    if (keypad[current.y][current.x + 1] != 'x') current = Point(current.x + 1, current.y)
                }
            }
        }
        println("Number ${keypad[current.y][current.x]}")
    }
}

data class Point(val x: Int, val y: Int)
