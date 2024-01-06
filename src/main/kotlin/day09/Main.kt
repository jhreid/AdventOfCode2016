package day09

import java.io.File

fun main() {
    val input = File("./day09.txt").readText(Charsets.UTF_8)

    var index = 0
    var length = 0
    while (index != -1) {
        val startIndex = input.indexOf('(', index)
        if (index == 0) {
            length += startIndex
        }
        val closingIndex = input.indexOf(')', startIndex)
        val xIndex = input.indexOf('x', startIndex)
        val dataLength = input.substring(startIndex + 1, xIndex).toInt()
        val repeat = input.substring(xIndex + 1, closingIndex).toInt()
        length += dataLength * repeat
        index = input.indexOf('(', closingIndex + dataLength)
        length += if (index == -1) {
            input.length - (closingIndex + 1 + dataLength)
        } else {
            index - (closingIndex + 1 + dataLength)
        }
    }

    println(length)

    part2()
}

fun part2(input: String) {
    var index = 0
    var length = 0
    while (index != -1) {
        val startIndex = input.indexOf('(', index)
        if (index == 0) {
            length += startIndex
        }
        val closingIndex = input.indexOf(')', startIndex)
        val xIndex = input.indexOf('x', startIndex)
        val dataLength = input.substring(startIndex + 1, xIndex).toInt()
        val repeat = input.substring(xIndex + 1, closingIndex).toInt()
        length += dataLength * repeat
        index = input.indexOf('(', closingIndex + dataLength)
        length += if (index == -1) {
            input.length - (closingIndex + 1 + dataLength)
        } else {
            index - (closingIndex + 1 + dataLength)
        }
    }

    println(length)
}