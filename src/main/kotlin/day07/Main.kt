package day07

import java.io.File

fun main() {
    val input = File("./day07.txt").readLines(Charsets.UTF_8)
    val abbaBrackets = """.*\[\w*(?!(\w)\1\1\1)(\w)(\w)\3\2\w*].*""".toRegex()
    val abba = """.*(?!(\w)\1\1\1)(\w)(\w)\3\2.*""".toRegex()

    val aba = """(\w)(\w)\1""".toRegex()

    val supportsTLS = input.filter { line ->
        val parts = line.split("""\[\w*]""".toRegex())
        val joined = parts.joinToString(" ")
        abba.matches(joined)
    }.filter { line ->
        !abbaBrackets.matches(line)
    }

    println(supportsTLS.size)

    val supportsSSL = input.filter { line ->
        val parts = line.split("""\[\w*]""".toRegex())
        var match = false
        parts.forEach { part ->
            part.windowed(3, 1).forEach { chunk ->
                if (aba.matches(chunk)) {
                    val bab = """.*\[\w*${chunk[1]}${chunk[0]}${chunk[1]}.*""".toRegex()
                    if (bab.matches(line)) {
                        match = true
                    }
                }
            }
        }
        match
    }

    println(supportsSSL.size)

}
