import java.awt.*;

public class UFO {

    protected int x, y;
    protected int vx = 2, vy = 1;
    protected static final int bodyWidth = 120, bodyHeight = 50;
    protected static final int cockpitWidth = 40, cockpitHeight = 33;
    protected static final int lightRadius = 15;
    private int lowerBound;
    protected int coolDown = 0;
    protected Cityscape screen;
    protected Color bodyColour = Color.BLUE;
    protected Color cockpitColour = Color.RED;
    protected Color lightColour = Color.YELLOW;

    public UFO(int tallestBuildingHeight, Cityscape screen, int x, int y, int roadHeight) {
        this.screen = screen;
        this.lowerBound = tallestBuildingHeight + 30 + roadHeight;
        this.x = x;
        this.y = y;
    }

    public UFO(int x,int y, Cityscape screen) {
        this.x = x;
        this.y = y;
        this.screen = screen;
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
        g2d.setColor(bodyColour);
        g2d.fillOval(x, y, bodyWidth, bodyHeight);
        g2d.setColor(cockpitColour);
        g2d.fillOval(x + 33, y , cockpitWidth, cockpitHeight);
        coolDown++;
        if (coolDown >= 50 && coolDown <= 100) {
            g2d.setColor(lightColour);
            g2d.fillOval(x + 10, y + 17, lightRadius, lightRadius);
            g2d.fillOval(x + bodyWidth - 30, y + 17, lightRadius, lightRadius);
        } else if (coolDown > 100) {
            coolDown = 0;
        }
    }
}
