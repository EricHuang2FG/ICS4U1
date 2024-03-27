import java.awt.*;

public class BeltLine {

    private int x1, x2, y1, y2;
    private int vx = 0, vy = 0;
    private final Color lineColour = Color.BLACK;

    public BeltLine(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}