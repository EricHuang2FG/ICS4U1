import java.awt.*;

public class Building {

    private int x, y, width, height;

    public Building(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics2D g2d) {
        Rectangle bounds = g2d.getClipBounds();
        g2d.setColor(Color.GRAY);
        g2d.fillRect(x, bounds.height - y - height, width, height);
    }
}
