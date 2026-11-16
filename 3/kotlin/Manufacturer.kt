enum class Manufacturer(private val displayName: String) {
    HONDA("Honda"), YAMAHA("Yamaha"), TOYOTA("Toyota"), SUZUKI("Suzuki");

    override fun toString(): String = displayName
}
