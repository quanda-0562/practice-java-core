import java.time.LocalDate

abstract class Goods(code: String, name: String, val quantity: Int, val unitPrice: Double) {
    val code: String = requireText(code, "Product code")
    val name: String = requireText(name, "Product name")

    init {
        require(quantity >= 0) { "Quantity must be >= 0." }
        require(unitPrice.isFinite() && unitPrice >= 0) { "Unit price must be finite and >= 0." }
    }

    abstract val type: String
    abstract val vatRate: Double
    val vatAmount: Double
        get() = quantity * unitPrice * vatRate

    abstract fun evaluateConsumption(today: LocalDate): String
    protected abstract fun getDetails(): String

    fun getInformation(today: LocalDate): String =
        "%s | %s | %s | Quantity: %d | Unit price: %.2f | VAT: %.2f | %s | %s".format(
            type, code, name, quantity, unitPrice, vatAmount, getDetails(), evaluateConsumption(today)
        )

    companion object {
        fun requireText(value: String, field: String): String {
            require(value.isNotBlank()) { "$field must not be blank." }
            return value.trim()
        }
    }
}
