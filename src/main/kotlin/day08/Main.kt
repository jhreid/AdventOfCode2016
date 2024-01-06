package day08

import java.io.File

fun main() {
    val input = File("./day08.txt").readLines(Charsets.UTF_8)

    val screen = Array(6) { Array(50) { '.' } }
    val rectDigits = """.* (\d+)x(\d+)""".toRegex()
    val rotateDigits = """.*=(\d+) by (\d+)""".toRegex()

    input.forEach { instruction ->
        when {
            instruction.startsWith("rect") -> {
                val matchResult = rectDigits.find(instruction)!!
                val (xStr, yStr) = matchResult.destructured
                val x = xStr.toInt() - 1
                val y = yStr.toInt() - 1

                for (i in 0 .. y) {
                    for (j in 0 .. x) {
                        screen[i][j] = '#'
                    }
                }
            }
            instruction.startsWith("rotate row") -> {
                val matchResult = rotateDigits.find(instruction)!!
                val (indexStr, amountStr) = matchResult.destructured
                val index = indexStr.toInt()
                val amount = amountStr.toInt()

                screen[index] = screen[index].slice(50 - amount..49).toTypedArray() +
                        screen[index].slice(0..49 - amount).toTypedArray()
            }
            instruction.startsWith("rotate column") -> {
                val matchResult = rotateDigits.find(instruction)!!
                val (indexStr, amountStr) = matchResult.destructured
                val index = indexStr.toInt()
                val amount = amountStr.toInt()
                var col = CharArray(6) { '.' }

                for (i in 0 .. 5) {
                    col[i] = screen[i][index]
                }
                col = col.slice(6 - amount .. 5).toCharArray() + col.slice(0 .. 5 - amount)
                for (i in 0 .. 5) {
                    screen[i][index] = col[i]
                }

            }
        }
    }

    val totalLit = screen.flatten().fold(0) { acc, next -> acc + if (next == '#') 1 else 0 }
    println(totalLit)

    screen.forEach { println(it.joinToString("")) }
}
