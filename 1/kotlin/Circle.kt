class Circle(radius: Double) : Shape(2 * radius, 2 * radius) {
    val radius: Double
        get() = width / 2

    fun getArea(): Double = Math.PI * radius * radius

    fun getCircumference(): Double = width * 3.14
}
