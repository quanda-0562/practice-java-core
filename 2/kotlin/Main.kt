import java.time.LocalDate
import java.time.format.DateTimeParseException
import java.util.NoSuchElementException
import java.util.Scanner

private val input = Scanner(System.`in`)

fun main() {
    val inventory = DSHH()
    try {
        while (true) {
            println("\n1. Add goods\n2. Display goods and consumption\n3. Inventory and VAT summary\n0. Exit")
            when (readInt("Choose: ")) {
                1 -> {
                    try {
                        val item = readGoods()
                        if (item != null) {
                            println(if (inventory.add(item)) "Added successfully."
                                else "Duplicate product code. Goods were not added.")
                        }
                    } catch (exception: IllegalArgumentException) {
                        println("Cannot add goods: ${exception.message}")
                    }
                }
                2 -> inventory.displayAll(LocalDate.now())
                3 -> inventory.displaySummary()
                0 -> return
                else -> println("Invalid choice.")
            }
        }
    } catch (exception: NoSuchElementException) {
        println("\nInput closed.")
    }
}

private fun readGoods(): Goods? {
    println("1. Food\n2. Electronics\n3. Crockery")
    val type = readInt("Goods type: ")
    if (type !in 1..3) {
        println("Invalid goods type.")
        return null
    }
    val code = readText("Product code: ")
    val name = readText("Product name: ")
    val quantity = readInt("Inventory quantity (>= 0): ")
    val price = readDouble("Unit price (>= 0): ")
    return when (type) {
        1 -> Food(code, name, quantity, price,
            readDate("Manufacture date"), readDate("Expiration date"), readText("Supplier: "))
        2 -> Electronics(code, name, quantity, price,
            readInt("Warranty months (>= 0): "), readDouble("Capacity kW (>= 0): "))
        else -> Crockery(code, name, quantity, price,
            readText("Manufacturer: "), readDate("Arrival date"))
    }
}

private fun readText(prompt: String): String {
    print(prompt)
    return input.nextLine().trim()
}

private fun readInt(prompt: String): Int {
    while (true) {
        val value = readText(prompt).toIntOrNull()
        if (value != null) return value
        println("Enter a valid integer.")
    }
}

private fun readDouble(prompt: String): Double {
    while (true) {
        val value = readText(prompt).toDoubleOrNull()
        if (value != null && value.isFinite()) return value
        println("Enter a finite number (use a decimal point).")
    }
}

private fun readDate(prompt: String): LocalDate {
    while (true) {
        try {
            return LocalDate.parse(readText("$prompt (yyyy-MM-dd): "))
        } catch (exception: DateTimeParseException) {
            println("Enter a valid date, e.g. 2026-11-03.")
        }
    }
}
