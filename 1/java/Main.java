public class Main {
    public static void main(String[] args) {
        Shape shape = new Shape(4, 6);
        Rectangle rectangle = new Rectangle(5, 8);
        Circle circle = new Circle(3);

        System.out.printf("Shape: width=%.2f, height=%.2f%n",
                shape.getWidth(), shape.getHeight());
        System.out.printf("Rectangle: width=%.2f, height=%.2f, area=%.2f, perimeter=%.2f%n",
                rectangle.getWidth(), rectangle.getHeight(),
                rectangle.getArea(), rectangle.getPerimeter());
        System.out.printf("Circle: width=%.2f, height=%.2f, radius=%.2f, area=%.2f, circumference=%.2f%n",
                circle.getWidth(), circle.getHeight(), circle.getRadius(),
                circle.getArea(), circle.getCircumference());
    }
}
