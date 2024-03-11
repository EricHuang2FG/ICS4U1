import java.awt.*;
import javax.swing.*;

public class UFO {

    private int x = 0, y = 0;
    private int vx = 4, vy = 2;
    private int bodyWidth = 125, bodyHeight = 50;
    private int cockpitWidth = 50, cockpitHeight = 40;
    private int lightRadius = 15;
    private int lowerBound;
    private int coolDown = 0;
    private Cityscape screen;

    public UFO (int tallestBuildingHeight, Cityscape screen) {
        this.screen = screen;
        this.lowerBound = tallestBuildingHeight + 20;
    }

    public void changeDirection() {
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

    public void move() {
        changeDirection();
        x += vx;
        y += vy;
    }

    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.fillOval(x, y, bodyWidth, bodyHeight);
        g2d.setColor(Color.RED);
        g2d.fillOval(x + 25, y - 10, cockpitWidth, cockpitHeight);
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
