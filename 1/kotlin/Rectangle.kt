class Rectangle(width: Double, height: Double) : Shape(width, height) {
    fun getArea(): Double = width * height

    fun getPerimeter(): Double = 2 * (width + height)
}
