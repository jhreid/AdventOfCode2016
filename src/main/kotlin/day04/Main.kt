package day04

import java.io.File

fun main() {
    val input = File("./day04.txt").readLines(Charsets.UTF_8)

    val regex = """([a-z\-]+)(\d+)\[([a-z]+)\]""".toRegex()
    var sectorSum = 0
    input.forEach { line ->
        val matchResult = regex.find(line)!!
        val(name, sector, checksum) = matchResult.destructured
        val nameCount = mutableMapOf<String, Int>()

        name.filter { c -> c != '-' }.map { c -> nameCount.merge(c.toString(), 1, Int::plus)}
        val common = nameCount.map { it.key to it.value }.sortedBy { it.first + it.second.toString() }
            .sortedByDescending { it.second }.toMap().keys.take(5).joinToString("")

        if (common.equals(checksum)) {
            sectorSum += sector.toInt()
            val decoded = name.map { c -> if (c == '-') ' ' else (((c.code - 'a'.code + sector.toInt()) % 26) + 'a'.code).toChar() }.joinToString("")
            if (decoded.equals("northpole object storage ")) println("$decoded $sector")
        }
    }
    println("$sectorSum")
}
