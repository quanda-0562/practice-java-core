class Car(
    number: String,
    manufacturer: Manufacturer,
    year: Int,
    color: String,
    owner: Owner,
    val seats: Int,
    engineType: String
) : Vehicle(number, manufacturer, year, color, owner) {
    val engineType: String = Owner.requireText(engineType, "Engine type")

    init {
        require(seats > 0) { "Seats must be > 0." }
    }

    override val type: String = "Car"
    override fun getDetails(): String = "Seats: $seats | Engine: $engineType"
}
