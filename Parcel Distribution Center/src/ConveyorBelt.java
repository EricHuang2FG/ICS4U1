import java.awt.*;

public class ConveyorBelt {

    protected Color colour = new Color(168, 178, 187);
    protected String orientation;
    protected int x, y;
    protected int length, height;
    protected int sideHeight = 25;
    protected Scanner scanner;
    protected boolean move = false;
    protected boolean stopped = false;
    protected Color lightColor = Color.RED;
    protected int lightRadius = 20;
    protected Color beltColourA = new Color(198, 187, 187);
    protected Color beltColourB = new Color(209, 204, 204);
    protected int beltThickness = 40;
    protected static final int NUM_BELTS = 30;
    protected BeltLine[] beltLines = new BeltLine[NUM_BELTS];
    protected BeltLine[] backwardBelts = new BeltLine[NUM_BELTS];

    public ConveyorBelt(String orientation, Scanner scanner) {
        this.scanner = scanner;
        this.orientation = orientation;
        if (orientation.equals("left")) {
            this.length = (int) (scanner.getX() + scanner.getDiagonal());
            this.height = (int) (scanner.getDiagonal());
            this.x = 0;
            this.y = scanner.getY() - this.height;
            int initialX = this.x + this.length;
            for (int i = 0; i < beltLines.length; i++) {
                if (i % 2 == 0) {
                    beltLines[i] = new BeltLine(initialX, this.y + (this.height / 2), beltThickness, "right", beltColourA, this); // (x, y) is on the tip
                } else {
                    beltLines[i] = new BeltLine(initialX, this.y + (this.height / 2), beltThickness, "right", beltColourB, this);
                }
                initialX -= beltThickness;
            }
            initialX = this.x;
            for (int i = 0; i < backwardBelts.length; i++) {
                backwardBelts[i] = new BeltLine(initialX, this.y + this.height + this.sideHeight, beltThickness, "back", beltColourA, this);
                initialX += beltThickness;
            }
        } else if (orientation.equals("right")) {
            this.length = (int) (ParcelDistributionCenter.getScreenWidth() - (scanner.getX() + scanner.getLength() - scanner.getDiagonal()));
            this.height = height = (int) (scanner.getDiagonal());
            this.x = scanner.getX() + scanner.getLength();
            this.y = scanner.getY() - this.height;
            int initialX = this.x + this.length;
            for (int i = 0; i < beltLines.length; i++) {
                if (i % 2 == 0) {
                    beltLines[i] = new BeltLine(initialX, this.y + (this.height / 2), beltThickness, "right", beltColourA, this); // x is on the tip
                } else {
                    beltLines[i] = new BeltLine(initialX, this.y + (this.height / 2), beltThickness, "right", beltColourB, this);
                }
                initialX -= beltThickness;
            }
            initialX = this.x;
            for (int i = 0; i < backwardBelts.length; i++) {
                backwardBelts[i] = new BeltLine(initialX, this.y + this.height + this.sideHeight, beltThickness, "back", beltColourA, this);
                initialX += beltThickness;
            }
        } else if (orientation.equals("top")) {
            this.length = scanner.getLength() - 90;
            this.height = scanner.getY() - scanner.getHeight();
            this.x = scanner.getX() + 30;
            this.y = 0;
            int initialY = this.y - beltThickness;
            for (int i = 0; i < beltLines.length; i++) {
                if (i % 2 == 0) {
                    beltLines[i] = new BeltLine(this.x + (this.length / 2), initialY, beltThickness, "up", beltColourA, this); // (x, y) is the tip
                } else {
                    beltLines[i] = new BeltLine(this.x + (this.length / 2), initialY, beltThickness, "up", beltColourB, this);
                }
                initialY += beltThickness;
            }
            initialY = this.y + this.height;
            for (int i = 0; i < backwardBelts.length; i++) {
                backwardBelts[i] = new BeltLine(this.x - (this.sideHeight / 2), initialY, beltThickness, "up back", beltColourA, this);
                initialY -= beltThickness;
            }
        } else if (orientation.equals("bottom")) {
            this.length = scanner.getLength() - 90;
            this.height = ParcelDistributionCenter.getScreenHeight() - scanner.getY();
            this.x = (int) (scanner.getX() + scanner.getDiagonal());
            this.y = scanner.getY();
            int initialY = this.y + this.height;
            for (int i = 0; i < beltLines.length; i++) {
                if (i % 2 == 0) {
                    beltLines[i] = new BeltLine(this.x + (this.length / 2), initialY, beltThickness, "down", beltColourA, this);
                } else {
                    beltLines[i] = new BeltLine(this.x + (this.length / 2), initialY, beltThickness, "down", beltColourB, this);
                }
                initialY -= beltThickness;
            }
            initialY = this.y;
            for (int i = 0; i < backwardBelts.length; i++) {
                backwardBelts[i] = new BeltLine(this.x - (this.sideHeight / 2), initialY, beltThickness, "down back", beltColourA, this);
                initialY += beltThickness;
            }
        }
    }

    public static int getNumBelts() {
        return NUM_BELTS;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void parcelCollision(Parcel[] parcels) {
        boolean isBroken = false;
        for (Parcel parcel: parcels) {
            if (orientation.equals("left")) {
                if (!stopped) {
                    lightColor = Color.GREEN;
                    move = true;
                    isBroken = true;
                    break;
                }
            } else if (orientation.equals("right")) {
                if ((parcel.getX() + parcel.getLength() >= x) && (parcel.getX() <= x + length)) {
                    lightColor = Color.GREEN;
                    move = true;
                    isBroken = true;
                    break;
                }
            } else {
                if ((parcel.getY() <= y + height) && (parcel.getY() + parcel.getHeight() >= y)) {
                    lightColor = Color.GREEN;
                    move = true;
                    isBroken = true;
                    break;
                }
            }
        }
        if (!isBroken) {
            move = false;
            lightColor = Color.RED; 
        }
    }

    public void move() {
        if (move) {
            for (BeltLine beltLine: beltLines) {
                beltLine.move();
            }
            for (BeltLine backwardBeltLine: backwardBelts) {
                backwardBeltLine.move();
            }
        }
    }

    public void paint(Graphics2D g2d) {
        g2d.setColor(colour);
        g2d.fillRect(x, y, length, height);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        if (orientation.equals("left") || orientation.equals("right")) {
            g2d.drawLine(x, y, (int) (x + length - scanner.getDiagonal()), y);
            g2d.drawLine(x, y + height, x + length, y + height);
            g2d.setColor(colour);
            if (orientation.equals("left")) {
                int offSet = 10;
                g2d.fillRect(x, y + height, length - offSet, sideHeight);
                g2d.setColor(Color.BLACK);
                g2d.drawLine(x, y + height + sideHeight, x + length - offSet, y + height + sideHeight);
                g2d.setStroke(new BasicStroke(5));
                g2d.drawOval(x + length - offSet - (sideHeight / 2), y + height, sideHeight, sideHeight);
                g2d.setColor(beltColourA);
                g2d.fillOval(x + length - offSet - (sideHeight / 2), y + height, sideHeight, sideHeight);
                g2d.setStroke(new BasicStroke(1));
            } else {
                int offSet = -22;
                g2d.fillRect(x + offSet, y + height, length - offSet, sideHeight);
                g2d.setColor(Color.BLACK);
                g2d.drawLine(x + offSet, y + height + sideHeight, x + length - offSet, y + height + sideHeight);
                g2d.setStroke(new BasicStroke(5));
                g2d.drawOval(x + offSet - (sideHeight / 2), y + height, sideHeight, sideHeight);
                g2d.setColor(beltColourA);
                g2d.fillOval(x + offSet - (sideHeight / 2), y + height, sideHeight, sideHeight);
                g2d.setStroke(new BasicStroke(1));
            }
            
        } else if (orientation.equals("top") || orientation.equals("bottom")) {
            g2d.drawLine(x, y, x, y + height);
            g2d.drawLine(x + length, y, x + length, y + height);
            g2d.setColor(colour);
            g2d.fillRect(x - (sideHeight / 2), y, (sideHeight / 2), height);
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.BLACK);
            g2d.drawLine(x - (sideHeight / 2), y, x - (sideHeight / 2), y + height);
            g2d.setStroke(new BasicStroke(1));
        }
        for (BeltLine beltLine: beltLines) {
            beltLine.paint(g2d);
        }
        for (BeltLine backwardBelts: backwardBelts) {
            backwardBelts.paint(g2d);
        }
        int[] lightDimensions = {x + 20, y + 25, lightRadius};
        g2d.setColor(lightColor);
        g2d.fillOval(lightDimensions[0], lightDimensions[1], lightDimensions[2], lightDimensions[2]);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(lightDimensions[0], lightDimensions[1], lightDimensions[2], lightDimensions[2]);
        g2d.setStroke(new BasicStroke(1));
    }
}