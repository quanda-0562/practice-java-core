import java.time.LocalDate

class Food(
    code: String,
    name: String,
    quantity: Int,
    unitPrice: Double,
    val manufactureDate: LocalDate,
    val expirationDate: LocalDate,
    supplier: String
) : Goods(code, name, quantity, unitPrice) {
    val supplier: String = requireText(supplier, "Supplier")

    init {
        require(!expirationDate.isBefore(manufactureDate)) {
            "Expiration date must be on or after manufacture date."
        }
    }

    override val type: String = "Food"
    override val vatRate: Double = 0.05

    override fun evaluateConsumption(today: LocalDate): String =
        if (quantity > 0 && expirationDate.isBefore(today)) "Hard to sell" else "Not evaluated"

    override fun getDetails(): String =
        "Manufactured: $manufactureDate | Expires: $expirationDate | Supplier: $supplier"
}
