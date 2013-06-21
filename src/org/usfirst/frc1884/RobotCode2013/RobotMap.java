// This file was generated by RobotBuilder v0.0.2.
package org.usfirst.frc1884.RobotCode2013;
    
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder.PIDSourceParameter;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc1884.RobotCode2013.subsystems.ShootingPitchControl;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController shooterFirstWheel;
    public static SpeedController shooterSecondWheel;
    public static SpeedController chassisFrontLeftWheel;
    public static SpeedController chassisFrontRightWheel;
    public static SpeedController chassisBackLeftWheel;
    public static SpeedController chassisBackRightWheel;
    public static DigitalInput chassisFrontLightSensor;
    public static DigitalInput chassisBackLightSensor;
    public static SpeedController feederFeederIntake;
    public static DoubleSolenoid feederFeederArmPiston;
    public static DoubleSolenoid climberLeftClimbingPiston;
    public static DoubleSolenoid climberRightClimbingPiston;
    public static Compressor otherCompressor;
    public static Gyro otherGyro;
    public static DoubleSolenoid storageBoxFiringPiston;
    public static SpeedController shootingPitchControlPitchControlMotor;
    public static Encoder shootingPitchControlPitchEncoder;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        shooterFirstWheel = new Talon(1, 5);
	LiveWindow.addActuator("Shooter", "First Wheel", (Talon) shooterFirstWheel);
        
        shooterSecondWheel = new Talon(1, 6);
	LiveWindow.addActuator("Shooter", "Second Wheel", (Talon) shooterSecondWheel);
        
        chassisFrontLeftWheel = new Talon(1, 1);
	LiveWindow.addActuator("Chassis", "Front Left Wheel", (Talon) chassisFrontLeftWheel);
        
        chassisFrontRightWheel = new Talon(1, 2);
	LiveWindow.addActuator("Chassis", "Front Right Wheel", (Talon) chassisFrontRightWheel);
        
        chassisBackLeftWheel = new Talon(1, 3);
	LiveWindow.addActuator("Chassis", "Back Left Wheel", (Talon) chassisBackLeftWheel);
        
        chassisBackRightWheel = new Talon(1, 4);
	LiveWindow.addActuator("Chassis", "Back Right Wheel", (Talon) chassisBackRightWheel);
        
        chassisFrontLightSensor = new DigitalInput(1, 2);
	LiveWindow.addSensor("Chassis", "Front Light Sensor", chassisFrontLightSensor);
        
        chassisBackLightSensor = new DigitalInput(1, 3);
	LiveWindow.addSensor("Chassis", "Back Light Sensor", chassisBackLightSensor);
        
        feederFeederIntake = new Talon(1, 7);
	LiveWindow.addActuator("Feeder", "Feeder Intake", (Talon) feederFeederIntake);
        
        feederFeederArmPiston = new DoubleSolenoid(1, 5, 6);      
	
        
        climberLeftClimbingPiston = new DoubleSolenoid(1, 1, 2);      
	
        
        climberRightClimbingPiston = new DoubleSolenoid(1, 3, 4);      
	
        
        otherCompressor = new Compressor(1, 1, 1, 1);
	
        
        otherGyro = new Gyro(1, 1);
	LiveWindow.addSensor("Other", "Gyro", otherGyro);
        otherGyro.setSensitivity(1.25);
        storageBoxFiringPiston = new DoubleSolenoid(1, 7, 8);      
	
        
        shootingPitchControlPitchControlMotor = new Talon(1, 9);
	LiveWindow.addActuator("Shooting Pitch Control", "Pitch Control Motor", (Talon) shootingPitchControlPitchControlMotor);
        
        shootingPitchControlPitchEncoder = new Encoder(1, 4, 1, 5, false, EncodingType.k4X);
	LiveWindow.addSensor("Shooting Pitch Control", "Pitch Encoder", shootingPitchControlPitchEncoder);
        shootingPitchControlPitchEncoder.setDistancePerPulse(1.0);
        shootingPitchControlPitchEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
        shootingPitchControlPitchEncoder.start();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        shootingPitchControlPitchEncoder.setDistancePerPulse(ShootingPitchControl.WINCH_DIAMETER * Math.PI / 250);
    }
}
