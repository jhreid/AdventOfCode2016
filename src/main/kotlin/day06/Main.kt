package day06

import java.io.File

fun main() {
    val input = File("./day06.txt").readLines(Charsets.UTF_8)

    val chars = listOf(
        mutableMapOf(),
        mutableMapOf(),
        mutableMapOf(),
        mutableMapOf(),
        mutableMapOf(),
        mutableMapOf(),
        mutableMapOf(),
        mutableMapOf<Char, Int>())

    input.forEach { line ->
        line.forEachIndexed { index, c ->
            chars[index].merge( c, 1, Int::plus)
        }
    }

    val result = chars.map { m -> m.map { it.key to it.value }
        .sortedBy { it.second }.toMap().keys.take(1) }
        .map { if (it.isNotEmpty()) it.first() else "" }.joinToString("")
    println("$result")
}
