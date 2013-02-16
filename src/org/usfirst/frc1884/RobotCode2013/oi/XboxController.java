package org.usfirst.frc1884.RobotCode2013.oi;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is a joystick class for the Xbox Controller
 *
 * http://www.chiefdelphi.com/forums/showpost.php?p=1003245&postcount=8
 */
public class XboxController extends Joystick {
    /**
     * These are constants to control the inversion of the controller
     */
    public final int UP = -1;
    public final int RIGHT = 1;
    
    /**
     * A constant to remove joystick drift
     */
    public final double driftConstant = 0.15;

    /**
     * Creates an XboxController
     *
     * @param port The port of the XboxController
     */
    public XboxController(int port) {
        super(port);
    }
    
    /**
     * This method removes drift
     * 
     * @param val The value from the joystick
     * @return The value without drift
     */
    public double removeDrift(double val) {
        if(Math.abs(val) < 0.12) {
            return 0.0;
        }
        return val * Math.abs(val);
    }
    
    /**
     * Gets the up-down value of the left joystick
     *
     * @return The value of this axis
     */
    public double getLeftY() {
        return removeDrift(getRawAxis(2)) * UP;
    }

    /**
     * Gets the left-right value of the left joystick
     *
     * @return The value of this axis
     */
    public double getLeftX() {
        return removeDrift(getRawAxis(1)) * RIGHT;
    }

    /**
     * Gets the left-right value of the right joystick
     *
     * @return The value of this axis
     */
    public double getRightX() {
        return removeDrift(getRawAxis(4)) * RIGHT;
    }

    /**
     * Gets the up-down value of the right joystick
     *
     * @return The value of this axis
     */
    public double getRightY() {
        return removeDrift(getRawAxis(5)) * UP;
    }

    /**
     * Gets the value of the triggers. The right joystick is positive, the left joystick is negative. The value of each is proportional to how much they are pressed, and then the two values are added together.
     *
     * @return The value of this axis
     */
    public double getTriggerValue() {
        return removeDrift(getRawAxis(3));
    }

    /**
     * Gets the value of the left lower trigger button. Note: not defined if both triggers are pressed simultaneously
     * 
     * @return The value of this button
     */
    public boolean getLeftLowerTrigger() {
        return (removeDrift(getRawAxis(3)) > 0.5);
    }

    /**
     * Gets the value of the right lower trigger button. Note: not defined if both triggers are pressed simultaneously
     * 
     * @return The value of this button
     */
    public boolean getRightLowerTrigger() {
        return (removeDrift(getRawAxis(3)) < -0.5);
    }

    /**
     * Gets the value of the A button
     *
     * @return The value of this button
     */
    public boolean getButtonA() {
        return getRawButton(1);
    }

    /**
     * Gets the value of the B button
     *
     * @return The value of this button
     */
    public boolean getButtonB() {
        return getRawButton(2);
    }

    /**
     * Gets the value of the X button
     *
     * @return The value of this button
     */
    public boolean getButtonX() {
        return getRawButton(3);
    }

    /**
     * Gets the value of the Y button
     *
     * @return The value of this button
     */
    public boolean getButtonY() {
        return getRawButton(4);
    }

    /**
     * Gets the value of the right upper trigger button
     *
     * @return The value of this button
     */
    public boolean getRightUpperTrigger() //right bumper
    {
        return getRawButton(6);
    }

    /**
     * Gets the value of the left upper trigger button
     *
     * @return The value of this button
     */
    public boolean getLeftUpperTrigger() {
        return getRawButton(5);
    }

    /**
     * Gets the value of the "back" button
     *
     * @return The value of this button
     */
    public boolean getBack()// to where you once belonged
    {
        return getRawButton(7);
    }

    /**
     * Gets the value of the "start" button
     *
     * @return The value of this button
     */
    public boolean getStart() {
        return getRawButton(8);
    }

    /**
     * Gets the value of the left stick button
     *
     * @return The value of this button
     */
    public boolean getLeftStick() {
        return getRawButton(9);
    }

    /**
     * Gets the value of the right stick button
     *
     * @return The value of this button
     */
    public boolean getRightStick() {
        return getRawButton(10);
    }

    /**
     * Gets the value of the right Dpad button
     *
     * @return The value of this button
     */
    public boolean getDpadRight() {
        return (getRawAxis(6) > 0);
    }

    /**
     * Gets the value of the left Dpad button
     *
     * @return The value of this button
     */
    public boolean getDpadLeft() {
        return (getRawAxis(6) < 0);
    }
}