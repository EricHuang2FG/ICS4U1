public class Rectangle extends Shape {

    protected int length, width;
    public Rectangle(int l, int w) {
        this.length = l;
        this.width = w;
    }

    @Override
    public double findPerimeter() {
        return 2.0 * (length + width);
    }

    @Override
    public double findArea() {
        return 1.0 * length * width;
    }
}
