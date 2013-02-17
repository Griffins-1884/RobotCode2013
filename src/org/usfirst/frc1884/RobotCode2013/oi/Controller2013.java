package org.usfirst.frc1884.RobotCode2013.oi;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This interface should be implemented by any device hoping to control the 2013 robot
 */
public interface Controller2013 {
    public static final int DRIVE_FORWARD = 0,
                            DRIVE_RIGHT = 1,
                            DRIVE_COUNTERCLOCKWISE = 2,
                            SHOOTER_PITCH = 3,
                            SHOOTER_SPEED = 4;
    public static final int SHOOTER_FIRE = 1,
                            FEEDER_INTAKE = 2,
                            CLIMBER_EXTEND = 4,
                            AUTOAIM_TRACK = 5,
                            EXTRA_BUTTON_1 = 6;
    
    public double getAnalog(int analogConstant);
    public boolean getBoolean(int digitalConstant);
    
    /**
     * This class masquerades as a JoystickButton so that we can abstract it
     */
    public static class Controller2013Trigger extends JoystickButton {
        Controller2013 controller;
        int buttonConstant;
        
        public Controller2013Trigger(Controller2013 controller, int buttonConstant) {
            super(null, 0); // Doesn't matter, because we overload every method that uses those values
            this.controller = controller;
            this.buttonConstant = buttonConstant;
        }
        
        public boolean get() {
            return controller.getBoolean(buttonConstant);
        }
    }
}
