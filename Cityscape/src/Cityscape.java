import javax.swing.*;
import java.awt.* ;
import java.util.Random;
public class Cityscape extends JPanel {

    private static int screenWidth = 1020;
    private static int screenHeight= 900;
    private int buildingWidth = 130;
    private int buildingSpacing = 30;
    private Building[] buildings = new Building[screenWidth / (5 + buildingWidth + buildingSpacing)];

    public Cityscape() {
        Random rand = new Random();
        int x = buildingSpacing;
        for (int i = 0; i < buildings.length; i++) {
            int buildingHeight = rand.nextInt((600 - 200) + 1) + 200;
            buildings[i] = new Building(x, 0, buildingWidth, buildingHeight);
            x += buildingWidth + buildingSpacing;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cityscape");
        Cityscape window = new Cityscape();
        frame.add(window);
        frame.setSize(screenWidth, screenHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, screenWidth, screenHeight);
        for (Building b: buildings) {
            b.paint(g2d);
        }
    }
}
