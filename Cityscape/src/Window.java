import java.util.Random;
import java.awt.*;

public class Window {
    private int x, y, length;
    private boolean lightsOn;

    public Window(int x, int y, int length, int offGround) {
        Random rand = new Random();
        lightsOn = rand.nextInt((2 - 1) + 1) + 1 == 1;
        this.x = x;
        this.y = y + offGround;
        this.length = length;
    }

    public void paint(Graphics2D g2d) {
        Rectangle bounds = g2d.getClipBounds();
        if (lightsOn) {
            g2d.setColor(Color.YELLOW);
        } else {
            g2d.setColor(Color.BLACK);
        }
        g2d.fillRect(x, bounds.height - y - length, length, length); // (x, y) is the bottom left corner
    }
}
