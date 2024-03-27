import java.awt.*;

public class ConveyorBelt {

    protected String orientation;
    protected int x, y;
    protected int length, height;
    protected Scanner scanner;
    protected boolean move = false;
    protected Color lightColor = Color.RED;
    protected int lightRadius = 20;

    public ConveyorBelt(String orientation, Scanner scanner) {
        this.scanner = scanner;
        this.orientation = orientation;
        if (orientation.equals("left")) {
            this.length = (int) (scanner.getX() + scanner.getDiagnol());
            this.height = (int) (scanner.getDiagnol());
            this.x = 0;
            this.y = scanner.getY() - this.height;
        } else if (orientation.equals("right")) {
            this.length = (int) (ParcelDistributionCenter.getScreenWidth() - (scanner.getX() + scanner.getLength() - scanner.getDiagnol()));
            this.height = height = (int) (scanner.getDiagnol());
            this.x = scanner.getX() + scanner.getLength();
            this.y = scanner.getY() - this.height;
        } else if (orientation.equals("top")) {
            this.length = scanner.getLength() - 90;
            this.height = scanner.getY() - scanner.getHeight();
            this.x = scanner.getX() + 30;
            this.y = 0;
        } else if (orientation.equals("bottom")) {
            this.length = scanner.getLength() - 90;
            this.height = ParcelDistributionCenter.getScreenHeight() - scanner.getY();
            this.x = (int) (scanner.getX() + scanner.getDiagnol());
            this.y = scanner.getY();
        }
    }

    public void parcelCollision(Parcel[] parcels) {
        boolean isBroken = false;
        for (Parcel parcel: parcels) {
            if (orientation.equals("left") || orientation.equals("right")) {
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

    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);
        g2d.fillRect(x, y, length, height);
        g2d.setColor(lightColor);
        g2d.fillOval(x + 20, y + 20, lightRadius, lightRadius);
    }
}