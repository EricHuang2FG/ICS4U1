import java.awt.*;
import java.util.Random;

public class StarSky {

    private int screenWidth, screenHeight;
    private int starWidth = 4;
    private int[][] stars = new int[100][2];

    public StarSky(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        Random rand = new Random();
        for (int i = 0; i < stars.length; i++) {
            stars[i][0] = rand.nextInt(screenWidth + 1);
            stars[i][1] = rand.nextInt(screenHeight + 1);
        }
    }

    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, screenWidth, screenHeight);
        g2d.setColor(Color.WHITE);
        for (int i = 0; i < stars.length; i++) {
            g2d.fillOval(stars[i][0], stars[i][1], starWidth, starWidth);
        }
    }
}
