import java.util.NoSuchElementException
import java.util.Scanner

private val input = Scanner(System.`in`)

fun main() {
    val manager = VehicleManager()
    try {
        while (true) {
            println("\n1. Add vehicle\n2. Search by vehicle number\n3. Search by owner ID"
                + "\n4. Delete by manufacturer\n5. Manufacturers with most vehicles"
                + "\n6. Sort by vehicle number descending\n7. Statistics by vehicle type\n0. Exit")
            try {
                when (readInt("Choose: ")) {
                    1 -> {
                        manager.add(readVehicle(manager))
                        println("Added successfully.")
                    }
                    2 -> println(manager.findByNumber(readText("Vehicle number: ")) ?: "No vehicle found.")
                    3 -> display(manager.findByOwnerId(readText("Owner ID: ")))
                    4 -> println("Deleted vehicles: ${manager.deleteByManufacturer(readManufacturer())}")
                    5 -> {
                        val top = manager.getMostCommonManufacturers()
                        if (top.isEmpty()) println("No vehicles managed.")
                        else {
                            val counts = manager.countByManufacturer()
                            for (manufacturer in top) println("$manufacturer: ${counts.getValue(manufacturer)}")
                        }
                    }
                    6 -> display(manager.sortByNumberDescending())
                    7 -> manager.countByType().forEach { (type, count) -> println("$type: $count") }
                    0 -> return
                    else -> println("Invalid choice.")
                }
            } catch (exception: IllegalArgumentException) {
                println("Error: ${exception.message}")
            }
        }
    } catch (exception: NoSuchElementException) {
        println("\nInput closed.")
    }
}

private fun readVehicle(manager: VehicleManager): Vehicle {
    println("1. Car\n2. Motorcycle\n3. Truck")
    val type = readInt("Vehicle type: ")
    require(type in 1..3) { "Invalid vehicle type." }
    val number = readText("Vehicle number (5 characters): ")
    val manufacturer = readManufacturer()
    val year = readInt("Manufacture year: ")
    val color = readText("Color: ")
    val id = readText("Owner ID (12 digits): ")
    val existing = manager.findOwnerById(id)
    val owner = if (existing == null) {
        Owner(id, readText("Full name: "), readText("Email: "))
    } else {
        println("Using existing owner: $existing")
        existing
    }
    return when (type) {
        1 -> Car(number, manufacturer, year, color, owner, readInt("Number of seats: "), readText("Engine type: "))
        2 -> Motorcycle(number, manufacturer, year, color, owner, readDouble("Capacity (cc): "))
        else -> Truck(number, manufacturer, year, color, owner, readDouble("Tonnage (tons): "))
    }
}

private fun readManufacturer(): Manufacturer {
    val manufacturers = Manufacturer.entries
    manufacturers.forEachIndexed { index, manufacturer -> println("${index + 1}. $manufacturer") }
    val choice = readInt("Manufacturer: ")
    require(choice in 1..manufacturers.size) { "Invalid manufacturer." }
    return manufacturers[choice - 1]
}

private fun display(vehicles: List<Vehicle>) {
    if (vehicles.isEmpty()) println("No vehicles found.")
    for (vehicle in vehicles) println(vehicle)
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
