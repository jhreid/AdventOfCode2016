package day01

import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {
    val input = File("./day01.txt").readText(Charsets.UTF_8)

    val visitedPoints = mutableMapOf<Point, Int>()
    var lastPoint = Point(0, 0)

    var direction = 0 // N = 0, E = 1, S = 2, W = 3
    var east = 0
    var north = 0
    input.split(",").forEach {
        val (turn, distance) = Pair (it.trim()[0], it.trim().substring(1).toInt())

        when(turn) {
            'L' -> direction -= 1
            'R' -> direction += 1
        }
        direction = direction.mod(4)
        var currentPoint = Point(0, 0)
        when(direction) {
            0 -> {
                north += distance
                for (i in 1 .. distance) {
                    currentPoint = Point(lastPoint.x, lastPoint.y + i)
                    if (visitedPoints.contains(currentPoint)) {
                        println("Visiting again: $currentPoint")
                    }
                    visitedPoints.merge(currentPoint, 1, Int::plus)
                }
            }
            1 -> {
                east += distance
                for (i in 1 .. distance) {
                    currentPoint = Point(lastPoint.x + i, lastPoint.y)
                    if (visitedPoints.contains(currentPoint)) {
                        println("Visiting again: $currentPoint")
                    }
                    visitedPoints.merge(currentPoint, 1, Int::plus)
                }
            }
            2 -> {
                north -= distance
                for (i in 1 .. distance) {
                    currentPoint = Point(lastPoint.x, lastPoint.y - i)
                    if (visitedPoints.contains(currentPoint)) {
                        println("Visiting again: $currentPoint")
                    }
                    visitedPoints.merge(currentPoint, 1, Int::plus)
                }
            }
            3 -> {
                east -= distance
                for (i in 1 .. distance) {
                    currentPoint = Point(lastPoint.x - i, lastPoint.y)
                    if (visitedPoints.contains(currentPoint)) {
                        println("Visiting again: $currentPoint")
                    }
                    visitedPoints.merge(currentPoint, 1, Int::plus)
                }
            }
        }
        lastPoint = currentPoint
    }
    east = abs(east)
    north = abs(north)
    println("East $east, North $north or ${north + east} blocks away")
}

data class Point(val x: Int, val y: Int)