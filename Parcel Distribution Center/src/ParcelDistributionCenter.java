import javax.swing.*;
import java.awt.* ;
import java.util.Random;
import java.awt.event.*;

public class ParcelDistributionCenter extends JPanel {

    private static final int SCREEN_WIDTH = 1020;
    private static final int SCREEN_HEIGHT = 640;
    private Parcel[] parcels = new Parcel[20];
    private final int PARCEL_SPACING = 250;
    private int bottomOfParcel = (SCREEN_HEIGHT / 2) + 30;
    private Scanner scanner = new Scanner();
    private ControlledConveyorBelt leftBelt = new ControlledConveyorBelt("left", scanner);;
    private ConveyorBelt rightBelt = new ConveyorBelt("right", scanner);
    private ConveyorBelt topBelt = new ConveyorBelt("top", scanner);
    private ConveyorBelt bottomBelt = new ConveyorBelt("bottom", scanner);
    private ConveyorBelt[] conveyorBelts= {topBelt, bottomBelt, leftBelt, rightBelt};

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
            x -= PARCEL_SPACING;
        }
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                leftBelt.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        setFocusable(true);
    }

    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    private void moveParcel() {
        leftBelt.operate(parcels);
        for (Parcel parcel: parcels) {
            parcel.move();
        }
        scanner.parcelCollision(parcels);
        scanner.sortParcel(parcels);
        for (ConveyorBelt conveyorBelt: conveyorBelts) {
            conveyorBelt.parcelCollision(parcels);
            conveyorBelt.move();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Parcel Distribution Center");
        ParcelDistributionCenter window = new ParcelDistributionCenter();
        frame.add(window);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            window.moveParcel();
            window.repaint();
            Thread.sleep(10);
        }
    }

    private void drawBackground(Graphics2D g2d) {
        g2d.setColor(new Color(255, 255, 190));
        g2d.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawBackground(g2d);
        for (ConveyorBelt conveyorBelt: conveyorBelts) {
            conveyorBelt.paint(g2d);
        }
        scanner.paint(g2d, parcels);
    }
}
