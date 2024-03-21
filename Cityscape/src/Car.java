import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;

public class Car {

    private BufferedImage car = null;
    private double scale = 0.02;
    private int x, y;
    private int vx = 3, vy = 0;
    private int width, height;
    private Cityscape screen;
    private boolean abductCar = false;
    private int counter = 0;

    public Car(int x, int y, Cityscape screen) {
        this.y = y;
        this.screen = screen;
        try {
            car = ImageIO.read(new File("res\\car.png"));
        } catch (IOException e) {
            System.out.println("File loading error " + e);
        }
        this.width = (int) -(scale * car.getWidth());
        this.height = (int) (scale * car.getHeight());
        this.x = x - Math.abs(width);
    }

    public int getX() {
        if (width > 0) {
            return x;
        } else {
            return x + width;
        }
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return Math.abs(width);
    }

    public int getHeight() {
        return height;
    }

    public void setAbductCar(boolean condition) {
        abductCar = condition;
    }

    public void screenCollision() {
        if (vx > 0 && x >= screen.getWidth()) {
            vx *= -1;
            width *= -1;
            x -= Math.abs(width);
        }
        if (vx < 0 && x <= 0) {
            vx *= -1;
            width *= -1;
            x += Math.abs(width);
        }
    }

    private void abductCar(PlayerUFO player) {
        vy = -3;
        vx = player.getVx();
    }

    private void fall() {
        vy = 3;
        vx = 0;
    }

    public void move(PlayerUFO player) {
        if (counter == 0) {
            y = screen.getHeight() - screen.getRoadHeight() - screen.getCarDistanceToTopOfRoad();
            counter = 1;
        }
        screenCollision();
        if (abductCar) {
            abductCar(player);
        } else {
            if (y != screen.getHeight() - screen.getRoadHeight() - screen.getCarDistanceToTopOfRoad()) {
                fall();
            } else {
                if (width < 0) {
                    vx = 3;
                } else {
                    vx = -3;
                }
                vy = 0;
            }
        }
        y += vy;
        x += vx;
    }

    public void paint(Graphics2D g2d) {
//        g2d.drawImage(car, x + Math.abs(width), y, width, height, null);
//        g2d.setColor(Color.RED);
//        if (width > 0) {
//            g2d.drawRect(x, y, width, height);
//        } else {
//            g2d.drawRect(x + width, y, Math.abs(width), height);
//        }
        g2d.drawImage(car, x, y, width, height, null);

    }
}