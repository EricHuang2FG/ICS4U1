import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;

public class Car {

    private BufferedImage car = null;
    private double scale = 0.02;
    private int x, y;
    private int vx = 3;
    private int width, height;
    private Cityscape screen;

    public Car(int x, int y, Cityscape screen) {
        this.y = y;
        this.x = x;
        this.screen = screen;
        try {
            car = ImageIO.read(new File("res\\car.png"));
        } catch (IOException e) {
            System.out.println("File loading error " + e);
        }
        this.width = (int) -(scale * car.getWidth());
        this.height = (int) (scale * car.getHeight());
    }

    public void screenCollision() {
        if (vx > 0 && x + Math.abs(width) >= screen.getWidth()) {
            vx *= -1;
            width *= -1;
            x -= Math.abs(width);
        }
        if (vx < 0 && x + Math.abs(width) <= 0) {
            vx *= -1;
            width *= -1;
            x += Math.abs(width);
        }
    }

    public void move() {
        screenCollision();
        y = screen.getHeight() - screen.getRoadHeight() - screen.getCarDistanceToTopOfRoad();
        x += vx;
    }

    public void paint(Graphics2D g2d) {
        g2d.drawImage(car, x + Math.abs(width), y, width, height, null);
    }
}