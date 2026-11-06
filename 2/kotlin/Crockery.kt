import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Crockery(
    code: String,
    name: String,
    quantity: Int,
    unitPrice: Double,
    manufacturer: String,
    val arrivalDate: LocalDate
) : Goods(code, name, quantity, unitPrice) {
    val manufacturer: String = requireText(manufacturer, "Manufacturer")
    override val type: String = "Crockery"
    override val vatRate: Double = 0.10

    override fun evaluateConsumption(today: LocalDate): String =
        if (quantity > 50 && ChronoUnit.DAYS.between(arrivalDate, today) > 10) {
            "Slow sale"
        } else {
            "Not evaluated"
        }

    override fun getDetails(): String = "Manufacturer: $manufacturer | Arrived: $arrivalDate"
}
