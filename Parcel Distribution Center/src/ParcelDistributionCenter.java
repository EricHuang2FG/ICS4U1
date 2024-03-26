import javax.swing.*;
import java.awt.* ;
import java.util.Random;
import java.awt.event.*;

public class ParcelDistributionCenter extends JPanel {

    private static final int screenWidth = 1090;
    private static final int screenHeight = 640;
    private Parcel[] parcels = new Parcel[20];
    private final int parcelSpacing = 200;
    private final int bottomOfParcel = screenHeight / 2;
    private Scanner scanner = new Scanner();

    public ParcelDistributionCenter() {
        Random rand = new Random();
        int x = 0;
        for (int i = 0; i < parcels.length; i++) {
            int length = rand.nextInt(20, 51);
            int width = rand.nextInt(10, 51);
            int height = rand.nextInt(20, 51);
            if (i == 0) {
                x -= length;
            }
            int typeInt = rand.nextInt(1, 4);
            String type;
            if (typeInt == 1) {
                type = "international";
            } else if (typeInt == 2) {
                type = "domestic";
            } else {
                type = "unknown";
            }
            parcels[i] = new Parcel(type, length, width, height, x, bottomOfParcel);
            x -= parcelSpacing;
        }
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    private void moveParcel() {
        for (Parcel parcel: parcels) {
            parcel.move();
        }
        scanner.parcelCollision(parcels);
        scanner.sortParcel(parcels);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Parcel Distribution Center");
        ParcelDistributionCenter window = new ParcelDistributionCenter();
        frame.add(window);
        frame.setSize(screenWidth, screenHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            window.moveParcel();
            window.repaint();
            Thread.sleep(10);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Parcel parcel: parcels) {
            parcel.paint(g2d);
        }
        scanner.paint(g2d);
    }
}
