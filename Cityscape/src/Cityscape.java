import javax.swing.*;
import java.awt.* ;
import java.util.Random;
public class Cityscape extends JPanel {

    private static int screenWidth = 1020;
    private static int screenHeight= 900;
    private int buildingWidth = 130;
    private int buildingSpacing = 30;
    private Building[] buildings = new Building[screenWidth / (5 + buildingWidth + buildingSpacing)];
    private StarSky starSky = new StarSky(screenWidth, screenHeight);
    private UFO ufo1;
    private int maxBuildingHeight = 0;

    public Cityscape() {
        Random rand = new Random();
        int x = buildingSpacing;
        for (int i = 0; i < buildings.length; i++) {
            int buildingHeight = rand.nextInt((600 - 200) + 1) + 200;
            if (buildingHeight > this.maxBuildingHeight) {
                this.maxBuildingHeight = buildingHeight;
            }
            buildings[i] = new Building(x, 0, buildingWidth, buildingHeight);
            x += buildingWidth + buildingSpacing;
        }
        ufo1 = new UFO(maxBuildingHeight, this);
    }

    private void moveUFO() {
        ufo1.move();
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Cityscape");
        Cityscape window = new Cityscape();
        frame.add(window);
        frame.setSize(screenWidth, screenHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            window.moveUFO();
            window.repaint();
            Thread.sleep(10);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        starSky.paint(g2d);
        for (Building b: buildings) {
            b.paint(g2d);
        }
        ufo1.paint(g2d);
    }
}