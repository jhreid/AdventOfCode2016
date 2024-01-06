package day05

import java.math.BigInteger
import java.security.MessageDigest

fun main() {
    val md = MessageDigest.getInstance("MD5")
    var hash = ""
    var index = 0
    val password = "uqwqemis".map {
        hash = ""
        while (!hash.startsWith("00000")) {
            hash = BigInteger(1, md.digest("uqwqemis$index".toByteArray())).toString(16).padStart(32, '0')
            index++
        }
        index++
        hash[5]
    }

    println(password.joinToString(""))

    val password2 = mutableListOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')
    hash = ""
    index = 0
    var positionsUsed = mutableListOf<Int>()
    while (password2.contains(' ')) {
        while (!hash.startsWith("00000")) {
            hash = BigInteger(1, md.digest("uqwqemis$index".toByteArray())).toString(16).padStart(32, '0')
            index++
        }
        val position = hash[5]
        if (position.isDigit() && position.digitToInt() <=7 && position.digitToInt() >= 0 && !positionsUsed.contains(position.digitToInt())) {
            password2[position.digitToInt()] = hash[6]
            positionsUsed.add(position.digitToInt())
            println("$position $hash ${password2.joinToString("")}")
        }
        index++
        hash = ""
    }

    println("${password2.joinToString("")}")
}