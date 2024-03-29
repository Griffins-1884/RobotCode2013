// This file was generated by RobotBuilder v0.0.2.
package org.usfirst.frc1884.RobotCode2013.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1884.RobotCode2013.Robot;
import org.usfirst.frc1884.RobotCode2013.oi.Controller2013;
/**
 * This class is what controls driving in Teleop
 */
public class  TeleopDrive extends Command {
    public TeleopDrive() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.chassis);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.chassis.driveMecanumNormalized(Robot.oi.driverController.getAnalog(Controller2013.DRIVE_FORWARD), Robot.oi.driverController.getAnalog(Controller2013.DRIVE_RIGHT), Robot.oi.driverController.getAnalog(Controller2013.DRIVE_COUNTERCLOCKWISE));
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    
    // Called once after isFinished returns true
    protected void end() {
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
