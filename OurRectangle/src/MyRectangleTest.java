public class MyRectangleTest
{
  public static void main(String[] args)
  {
    OurRectangle r1 = new OurRectangle(1,2,8,6);
    OurRectangle r2 = new OurRectangle(6,4,7,3);

    OurRectangle r3 = new OurRectangle(0, 0, 6, 3);
    OurRectangle r4 = new OurRectangle(3, 1, 5, 3);
    
    System.out.println("toString Test: " + (r1.toString().equals("base: (1,2) w:8 h:6")? "Passed" : "Failed"));

    System.out.println("Area Test: " + (r1.area()==48 ? "Passed" : "Failed"));

    System.out.println("Perimeter Test: " + (r1.perimeter()==28 ? "Passed" : "Failed"));

    System.out.println("Intersection Test: " + (OurRectangle.intersection(r1,r2).toString().equals("base: (6,4) w:3 h:3")?"Passed":"Failed"));

    System.out.println(OurRectangle.intersection(r4, r3).toString());

    System.out.println(OurRectangle.totalPerimeter(r3, r4));
    
  }
}