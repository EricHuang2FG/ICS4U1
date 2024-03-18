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
    private UFO[] ufoList = new UFO[5];
    private int maxBuildingHeight = 0;
    private int[] usedX = new int[5];
    private int[] usedY = new int[5];

    public Cityscape() {
        Random rand = new Random();
        int x = buildingSpacing;
        for (int i = 0; i < buildings.length; i++) {
            int buildingHeight = rand.nextInt((550 - 200) + 1) + 200;
            if (buildingHeight > this.maxBuildingHeight) {
                this.maxBuildingHeight = buildingHeight;
            }
            buildings[i] = new Building(x, 0, buildingWidth, buildingHeight);
            x += buildingWidth + buildingSpacing;
        }
        for (int i = 0; i < ufoList.length; i++) {
            int ufoX = rand.nextInt(((screenWidth - UFO.getBodyWidth())) + 1);
            int ufoY = rand.nextInt((screenHeight - maxBuildingHeight - 50 - UFO.getBodyHeight()));
            while (invalidPosition(ufoX, ufoY)) {
                ufoX = rand.nextInt(((screenWidth - UFO.getBodyWidth())) + 1);
                ufoY = rand.nextInt((screenHeight - maxBuildingHeight - 50 - UFO.getBodyHeight()));
            }
            usedX[i] = ufoX;
            usedY[i] = ufoY;
            ufoList[i] = new UFO(maxBuildingHeight, this, ufoX, ufoY);
        }
    }

    private boolean invalidPosition(int x, int y) {
        for (int i = 0; i < usedX.length; i++) {
            int xVal = usedX[i];
            int yVal = usedY[i];
            if (((x > xVal && x < xVal + UFO.getBodyWidth()) || (xVal > x && xVal < x + UFO.getBodyWidth())) && ((y > yVal && y < yVal + UFO.getBodyHeight()) || (yVal > y && yVal < y + UFO.getBodyHeight()))) {
                return true;
            }
        }
        return false;
    }

    private void moveUFO() {
        for (int i = 0; i < ufoList.length; i++) {
            for (int j = i + 1; j < ufoList.length; j++) {
                ufoList[i].ufoCollision(ufoList[j]);
            }
        }
        for (UFO ufo: ufoList) {
            ufo.move();
        }
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
        for (UFO ufo: ufoList) {
            ufo.paint(g2d);
        }
    }
}