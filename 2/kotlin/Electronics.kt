import java.time.LocalDate

class Electronics(
    code: String,
    name: String,
    quantity: Int,
    unitPrice: Double,
    val warrantyMonths: Int,
    val capacityKw: Double
) : Goods(code, name, quantity, unitPrice) {
    init {
        require(warrantyMonths >= 0) { "Warranty months must be >= 0." }
        require(capacityKw.isFinite() && capacityKw >= 0) { "Capacity must be finite and >= 0." }
    }

    override val type: String = "Electronics"
    override val vatRate: Double = 0.10

    override fun evaluateConsumption(today: LocalDate): String =
        if (quantity < 3) "Selling well" else "Not evaluated"

    override fun getDetails(): String = "Warranty months: $warrantyMonths | Capacity (kW): $capacityKw"
}
