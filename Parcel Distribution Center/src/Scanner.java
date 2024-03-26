import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.sql.SQLOutput;

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
    private BufferedImage plane = null;
    private final double planeScale = 0.6;
    private int scaledPlaneWidth, scaledPlaneHeight;
    private BufferedImage truck = null;
    private final double truckScale = 0.04;
    private int scaledTruckWidth, scaledTruckHeight;
    private BufferedImage questionMark = null;
    private final double questionMarkScale = 0.19;
    private int scaledQuestionMarkWidth, scaledQuestionMarkHeight;
    private boolean displayPlane = false, displayTruck = false, displayQuestionMark = false;
    private final int imageX = 20, imageY = ParcelDistributionCenter.getScreenHeight() - 200;

    public Scanner() {
        try {
            plane = ImageIO.read(new File("res\\plane.png"));
            truck = ImageIO.read(new File("res\\truck.png"));
            questionMark = ImageIO.read(new File("res\\questionMark.png"));
        } catch (IOException e) {
            System.out.println("File loading error \n" + e);
        }
        this.scaledPlaneWidth = (int) (plane.getWidth() * planeScale);
        this.scaledPlaneHeight = (int) (plane.getHeight() * planeScale);
        this.scaledTruckWidth = (int) (truck.getWidth() * truckScale);
        this.scaledTruckHeight = (int) (truck.getHeight() * truckScale);
        this.scaledQuestionMarkWidth = (int) (questionMark.getWidth() * questionMarkScale);
        this.scaledQuestionMarkHeight = (int) (questionMark.getHeight() * questionMarkScale);
    }

    public void parcelCollision(Parcel[] parcels) {
        boolean isBroken = false;
        for (Parcel parcel: parcels) {
            if ((parcel.getX() + parcel.getLength() >= x && parcel.getX() <= x + totalLength) && (parcel.getY() <= y && parcel.getY() + parcel.getHeight() >= topEdge)) {
                turnOnLight = true;
                isBroken = true;
                if (parcel.isType("international")) {
                    displayPlane =  true;
                    displayTruck = false;
                    displayQuestionMark = false;
                } else if (parcel.isType("unknown")) {
                    displayPlane = false;
                    displayTruck = false;
                    displayQuestionMark = true;
                } else if (parcel.isType("domestic")) {
                    displayPlane = false;
                    displayTruck = true;
                    displayQuestionMark = false;
                }
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
                    parcel.setVy(-4);
                } else if (parcel.isType("unknown")) {
                    parcel.setVx(0);
                    parcel.setVy(4);
                }
                parcel.setSorted(true);
            }
        }
    }

    public void paint(Graphics2D g2d, Parcel[] parcels) {
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

        Polygon polyFront = new Polygon(polyFrontX, polyFrontY, nPoints);
        Polygon polySide = new Polygon(polySideX, polySideY, nPoints);
        Polygon polyTop = new Polygon(polyTopX, polyTopY, nPoints);
        g2d.setColor(Color.GRAY);
        g2d.fillPolygon(polySide);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawPolygon(polySide);
        g2d.setStroke(new BasicStroke(1));
        for (Parcel parcel: parcels) {
            parcel.paint(g2d);
        }
        g2d.setColor(Color.GRAY);
        g2d.fillPolygon(polyFront);
        g2d.fillPolygon(polyTop);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawPolygon(polyFront);
        g2d.drawPolygon(polyTop);
        g2d.setStroke(new BasicStroke(1));
        if (turnOnLight) {
            g2d.setColor(Color.RED);
            g2d.fillOval(x + 25, y - 80, 30, 30);
        }
        if (displayPlane) {
            g2d.drawImage(plane, imageX, imageY, scaledPlaneWidth, scaledPlaneHeight, null);
        }
        if (displayTruck) {
            g2d.drawImage(truck, imageX, imageY, scaledTruckWidth, scaledTruckHeight, null);
        }
        if (displayQuestionMark) {
            g2d.drawImage(questionMark, imageX, imageY, scaledQuestionMarkWidth, scaledQuestionMarkHeight, null);
        }
    }
}