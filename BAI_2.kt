package com.example.baitap

import kotlin.math.PI

// ============================
// Bài 2
// ============================

// 2.1: Lớp
// 2.1.1, 2.1.2, 2.1.3
abstract class Dwelling {
    abstract val buildingMaterial: String
    abstract fun floorArea(): Double
}

// 2.1.4
open class RoundHut(
    val residents: Int,
    val radius: Double
) : Dwelling() {

    override val buildingMaterial: String = "Straw"

    override fun floorArea(): Double {
        return PI * radius * radius
    }

    open fun hasRoom(): Boolean {
        return residents < 4
    }

    open val capacity: Int = 4
}

// 2.1.5
class SquareCabin(
    val residents: Int,
    val length: Double,
    val floors: Int = 1
) : Dwelling() {

    override val buildingMaterial: String = "Wood"

    override fun floorArea(): Double {
        return length * length * floors
    }

    val capacity: Int = floors * 4

    fun hasRoom(): Boolean {
        return residents < capacity
    }
}

// ============================
// 2.2: Danh sách
// ============================

fun listExamples() {
    // 2.2.1
    val numbers = listOf(1, 2, 3, 4, 5, 6)

    // 2.2.2
    println("Size: ${numbers.size}")

    // 2.2.3
    println("First element: ${numbers[0]}")

    // 2.2.4
    println("Colors reversed: ${listOf("red", "blue", "green").reversed()}")

    // 2.2.5
    val entrees = mutableListOf<String>()

    // 2.2.6
    entrees.add("spaghetti")
    println(entrees)

    // 2.2.7
    entrees[0] = "lasagna"
    println(entrees)

    // 2.2.8
    entrees.remove("lasagna")
    println(entrees)
}

// ============================
// 2.3: Vòng lặp
// ============================

fun loopExamples() {
    val myList = listOf("A", "B", "C")

    // 2.3.1
    for (element in myList) {
        println(element)
    }

    // 2.3.2
    var index = 0
    while (index < myList.size) {
        println(myList[index])
        index++
    }
}

// ============================
// 2.4: Chuỗi
// ============================

fun stringExamples() {
    // 2.4.1
    val name = "Android"
    println(name.length)

    // 2.4.2
    val number = 10
    println("$number people")

    // 2.4.3
    val groups = 5
    println("${number * groups} people")
}

// ============================
// 2.5: Khác
// ============================

fun mathExamples() {
    // 2.5.1
    var a = 10
    var b = 5
    a += b
    a -= b
    a *= b
    a /= b
    println("Final a: $a")

    // 2.5.2
    val squareCabin = SquareCabin(residents = 3, length = 5.0, floors = 2)
    with(squareCabin) {
        println("Capacity: $capacity")
        println("Material: $buildingMaterial")
        println("Has room? ${hasRoom()}")
    }

    // 2.5.4
    val radius = 3.0
    println("Circle area: ${PI * radius * radius}")

    // 2.5.5
    val stringInTextField = "100".toString()
    println("Text field: $stringInTextField")
}

// 2.5.6
fun addToppings(vararg toppings: String) {
    for (topping in toppings) {
        println("Topping: $topping")
    }
}

// ============================
// Main
// ============================

fun main() {
    println("=== Bài 2: Danh sách ===")
    listExamples()

    println("\n=== Bài 2: Vòng lặp ===")
    loopExamples()

    println("\n=== Bài 2: Chuỗi ===")
    stringExamples()

    println("\n=== Bài 2: Khác ===")
    mathExamples()

    println("\n=== Bài 2: Toppings ===")
    addToppings("Cheese", "Tomato", "Mushroom")
}
