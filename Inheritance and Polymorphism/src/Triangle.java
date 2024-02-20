public class Triangle extends Shape {

    private int a, b, c;
    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double findPerimeter() {
        return a + b + c;
    }

    @Override
    public double findArea() {
        double semiPerimeter = findPerimeter() / 2.0;
        return Math.sqrt(semiPerimeter * (semiPerimeter - a) * (semiPerimeter - b) * (semiPerimeter - c));
    }
}
