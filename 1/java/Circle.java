public class Circle extends Shape {
    public Circle(double radius) {
        super(2 * radius, 2 * radius);
    }

    public double getRadius() {
        return getWidth() / 2;
    }

    public double getArea() {
        return Math.PI * getRadius() * getRadius();
    }

    public double getCircumference() {
        return getWidth() * 3.14;
    }
}
