package com.example.baitap

import kotlinx.coroutines.*

// 4.1: Coroutine
// 4.1.1
suspend fun getValue(): Double {
    delay(500) // giả lập công việc lâu
    return Math.random() * 100
}

// 4.1.2
fun demoLaunch() {
    GlobalScope.launch {
        val output = getValue()
        println("Launch demo -> $output")
    }
}

// 4.1.3
suspend fun processValue() {
    val value = getValue()
    println("Processed value = ${value * 2}")
}

// 4.1.4 + 4.1.5
fun demoJob() {
    val job: Job = GlobalScope.launch {
        val output = getValue()
        println("Job demo -> $output")
    }
    runBlocking {
        delay(200) // chờ một chút
        println("Cancel job!")
        job.cancel()
    }
}

// 4.1.6
fun demoRunBlocking() = runBlocking {
    val output = getValue()
    println("RunBlocking demo -> $output")
}

// 4.1.7 (dùng async/await)
fun demoAsyncAwait() = runBlocking {
    val deferred: Deferred<Double> = async { getValue() }
    println("Async/Await demo -> ${deferred.await()}")
}

// 4.2: Khác
// 4.2.1
object DataProviderManager {
    fun provideData() = "Some data from DataProviderManager"
}

// 4.2.2
fun demoTryCatch() {
    try {
        val result = 10 / 0
        println("Result = $result")
    } catch (exception: Exception) {
        println("Caught exception: ${exception.message}")
    }
}

// 4.2.3
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

// 4.2.4 + 4.2.5
fun demoDirection() {
    val direction = Direction.NORTH
    when (direction) {
        Direction.NORTH -> println("Going north")
        Direction.SOUTH -> println("Going south")
        Direction.WEST -> println("Going west")
        Direction.EAST -> println("Going east")
    }
}

// MAIN
fun main() {
    println("=== Bài 4: Coroutine & Khác ===")

    // Coroutine demos
    demoLaunch()
    runBlocking { processValue() }
    demoJob()
    demoRunBlocking()
    demoAsyncAwait()

    // Others
    println(DataProviderManager.provideData())
    demoTryCatch()
    demoDirection()

}
