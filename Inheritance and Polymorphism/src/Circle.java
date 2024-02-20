public class Circle extends Shape {

    private int r;
    public Circle(int r) {
        this.r = r;
    }

    @Override
    public double findPerimeter() {
        return 2 * Math.PI * r;
    }

    @Override
    public double findArea() {
        return Math.PI * (r * r);
    }
}
