fun main() {
    val shape = Shape(4.0, 6.0)
    val rectangle = Rectangle(5.0, 8.0)
    val circle = Circle(3.0)

    println("Shape: width=%.2f, height=%.2f".format(shape.width, shape.height))
    println(
        "Rectangle: width=%.2f, height=%.2f, area=%.2f, perimeter=%.2f".format(
            rectangle.width, rectangle.height, rectangle.getArea(), rectangle.getPerimeter()
        )
    )
    println(
        "Circle: width=%.2f, height=%.2f, radius=%.2f, area=%.2f, circumference=%.2f".format(
            circle.width, circle.height, circle.radius, circle.getArea(), circle.getCircumference()
        )
    )
}
