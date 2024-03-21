import java.awt.*;
import java.awt.event.*;

public class PlayerUFO extends UFO {

    private boolean goRight = false, goLeft = false, goUp = false, goDown = false;
    public PlayerUFO(int x, int y, Cityscape screen, int roadHeight) {
        super(x, y, screen, roadHeight);
        bodyColour = new Color(232, 255, 243);
        cockpitColour = new Color(255, 143, 52);
        lightColour = new Color(172, 255, 7);
        vx = 0;
        vy = 0;
    }

    public int getVx() {
        return vx;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
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
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            tractorBeam = true;
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
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            tractorBeam = false;
        }
    }

    private boolean carTouchesTractorBeam(Car car) {
        return (((car.getX() >= getBeamX() && car.getX() <= getBeamX() + beamWidth) || (getBeamX() >= car.getX() && getBeamX() <= car.getX() + car.getWidth())) && ((car.getY() >= getBeamY() && car.getY() <= getBeamY() + beamHeight) || (getBeamY() >= car.getY() && getBeamY() <= car.getY() + car.getHeight())));
    }

    public void abduct(Car car) {
        if (tractorBeam) {
            if (carTouchesTractorBeam(car)) {
                car.setAbductCar(true);
            } else {
                car.setAbductCar(false);
            }
        } else {
            car.setAbductCar(false);
        }
    }

    public void move() {
        screenCollision();
        if (goLeft) {
            vx = -5;
        }
        if (goRight) {
            vx = 5;
        }
        if (goUp) {
            vy = -5;
        }
        if (goDown) {
            vy = 5;
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
        if (y + bodyHeight + roadHeight >= screen.getHeight()) {
            y = screen.getHeight() - bodyHeight - roadHeight;
        }
    }
}