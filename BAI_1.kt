package com.example.baitap

// 1.1.4
val age = "5"
val name = "Rover"

// 1.1.7
fun printHello() {
    println("Hello Kotlin")
}

// 1.1.8
fun printBorder(border: String, timesToRepeat: Int) {
    repeat(timesToRepeat) {
        print(border)
    }
    println()
}

// 1.1.9
fun roll(): Int {
    return (1..6).random()
}

// 1.1.12
val diceRange = listOf(1, 2, 3, 4, 5, 6)
val globalRandomNumber = diceRange.random()

fun roll1() {
    val anotherRandomNumber = (1..6).random()
    println(globalRandomNumber)
}

// 1.2.1
fun laplai() {
    repeat(23) {
        print("cuong ")
    }
    println()
}

// 1.2.2
fun printCakeBottom(age: Int, layers: Int) {
    repeat(layers) {
        repeat(age + 2) {
            print("@")
        }
        println()
    }
}

// 1.2.3
fun ipseo() {
    val num = 4
    if (num > 4) {
        println("The variable is greater than 4")
    } else if (num == 4) {
        println("The variable is equal to 4")
    } else {
        println("The variable is less than 4")
    }
}

// 1.2.4
fun diceGame() {
    println("🎲 Welcome to the Dice Game!")

    print("Choose your lucky number (1 to 6): ")
    val luckyNumber = readLine()?.toIntOrNull()

    if (luckyNumber == null || luckyNumber !in 1..6) {
        println("❌ Invalid number. Please enter a number from 1 to 6.")
        return
    }

    playDiceGame(luckyNumber)
}

fun playDiceGame(luckyNumber: Int) {
    val rollResult = (1..6).random()
    println("Lucky number: $luckyNumber")
    println("You rolled: $rollResult")

    when (rollResult) {
        luckyNumber -> println("🎉 You won!")
        else -> println("Try again, unlucky this time!")
    }
}

// 1.2.5
fun diceDrawableDemo() {
    val diceRoll = (1..6).random()
    val drawableResource = when (diceRoll) {
        1 -> "🎲 Dice 1"
        2 -> "🎲 Dice 2"
        3 -> "🎲 Dice 3"
        4 -> "🎲 Dice 4"
        5 -> "🎲 Dice 5"
        else -> "🎲 Dice 6"
    }
    println("Drawable resource: $drawableResource")
}

// 1.3.2
class Dice(val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}

fun main() {
    println("Hello, world!")
    println("This is the text to print!")

    println("You are already $age!")
    println("You are already $age days old, $name!")

    printHello()
    printBorder("Cuong", 10)

    println("You rolled a ${roll()}")
    roll1()

    laplai()
    printCakeBottom(3, 3)
    ipseo()
    diceGame()
    diceDrawableDemo()

    val myFirstDice = Dice(6)
    println("My dice rolled: ${myFirstDice.roll()}")
}
