public class OurRectangle {

    private int x, y, width, height;

    public OurRectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = Math.max(0, width);
        this.height = Math.max(0, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int area() {
        return width * height;
    }

    public int perimeter() {
        return 2 * width + 2 * height;
    }

    public static OurRectangle intersection(OurRectangle r1, OurRectangle r2) {
//        int[] bl1 = {r1.getX(), r1.getY()};
//        int[] tl1 = {r1.getX(), r1.getY() + r1.getHeight()};
//        int[] br1 = {r1.getX() + r1.getWidth(), r1.getY()};
//        int[] tr1 = {r1.getX() + r1.getWidth(), r1.getY() + r1.getHeight()};
//        int[] bl2 = {r2.getX(), r2.getY()};
//        int[] tl2 = {r2.getX(), r2.getY() + r2.getHeight()};
//        int[] br2 = {r2.getX() + r2.getWidth(), r2.getY()};
//        int[] tr2 = {r2.getX() + r2.getWidth(), r2.getY() + r2.getHeight()};
//        int[][] rect2 = {bl2, tl2, br2, tr2};
//        int count = 0;
        // do swapping!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if ((r1.getX() >= r2.getX() + r2.getWidth() || r2.getX() >= r2.getX() + r2.getWidth()) && (r1.getY() >= r2.getY() + r2.getHeight() || r2.getY() >= r2.getY() + r2.getHeight())) { // no intersection
            return new OurRectangle(0, 0, 0, 0);
        } else if ((r2.getX() > r1.getX() && r2.getX() < r1.getX() + r1.getWidth()) && (r2.getX() + r2.getWidth() > r1.getX() + r1.getWidth())) { // one intersection
            return new OurRectangle(r2.getX(), r2.getY(), r1.getX() + r1.getWidth() - r2.getX(), r1.getY() + r1.getHeight() - r2.getY());
        } else if ((r2.getX() + r2.getWidth() > r1.getX() && r2.getX() + r2.getWidth() < r1.getX() + r1.getWidth()) && (r2.getX() < r1.getX())) {
            return new OurRectangle(r1.getX(), r2.getY(), r2.getX() + r2.getWidth() - r1.getX(), r1.getY() + r1.getHeight() - r2.getY());
        }
        return new OurRectangle(0, 0, 0, 0);
    }

    public String toString() {
        return "base: (" + x + "," + y + ") w:" + width + " h:" + height;
    }
}
