package com.example.baitap

fun main() {
    // 3.1: Nhóm
    val numbers = listOf(0, 3, 8, 4, 0, 5, 5, 8, 9, 2)
    val setOfNumbers = numbers.toSet()
    println("Set of numbers: $setOfNumbers")

    val set1 = setOf(1, 2, 3)
    val set2 = mutableSetOf(3, 4, 5)

    println("Intersection: ${set1.intersect(set2)}")
    println("Union: ${set1.union(set2)}")

    // 3.2: Sơ đồ (Map)
    val peopleAges = mutableMapOf<String, Int>(
        "Fred" to 30,
        "Ann" to 23
    )
    peopleAges.put("Barbara", 42)
    peopleAges["Joe"] = 51

    // 3.3: Phép toán trên tập hợp
    print("ForEach: ")
    peopleAges.forEach { print("${it.key} is ${it.value}, ") }
    println()

    println(
        "Map + joinToString: " +
                peopleAges.map { "${it.key} is ${it.value}" }.joinToString(", ")
    )

    val filteredNames = peopleAges.filter { it.key.length < 4 }
    println("Filtered names (length < 4): $filteredNames")

    val words = listOf("about", "acute", "balloon", "best", "brief", "class")
    val filteredWords = words.filter { it.startsWith("b", ignoreCase = true) }
        .shuffled()
        .take(2)
        .sorted()
    println("Filtered words: $filteredWords")

    // 3.5: Khác
    // 3.5.1 & 3.5.2 chỉ chạy được trong Android nên bỏ qua
    // 3.5.3: Lambda function
    val triple: (Int) -> Int = { a: Int -> a * 3 }
    println("Triple of 5 = ${triple(5)}")

    // 3.5.6: lateinit & mutableList
    val wordsList: MutableList<String> = mutableListOf("apple", "banana", "cherry")
    lateinit var currentWord: String
    currentWord = "grape"
    println("Words list = $wordsList, currentWord = $currentWord")

    // 3.5.7: Toán tử Elvis (?:)
    var quantity: Int? = null
    println("Quantity when null = ${quantity ?: 0}")
    quantity = 4
    println("Quantity when not null = ${quantity ?: 0}")

    // Demo kết thúc
}

/* ======================
   Các phần dành cho Android
======================

// 3.4.1: Let
arguments?.let {
    letterId = it.getString(LETTER).toString()
}

// 3.4.2: apply
binding?.apply {
    flavorFragment = this@FlavorFragment
}

// 3.5.1
private var _currentScrambledWord = "test"
val currentScrambledWord: String
    get() = _currentScrambledWord

// 3.5.2
val letterId = intent?.extras?.getString("letter").toString()

// 3.5.4
class DetailActivity: AppCompatActivity() {
    companion object {
        const val LETTER = "letter"
    }
}
// Accessible outside DetailActivity
DetailActivity.LETTER

// 3.5.5 (property delegation)
private val viewModel: GameViewModel by viewModels()

====================== */
