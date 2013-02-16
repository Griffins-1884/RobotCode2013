package org.usfirst.frc1884.RobotCode2013.oi;

import edu.wpi.first.wpilibj.Joystick;

public class Joystick2013 extends Joystick implements Controller2013 {
    public Joystick2013(int port) {super(port);}
    public double getAnalog(int analogConstant) {
        switch(analogConstant) {
            case Controller2013.DRIVE_FORWARD:
                return this.getY();
            default:
                return 0.0;
        }
    }
    public boolean getBoolean(int digitalConstant) {
        switch(digitalConstant) {
            default:
                return false;
        }
    }
}
