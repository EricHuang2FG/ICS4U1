public class Point3D extends Point {

    private double z;

    public Point3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    @Override
    public String toString() {
        return "(" + super.getX() + ", " + super.getY() + ", " + z + ")";
    }

    public static Point3D midpoint(Point3D p1, Point3D p2) {
        double x1 = p1.getX();
        double x2 = p2.getX();
        double y1 = p1.getY();
        double y2 = p2.getY();
        double z1 = p1.z;
        double z2 = p2.z;
        return new Point3D((x1 + x2) / 2, (y1 + y2) / 2, (z1 + z2) / 2);
    }
}