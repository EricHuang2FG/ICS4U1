import java.awt.*;
import javax.swing.*;

public class UFO {

    private int x = 0, y = 0;
    private int vx = 5, vy = 3;
    private int bodyWidth = 125, bodyHeight = 50;
    private int cockpitWidth = 50, cockpitHeight = 40;
    private int lowerBound;
    private Cityscape screen;

    public UFO (int tallestBuildingHeight, Cityscape screen) {
        this.lowerBound = tallestBuildingHeight + 10;
        this.screen = screen;
    }

    public void changeDirection() {
        if (vx > 0 && x + bodyWidth >= screen.getWidth()) {
            vx *= -1;
        }
        if (vx < 0 && x <= 0) {
            vx *= -1;
        }
        if (vy > 0 && y + bodyHeight >= Math.min(lowerBound, screen.getHeight())) {
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
    }
}
