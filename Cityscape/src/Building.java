import java.awt.*;

public class Building {

    private int x, y, width, height, roadHeight;
    private int windowLength = 12;
    private int windowSpacing = 5;
    private Window[][] windows;

    public Building(int x, int y, int width, int height, int roadHeight) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.roadHeight = roadHeight;
        windows = new Window[height / (windowSpacing + windowLength)][width / (windowSpacing + windowLength)];
        int verticalPosition = y + height - windowSpacing - windowLength;
        for (int i = 0; i < windows.length; i++) {
            int horizontalPosition = x + windowSpacing;
            for (int j = 0; j < windows[i].length; j++) {
                windows[i][j] = new Window(horizontalPosition, verticalPosition, windowLength, roadHeight);
                horizontalPosition += windowSpacing + windowLength;
            }
            verticalPosition -= (windowLength + windowSpacing);
        }
    }

    public void paint(Graphics2D g2d) {
        Rectangle bounds = g2d.getClipBounds();
        g2d.setColor(Color.GRAY);
        g2d.fillRect(x, bounds.height - y - height - roadHeight, width, height); // (x, y) is the bottom left corner
        for (int i = 0; i < windows.length; i++) {
            for (int j = 0; j < windows[i].length; j++) {
                windows[i][j].paint(g2d);
            }
        }
    }
}
