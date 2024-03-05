import javax.swing.*;
import java.awt.* ;
public class Cityscape extends JPanel {

    private Building[] buildings = new Building[6];
    private static int screenWidth = 1020;
    private static int screenHeight= 900;

    public Cityscape() {
        buildings[0] = new Building(5, 0, 100, 200);
        buildings[1] = new Building(125, 0, 100, 100);
        buildings[2] = new Building(250, 0, 100, 500);
        buildings[3] = new Building(375, 0, 100, 400);
        buildings[4] = new Building(500, 0, 100, 100);
        buildings[5] = new Building(625, 0, 100, 300);
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
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, screenWidth, screenHeight);
        for (Building b: buildings) {
            b.paint(g2d);
        }
    }
}
