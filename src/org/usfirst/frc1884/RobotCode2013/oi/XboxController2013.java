package org.usfirst.frc1884.RobotCode2013.oi;

public class XboxController2013 extends XboxController implements Controller2013 {
    public XboxController2013(int port) {super(port);}
    public double getAnalog(int analogConstant) {
        switch(analogConstant) {
            case Controller2013.DRIVE_FORWARD:
                System.out.println("forward: " + this.getLeftY());
                return this.getLeftY();
            case Controller2013.DRIVE_RIGHT:
                System.out.println("right: " + this.getLeftX());
                return this.getLeftX();
            case Controller2013.DRIVE_COUNTERCLOCKWISE:
                System.out.println("ccl: " + this.getRightX());
                return this.getRightX();
            case Controller2013.SHOOTER_PITCH:
                return this.getRightY();
            case Controller2013.SHOOTER_SPEED:
                return Math.max(this.getTriggerValue(), 0);
            default:
                return 0.0;
        }
    }
    public boolean getBoolean(int digitalConstant) {
        switch(digitalConstant) {
            case Controller2013.AUTOAIM_TRACK:
                return this.getButtonA();
            case Controller2013.CLIMBER_EXTEND:
                return this.getRightLowerTrigger();
            case Controller2013.FEEDER_INTAKE:
                return this.getLeftUpperTrigger();
            case Controller2013.SHOOTER_FIRE:
                return this.getRightUpperTrigger();
            case Controller2013.EXTRA_BUTTON_1:
                return this.getButtonX();
            case Controller2013.EXTRA_BUTTON_2:
                return this.getButtonY();
            default:
                return false;
        }
    }
}
