import java.awt.*;
import java.awt.event.*;

public class ControlledConveyorBelt extends ConveyorBelt {

    public ControlledConveyorBelt(String orientation, Scanner scanner) {
        super(orientation, scanner);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            stopped = !stopped;
        }
    }

    public void operate(Parcel[] parcels) {
        if (!stopped) {
            for (Parcel parcel: parcels) {
                if (parcel.getX() + parcel.getLength() <= x + length) {
                    parcel.setVx(4);
                }
            }
        } else {
            for (Parcel parcel: parcels) {
                if (parcel.getX() + parcel.getLength() <= x + length) {
                    parcel.setVx(0);
                }
            }
        }
    }
}