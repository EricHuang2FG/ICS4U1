import java.awt.*;

public class Scanner {
    
    private final int length = 140, height = 150, width = 40;
    private final int x = ParcelDistributionCenter.getScreenWidth() / 2 - (length / 2), y = ParcelDistributionCenter.getScreenHeight() / 2 + (height / 2);
    private final int nPoints = 5;
    private int[] polyFrontX = new int[nPoints];
    private int[] polyFrontY = new int[nPoints];
    private int[] polySideX = new int[nPoints];
    private int[] polySideY = new int[nPoints];
    private int[] polyTopX = new int[nPoints];
    private int[] polyTopY = new int[nPoints];
    private double diagnol = (width * Math.sqrt(2)) / 2;
    private int leftEdge = (int) (x - diagnol);
    private int topEdge = (int) (y - diagnol - height);
    private int totalLength = (int) (length + diagnol);
    private int totalHeight = (int) (height + diagnol);
    private boolean turnOnLight = false;

    public Scanner() {

    }

    public void parcelCollision(Parcel[] parcels) {
        boolean isBroken = false;
        for (Parcel parcel: parcels) {
            if ((parcel.getX() + parcel.getLength() >= x && parcel.getX() <= x + totalLength) && (parcel.getY() <= y && parcel.getY() + parcel.getHeight() >= topEdge)) {
                turnOnLight = true;
                isBroken = true;
                break;
            } 
        }
        if (!isBroken) {
            turnOnLight = false;
        }
    }

    public void sortParcel(Parcel[] parcels) {
        for (Parcel parcel: parcels) {
            if (!parcel.getSortSituation() && parcel.getX() >= x + 25) {
                if (parcel.isType("international")) {
                    parcel.setVx(0);
                    parcel.setVy(-5);
                } else if (parcel.isType("unknown")) {
                    parcel.setVx(0);
                    parcel.setVy(5);
                }
                parcel.setSorted(true);
            }
        }
    }

    public void paint(Graphics2D g2d) {
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

        g2d.setColor(Color.GRAY);
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
        if (turnOnLight) {
            g2d.setColor(Color.RED);
            g2d.fillOval(x + 25, y - 80, 30, 30);
        }
    }
}