package org.usfirst.frc1884.RobotCode2013.oi;

import edu.wpi.first.wpilibj.Joystick;

public class TwoJoystick2013 implements Controller2013 {
    public Joystick joystick1;
    public Joystick joystick2;
    public TwoJoystick2013(int port1, int port2) {
        joystick1 = new Joystick(port1);
        joystick2 = new Joystick(port2);
    }
    public double removeDrift(double val) {
        if(Math.abs(val) < 0.12) {
            return 0.0;
        }
        return val * Math.abs(val);
    }
    public double getAnalog(int analogConstant) {
        switch(analogConstant) {
            case Controller2013.DRIVE_FORWARD:
                return removeDrift(sensitivize(-joystick1.getY()));
            case Controller2013.DRIVE_RIGHT:
                return removeDrift(sensitivize(joystick1.getX()));
            case Controller2013.DRIVE_COUNTERCLOCKWISE:
                return removeDrift(sensitivize(joystick1.getThrottle()));
            case Controller2013.SHOOTER_PITCH:
                if(joystick2.getRawButton(3)) {
                    return 1.0;
                } else if(joystick2.getRawButton(5)) {
                    return -1.0;
                } else {
                    return 0.0;
                }
            case Controller2013.SHOOTER_SPEED:
                if(joystick2.getRawButton(2)) {
                    return 1.0;
                } else {
                    return 0.0;
                }
            default:
                return 0.0;
        }
    }
    public boolean getBoolean(int digitalConstant) {
        switch(digitalConstant) {
            case Controller2013.AUTOAIM_TRACK:
                return false;
            case Controller2013.CLIMBER_EXTEND:
                return joystick2.getRawButton(4);
            case Controller2013.FEEDER_INTAKE:
                return joystick2.getRawButton(6);
            case Controller2013.SHOOTER_FIRE:
                return joystick2.getRawButton(1);
            case Controller2013.EXTRA_BUTTON_1:
            case Controller2013.EXTRA_BUTTON_2:
            default:
                return false;
        }
    }
    public double sensitivize(double val) {
        return val * (joystick1.getZ() + 1.0) / 2.0;
    }
}
