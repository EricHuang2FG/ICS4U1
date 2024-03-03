public class Main {
    public static void main(String[] args) {
        Point3D point1 = new Point3D(0, 3, 4);
        Point3D point2 = new Point3D(1, 2, 5);
        Point3D midpoint = Point3D.midpoint(point1, point2);
        System.out.println(midpoint.toString());
    }
}
