import java.time.Year
import java.util.Locale

abstract class Vehicle(
    number: String,
    val manufacturer: Manufacturer,
    val manufactureYear: Int,
    color: String,
    val owner: Owner
) {
    val number: String = normalizeNumber(number)
    val color: String = Owner.requireText(color, "Color")

    init {
        require(this.number.length == 5) { "Vehicle number must have exactly 5 characters." }
        require(manufactureYear > 2000 && manufactureYear <= Year.now().value) {
            "Manufacture year must be > 2000 and <= the current year."
        }
    }

    abstract val type: String
    protected abstract fun getDetails(): String

    override fun toString(): String =
        "$type | Number: $number | $manufacturer | Year: $manufactureYear | Color: $color | ${getDetails()} | Owner: $owner"

    companion object {
        fun normalizeNumber(number: String): String =
            Owner.requireText(number, "Vehicle number").uppercase(Locale.ROOT)

        fun requirePositive(value: Double, field: String): Double {
            require(value.isFinite() && value > 0) { "$field must be finite and > 0." }
            return value
        }
    }
}
