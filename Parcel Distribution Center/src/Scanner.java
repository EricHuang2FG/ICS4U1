import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;

public class Scanner {
    
    private int length = 170, height = 150, width = 80;
    private final int x = ParcelDistributionCenter.getScreenWidth() / 2 - (length / 2), y = ParcelDistributionCenter.getScreenHeight() / 2 + (height / 2) - 30;
    private final int nPoints = 5;
    private int[] polyFrontX = new int[nPoints];
    private int[] polyFrontY = new int[nPoints];
    private int[] polySideX = new int[nPoints];
    private int[] polySideY = new int[nPoints];
    private int[] polyTopX = new int[nPoints];
    private int[] polyTopY = new int[nPoints];
    private double diagonal = (width * Math.sqrt(2)) / 2;
    private int leftEdge = (int) (x - diagonal);
    private int topEdge = (int) (y - diagonal - height);
    private int totalLength = (int) (length + diagonal);
    private int totalHeight = (int) (height + diagonal);
    private boolean turnOnLight = false;
    private int lightRadius = 30;
    private BufferedImage plane = null;
    private double planeScale = 0.6;
    private int scaledPlaneWidth, scaledPlaneHeight;
    private BufferedImage truck = null;
    private double truckScale = 0.04;
    private int scaledTruckWidth, scaledTruckHeight;
    private BufferedImage questionMark = null;
    private double questionMarkScale = 0.19;
    private int scaledQuestionMarkWidth, scaledQuestionMarkHeight;
    private boolean displayPlane = false, displayTruck = false, displayQuestionMark = false;
    private int imageX = 20, imageY = ParcelDistributionCenter.getScreenHeight() - 200;

    public Scanner() {
        try {
            plane = ImageIO.read(new File("res\\plane.png"));
            truck = ImageIO.read(new File("res\\truck.png"));
            questionMark = ImageIO.read(new File("res\\questionMark.png"));
//           plane = ImageIO.read(new File("../res/plane.png"));
//           truck = ImageIO.read(new File("../res/truck.png"));
//           questionMark = ImageIO.read(new File("../res/questionMark.png"));
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

    public double getDiagonal() {
        return diagonal;
    }

    public int getX() {
        return leftEdge;
    }

    public int getY() {
        return y;
    }

    public int getLength() {
        return totalLength;
    }

    public int getHeight() {
        return totalHeight;
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
            if (!parcel.getSortSituation() && parcel.getX() >= x + 20) {
                if (parcel.isType("international")) {
                    parcel.setVx(0);
                    parcel.setVy(-Parcel.getTargetSpeed());
                } else if (parcel.isType("unknown")) {
                    parcel.setVx(0);
                    parcel.setVy(Parcel.getTargetSpeed());
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

        polySideX[0] = (int) (x - diagonal);
        polySideX[1] = x;
        polySideX[2] = x;
        polySideX[3] = (int) (x - diagonal);
        polySideX[4] = (int) (x - diagonal);
        polySideY[0] = (int) (y - height - diagonal);
        polySideY[1] = y - height;
        polySideY[2] = y;
        polySideY[3] = (int) (y - diagonal);
        polySideY[4] = (int) (y - height - diagonal);

        polyTopX[0] = (int) (x - diagonal);
        polyTopX[1] = (int) (x - diagonal + length);
        polyTopX[2] = x + length;
        polyTopX[3] = x;
        polyTopX[4] = (int) (x - diagonal);
        polyTopY[0] = (int) (y - height - diagonal);
        polyTopY[1] = (int) (y - height - diagonal);
        polyTopY[2] = y - height;
        polyTopY[3] = y - height;
        polyTopY[4] = (int) (y - height - diagonal);

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
        int[] lightDimensions = {x + 25, y - 70, lightRadius};
        if (turnOnLight) {
            g2d.setColor(Color.RED);
            g2d.fillOval(lightDimensions[0], lightDimensions[1], lightDimensions[2], lightDimensions[2]);
        }
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(lightDimensions[0], lightDimensions[1], lightDimensions[2], lightDimensions[2]);
        g2d.setStroke(new BasicStroke(1));
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