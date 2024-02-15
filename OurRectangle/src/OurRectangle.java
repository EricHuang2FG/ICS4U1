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

    public String toString() {
        return "base: (" + x + "," + y + ") w:" + width + " h:" + height;
    }

    public boolean contains(OurRectangle b) {
        return (x <= b.getX()) && (x + width >= b.getX() + b.getWidth()) && (y <= b.getY()) && (y + height >= b.getY() + b.getHeight());
    }

    public static OurRectangle intersection(OurRectangle r1, OurRectangle r2) {
        OurRectangle left = new OurRectangle(r1.getX(), r1.getY(), r1.getWidth(), r1.getHeight());
        OurRectangle right = new OurRectangle(r2.getX(), r2.getY(), r2.getWidth(), r2.getHeight());

        if (r1.getX() > r2.getX()) { // swap left and right if necessary 
            OurRectangle tempLeft = left;
            left = right;   
            right = tempLeft;       
        } 

        int lx = left.getX();
        int ly = left.getY();
        int lw = left.getWidth();
        int lh = left.getHeight();
        int rx = right.getX();
        int ry = right.getY();
        int rw = right.getWidth();
        int rh = right.getHeight();

        if (lx == rx && ly == ry && lw == rw && lh == rh) { // they are the same triangle
            return left;
        } else if (lx + lw < rx) { // the two rectangles don't touch
            return new OurRectangle(0, 0, 0, 0);
        } else if (lx + lw == rx && ly + lh == ry) { // touch in top right corner
            return new OurRectangle(rx, ry, 0, 0);
        } else if (lx + lw == rx && ly == ry + rh) { // touch in bottom right corner
            return new OurRectangle(rx, ly, 0, 0);
        } else if (lx + lw == rx) { // right edge touch
            return new OurRectangle(rx, Math.max(ry, ly), 0, Math.min(ry + rh, ly + lh) - Math.max(ry, ly));
        } else if (ly + lh == ry) { // top edge touch
            return new OurRectangle(rx, ry, Math.min(lx + lw, rx + rw) - rx, 0);
        } else if (ly == ry + rh) { // bottom edge touch
            return new OurRectangle(rx, ry + rh, Math.min(lx + lw, rx + rw) - rx, 0);
        } else { // they intersect either once, twice, or is contained
            return new OurRectangle(rx, Math.max(ly, ry), Math.min(lx + lw, rx + rw) - rx, Math.min(ly + lh, ry + rh) - Math.max(ly, ry));
        }
    }

    public static int totalPerimeter(OurRectangle r1, OurRectangle r2) {
        return 0;
    }
}