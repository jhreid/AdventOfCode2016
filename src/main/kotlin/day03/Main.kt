package day03

import java.io.File

fun main() {
    val input = File("./day03.txt").readLines(Charsets.UTF_8)

    val valid = input.filter { line ->
        val sides = line.trim().split("\\s+".toRegex()).map { it.toInt() }
        val (one, two, three) = Triple(sides[0], sides[1], sides[2])

        one + two > three && one + three > two && two + three > one
    }

    println("Part 1 valid: ${valid.size}")

    val valid1 = input.map { line ->
        val sides = line.trim().split("\\s+".toRegex()).map { it.toInt() }
        sides[0]
    }.chunked(3).filter { chunk ->
        val (one, two, three) = Triple(chunk[0], chunk[1], chunk[2])
        one + two > three && one + three > two && two + three > one
    }.size
    val valid2 = input.map { line ->
        val sides = line.trim().split("\\s+".toRegex()).map { it.toInt() }
        sides[1]
    }.chunked(3).filter { chunk ->
        val (one, two, three) = Triple(chunk[0], chunk[1], chunk[2])
        one + two > three && one + three > two && two + three > one
    }.size
    val valid3 = input.map { line ->
        val sides = line.trim().split("\\s+".toRegex()).map { it.toInt() }
        sides[2]
    }.chunked(3).filter { chunk ->
        val (one, two, three) = Triple(chunk[0], chunk[1], chunk[2])
        one + two > three && one + three > two && two + three > one
    }.size
    println("Part 2 valid ${valid1 + valid2 + valid3}")
}
