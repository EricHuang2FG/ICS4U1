class Main {
    public static void main(String[] args) {
        Circle c = new Circle(1);
        System.out.println("Circle area " + c.findArea());
        System.out.println("Circle perimeter " + c.findPerimeter());

        Triangle t = new Triangle(3,4,5);
        System.out.println("Triangle area " + t.findArea());
        System.out.println("Triangle perimeter " + t.findPerimeter());

        Rectangle r = new Rectangle(3,4);
        System.out.println("Rectangle area " + r.findArea());
        System.out.println("Rectangle perimeter " + r.findPerimeter());

        Square s = new Square(3);
        System.out.println("Square area " + s.findArea());
        System.out.println("Square perimeter " + s.findPerimeter());
    }
}