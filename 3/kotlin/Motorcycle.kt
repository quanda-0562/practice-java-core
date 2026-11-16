class Motorcycle(
    number: String,
    manufacturer: Manufacturer,
    year: Int,
    color: String,
    owner: Owner,
    capacity: Double
) : Vehicle(number, manufacturer, year, color, owner) {
    val capacity: Double = requirePositive(capacity, "Capacity")
    override val type: String = "Motorcycle"
    override fun getDetails(): String = "Capacity (cc): $capacity"
}
