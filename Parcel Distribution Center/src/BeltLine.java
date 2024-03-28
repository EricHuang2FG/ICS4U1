import java.awt.*;

public class BeltLine {

    private int x, y;
    private String direction;
    private int nPoints = 7;
    private int[] xCoords = new int[nPoints], yCoords = new int[nPoints];
    private int vx, vy;
    private Color beltColour;
    private int beltThickness;
    private int convex = 12;
    private ConveyorBelt motherBelt;

    public BeltLine(int x, int y, int thickness, String direction, Color colour, ConveyorBelt motherBelt) {
        this.x = x;
        this.y = y;
        this.beltThickness = thickness;
        this.beltColour = colour;
        this.motherBelt = motherBelt;
        this.direction = direction;
        if (direction.equals("right")) {
            vx = Parcel.getTargetSpeed();
            vy = 0;
        } else if (direction.equals("up")) {
            vx = 0;
            vy = -Parcel.getTargetSpeed();
        } else if (direction.equals("down")) {
            vx = 0;
            vy = Parcel.getTargetSpeed();
        }
    }

    public void move() {
        if (direction.equals("right") && x - convex - beltThickness >= motherBelt.getX() + motherBelt.getLength()) {
            x -= ConveyorBelt.getNumBelts() * beltThickness;
        } else if (direction.equals("up") && y + convex + beltThickness <= motherBelt.getY()) {
            y += ConveyorBelt.getNumBelts() * beltThickness;
        } else if (direction.equals("down") && y - convex - beltThickness >= motherBelt.getY() + motherBelt.getHeight()) {
            y -= ConveyorBelt.getNumBelts() * beltThickness;
        }
        x += vx;
        y += vy;
    }

    public void paint(Graphics2D g2d) {
        if (direction.equals("right")) {
            int augmentedConvexA = convex + 27;
            int augmentedConvexB = 1;
            xCoords[0] = x;
            xCoords[1] = x - augmentedConvexB;
            xCoords[2] = x - augmentedConvexB - beltThickness;
            xCoords[3] = x - beltThickness;
            xCoords[4] = x - augmentedConvexA - beltThickness;
            xCoords[5] = x - augmentedConvexA;
            xCoords[6] = x;
            yCoords[0] = y;
            yCoords[1] = y + (motherBelt.getHeight() / 2);
            yCoords[2] = y + (motherBelt.getHeight() / 2);
            yCoords[3] = y;
            yCoords[4] = y - (motherBelt.getHeight() / 2);
            yCoords[5] = y - (motherBelt.getHeight() / 2);
            yCoords[6] = y;
        } else if (direction.equals("up")) {
            xCoords[0] = x;
            xCoords[1] = x + (motherBelt.getLength() / 2);
            xCoords[2] = x + (motherBelt.getLength() / 2);
            xCoords[3] = x;
            xCoords[4] = x - (motherBelt.getLength() / 2);
            xCoords[5] = x - (motherBelt.getLength() / 2);
            xCoords[6] = x;
            yCoords[0] = y;
            yCoords[1] = y + convex;
            yCoords[2] = y + convex + beltThickness;
            yCoords[3] = y + beltThickness;
            yCoords[4] = y + convex + beltThickness;
            yCoords[5] = y + convex;
            yCoords[6] = y;
        } else if (direction.equals("down")) {
            xCoords[0] = x;
            xCoords[1] = x + (motherBelt.getLength() / 2);
            xCoords[2] = x + (motherBelt.getLength() / 2);
            xCoords[3] = x;
            xCoords[4] = x - (motherBelt.getLength() / 2);
            xCoords[5] = x - (motherBelt.getLength() / 2);
            xCoords[6] = x;
            yCoords[0] = y;
            yCoords[1] = y - convex;
            yCoords[2] = y - convex - beltThickness;
            yCoords[3] = y - beltThickness;
            yCoords[4] = y - convex - beltThickness;
            yCoords[5] = y - convex;
            yCoords[6] = y;
        }
        Polygon belt = new Polygon(xCoords, yCoords, nPoints);
        g2d.setColor(beltColour);
        g2d.setStroke(new BasicStroke(2));
        if (direction.equals("right") && x >= motherBelt.getX()) {
            g2d.fillPolygon(belt);
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(belt);
        } else if (direction.equals("up") && y <= motherBelt.getY() + motherBelt.getHeight()) {
            g2d.fillPolygon(belt);
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(belt);
        } else if (direction.equals("down") && y >= motherBelt.getY()) {
            g2d.fillPolygon(belt);
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(belt);
        }
        g2d.setStroke(new BasicStroke(1));
    }
}