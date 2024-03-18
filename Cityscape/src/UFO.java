import java.awt.*;
import javax.swing.*;

public class UFO {

    private int x, y;
    private int vx = 2, vy = 1;
//    private int vx = 200, vy = 100;
    private static final int bodyWidth = 125, bodyHeight = 50;
    private static final int cockpitWidth = 40, cockpitHeight = 33;
    private static final int lightRadius = 15;
    private int lowerBound;
    private int coolDown = 0;
    private Cityscape screen;

    public UFO (int tallestBuildingHeight, Cityscape screen, int x, int y) {
        this.screen = screen;
        this.lowerBound = tallestBuildingHeight + 30;
        this.x = x;
        this.y = y;
    }

    public static int getBodyHeight() {
        return bodyHeight;
    }

    public static int getBodyWidth() {
        return bodyWidth;
    }

    public void screenCollision() {
        if (vx > 0 && x + bodyWidth >= screen.getWidth()) {
            vx *= -1;
        }
        if (vx < 0 && x <= 0) {
            vx *= -1;
        }
        if (vy > 0 && y + bodyHeight >= Math.min(screen.getHeight() - lowerBound, screen.getHeight())) {
            vy *= -1;
        }
        if (vy < 0 && y <= 0) {
            vy *= -1;
        }
    }

    public void ufoCollision(UFO other) {
        if (((x > other.x && x < other.x + bodyWidth) || (other.x > x && other.x < x + bodyWidth)) && ((y > other.y && y < other.y + bodyHeight) || (other.y > y && other.y < y + bodyHeight))) {
            vx *= -1;
            other.vx *= -1;
            vy *= -1;
            other.vy *= -1;
        }
    }

    public void move() {
        screenCollision();
        x += vx;
        y += vy;
    }

    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.fillOval(x, y, bodyWidth, bodyHeight);
        g2d.setColor(Color.RED);
        g2d.fillOval(x + 33, y , cockpitWidth, cockpitHeight);
        coolDown++;
        if (coolDown >= 50 && coolDown <= 100) {
            g2d.setColor(Color.YELLOW);
            g2d.fillOval(x + 10, y + 17, lightRadius, lightRadius);
            g2d.fillOval(x + bodyWidth - 30, y + 17, lightRadius, lightRadius);
        } else if (coolDown > 100) {
            coolDown = 0;
        }
    }
}
