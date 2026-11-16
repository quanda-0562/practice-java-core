class Truck(
    number: String,
    manufacturer: Manufacturer,
    year: Int,
    color: String,
    owner: Owner,
    tonnage: Double
) : Vehicle(number, manufacturer, year, color, owner) {
    val tonnage: Double = requirePositive(tonnage, "Tonnage")
    override val type: String = "Truck"
    override fun getDetails(): String = "Tonnage (tons): $tonnage"
}
