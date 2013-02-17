// This file was generated by RobotBuilder v0.0.2.
package org.usfirst.frc1884.RobotCode2013.subsystems;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc1884.RobotCode2013.RobotMap;
import org.usfirst.frc1884.RobotCode2013.commands.*;
/**
 * This class controls the chassis
 */
public class Chassis extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController frontLeftWheel = RobotMap.chassisFrontLeftWheel;
    SpeedController frontRightWheel = RobotMap.chassisFrontRightWheel;
    SpeedController backLeftWheel = RobotMap.chassisBackLeftWheel;
    SpeedController backRightWheel = RobotMap.chassisBackRightWheel;
    DigitalInput frontLightSensor = RobotMap.chassisFrontLightSensor;
    DigitalInput backLightSensor = RobotMap.chassisBackLightSensor;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public static final double TURN_GAIN = 0.5;
    public static final double _TURN_GAIN_Y_INTERCEPT = 1.0 - TURN_GAIN;
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new TeleopDrive());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    }
    
    public boolean getFrontLightSensor() {
        return frontLightSensor.get();
    }
    
    public boolean getBackLightSensor() {
        return backLightSensor.get();
    }
    
    /**
     * This method normalizes the mecanum drive, such that no motor will be given a value out of range
     * 
     * @param forward The amount to move forward
     * @param right The amount to move right
     * @param counterclockwise The amount to move counterclockwise
     */
    public void driveMecanumNormalized(double forward, double right, double counterclockwise) {
        double pseudoMagnitude = Math.abs(forward) + Math.abs(right);
        if(pseudoMagnitude > 1) {
            counterclockwise = counterclockwise * (TURN_GAIN * pseudoMagnitude + _TURN_GAIN_Y_INTERCEPT);
        }
        
        double normalizationValue = pseudoMagnitude + Math.abs(counterclockwise);
        if(normalizationValue > 1) {
            forward /= normalizationValue;
            right /= normalizationValue;
            counterclockwise /= normalizationValue;
        }
        driveMecanumNonNormalized(forward, right, counterclockwise);
    }
    
    /**
     * This method abstracts the mecanum drive to a forward, right and counterclockwise amount
     * 
     * @param forward The amount to move forward
     * @param right The amount to move right
     * @param counterclockwise The amount to move counterclockwise
     */
    public void driveMecanumNonNormalized(double forward, double right, double counterclockwise) {
        double frontRight = forward + right + counterclockwise;
        double frontLeft = forward - right - counterclockwise;
        double backRight = forward - right + counterclockwise;
        double backLeft = forward + right - counterclockwise;
        
        driveWheelsForward(frontRight, frontLeft, backRight, backLeft);
    }
    
    /**
     * This method abstracts the reversal of motors, so that every motor goes forward
     * 
     * @param frontLeft The voltage of the front left motor
     * @param frontRight The voltage of the front right motor
     * @param backLeft The voltage of the back left motor
     * @param backRight The voltage of the back right motor
     */
    public void driveWheelsForward(double frontRight, double frontLeft, double backRight, double backLeft) {
        frontRightWheel.set(frontRight);
        frontLeftWheel.set(-frontLeft);
        backRightWheel.set(backRight);
        backLeftWheel.set(-backLeft);
    }
}
