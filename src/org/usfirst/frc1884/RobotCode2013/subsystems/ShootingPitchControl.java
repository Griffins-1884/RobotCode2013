// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc1884.RobotCode2013.subsystems;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc1884.RobotCode2013.RobotMap;
import org.usfirst.frc1884.RobotCode2013.commands.ManuallyControlShooterPitch;
/**
 *
 */
public class ShootingPitchControl extends Subsystem implements PIDSource, PIDOutput {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController pitchControlMotor = RobotMap.shootingPitchControlPitchControlMotor;
    Encoder pitchEncoder = RobotMap.shootingPitchControlPitchEncoder;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public PIDController pidController;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new ManuallyControlShooterPitch());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        pidController = new PIDController(50.0, 0.0, 0.0, 0.0, this, this, 0.05);
        pidController.setPercentTolerance(1.0);
        pidController.setInputRange(0.1, 0.3); // @todo: add the maximum and minimum distances for the winch
        pidController.setOutputRange(-1.0, 1.0);
        pidController.disable();
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static final double SHOOTING_TRIANGLE_TOP_SIDE_LENGTH = 0.332, // in meters
                               SHOOTING_TRIANGLE_BOTTOM_SIDE_LENGTH = 0.415, // in meters
                               SHOOTING_TRIANGLE_TOP_SIDE_ANGLE_TO_SHOOTER = Math.toRadians(36), // in radians
                               SHOOTING_TRIANGLE_BOTTOM_SIDE_ANGLE_TO_FLOOR = Math.toRadians(41); // in radians
    public double calculateWinchLengthForShooterAngle(double shooterAngle) {
        // Returns the target length of the winch given the desiered shooter angle in radians
        //     by using the law of cosines with the triangle from the axle to the winch clip to the winch pulley
        // The shooting angle is given by SHOOTING_TRIANGLE_TOP_SIDE_ANGLE_TO_SHOOTER + <the angle between the winch clip, the axle and the winch> - SHOOTING_TRIANGLE_BOTTOM_SIDE_ANGLE_TO_FLOOR
        double triangleAngle = shooterAngle + SHOOTING_TRIANGLE_BOTTOM_SIDE_ANGLE_TO_FLOOR - SHOOTING_TRIANGLE_TOP_SIDE_ANGLE_TO_SHOOTER;
        double targetWinchLength = Math.sqrt(SHOOTING_TRIANGLE_TOP_SIDE_LENGTH * SHOOTING_TRIANGLE_TOP_SIDE_LENGTH + SHOOTING_TRIANGLE_BOTTOM_SIDE_LENGTH * SHOOTING_TRIANGLE_BOTTOM_SIDE_LENGTH - 2 * SHOOTING_TRIANGLE_TOP_SIDE_LENGTH * SHOOTING_TRIANGLE_BOTTOM_SIDE_LENGTH * Math.cos(triangleAngle));
        return targetWinchLength;
    }
    
    public double getShooterAngle() {
        double winchLength = pidGet();
        double triangleAngle = (SHOOTING_TRIANGLE_TOP_SIDE_LENGTH * SHOOTING_TRIANGLE_TOP_SIDE_LENGTH + SHOOTING_TRIANGLE_BOTTOM_SIDE_LENGTH * SHOOTING_TRIANGLE_BOTTOM_SIDE_LENGTH - winchLength * winchLength) / (2 * SHOOTING_TRIANGLE_TOP_SIDE_LENGTH * SHOOTING_TRIANGLE_BOTTOM_SIDE_LENGTH);
        return triangleAngle + SHOOTING_TRIANGLE_TOP_SIDE_ANGLE_TO_SHOOTER - SHOOTING_TRIANGLE_BOTTOM_SIDE_ANGLE_TO_FLOOR;
    }
    
    public static final double WINCH_DIAMETER = 0.0115; // in meters
    
    public double initialWinchLength = 0.0; // in meters
    public double pidGet() {
        return pitchEncoder.getDistance() + initialWinchLength;
    }
    public void pidWrite(double d) {
        this.setPitchChangeSpeed(d);
    }
    
    public void setPitchChangeSpeed(double value) { // Positive values move the shooter up
        System.out.println("motor: " + value);
        System.out.println("pid: " + pidController.isEnable());
        pitchControlMotor.set(value);
    }
}
