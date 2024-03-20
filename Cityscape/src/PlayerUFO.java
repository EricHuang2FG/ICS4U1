import java.awt.*;
import java.awt.event.*;

public class PlayerUFO extends UFO {

    private boolean goRight = false, goLeft = false, goUp = false, goDown = false;
    public PlayerUFO(int x, int y, Cityscape screen) {
        super(x, y, screen);
        bodyColour = new Color(232, 255, 243);
        cockpitColour = new Color(255, 143, 52);
        lightColour = new Color(172, 255, 7);
        vx = 0;
        vy = 0;
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            goLeft = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            goRight = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            goUp = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            goDown = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            goLeft = false;
            vx = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            goRight = false;
            vx = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            goUp = false;
            vy = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            goDown = false;
            vy = 0;
        }
    }

    public void move() {
        screenCollision();
        if (goLeft) {
            vx = -4;
        }
        if (goRight) {
            vx = 4;
        }
        if (goUp) {
            vy = -4;
        }
        if (goDown) {
            vy = 4;
        }
        x += vx;
        y += vy;
        if (x <= 0) {
            x = 0;
        }
        if (x + bodyWidth >= screen.getWidth()) {
            x = screen.getWidth() - bodyWidth;
        }
        if (y <= 0) {
            y = 0;
        }
        if (y + bodyHeight >= screen.getHeight()) {
            y = screen.getHeight() - bodyHeight;
        }
    }
}