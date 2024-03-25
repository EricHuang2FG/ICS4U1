import java.awt.*;
import java.util.Random;

public class Parcel {

    private String type;
    private Color colour;
    private final int length, width, height;
    private int x, y;
    private int vx = 6, vy = 0;
    private final int nPoints = 5;
    private int[] polyFrontX = new int[nPoints];
    private int[] polyFrontY = new int[nPoints];
    private int[] polySideX = new int[nPoints];
    private int[] polySideY = new int[nPoints];
    private int[] polyTopX = new int[nPoints];
    private int[] polyTopY = new int[nPoints];

    public Parcel(String type, int length, int width, int height, int x, int y) {
        if (type.equals("international")) {
            this.colour = Color.BLUE;
        } else if (type.equals("domestic")) {
            this.colour = Color.GREEN;
        } else if (type.equals("unknown")) {
            this.colour = Color.YELLOW;
        }
        this.length = length;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public void move() {
        x += vx;
        y += vy;
    }

    public void paint(Graphics2D g2d) {
        double diagnol = (width * Math.sqrt(2)) / 2;
        polyFrontX[0] = x;
        polyFrontX[1] = x + length;
        polyFrontX[2] = x + length;
        polyFrontX[3] = x;
        polyFrontX[4] = x;
        polyFrontY[0] = y - height;
        polyFrontY[1] = y - height;
        polyFrontY[2] = y;
        polyFrontY[3] = y;
        polyFrontY[4] = y - height;

        polySideX[0] = (int) (x - diagnol);
        polySideX[1] = x;
        polySideX[2] = x;
        polySideX[3] = (int) (x - diagnol);
        polySideX[4] = (int) (x - diagnol);
        polySideY[0] = (int) (y - height - diagnol);
        polySideY[1] = y - height;
        polySideY[2] = y;
        polySideY[3] = (int) (y - diagnol);
        polySideY[4] = (int) (y - height - diagnol);

        polyTopX[0] = (int) (x - diagnol);
        polyTopX[1] = (int) (x - diagnol + length);
        polyTopX[2] = x + length;
        polyTopX[3] = x;
        polyTopX[4] = (int) (x - diagnol);
        polyTopY[0] = (int) (y - height - diagnol);
        polyTopY[1] = (int) (y - height - diagnol);
        polyTopY[2] = y - height;
        polyTopY[3] = y - height;
        polyTopY[4] = (int) (y - height - diagnol);

        g2d.setColor(colour);
        Polygon polyFront = new Polygon(polyFrontX, polyFrontY, nPoints);
        Polygon polySide = new Polygon(polySideX, polySideY, nPoints);
        Polygon polyTop = new Polygon(polyTopX, polyTopY, nPoints);
        g2d.fillPolygon(polyFront);
        g2d.fillPolygon(polySide);
        g2d.fillPolygon(polyTop);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawPolygon(polyFront);
        g2d.drawPolygon(polySide);
        g2d.drawPolygon(polyTop);
        g2d.setStroke(new BasicStroke(1));
    }
}
