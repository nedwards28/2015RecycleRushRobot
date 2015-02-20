package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auto extends Command {

    public Auto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    public double time;
    // Called just before this Command runs the first time
    protected void initialize() {
    	time = Timer.getFPGATimestamp();
    	RobotMap.gyro.reset();
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false);
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);//frontRight
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);//rearRight
    	
    	RobotMap.driveTrain.drive(.2, 0);
    //	while(time-4<Timer.getFPGATimestamp())
    	
    	//Timer.delay(4);
    	if(time+4>Timer.getFPGATimestamp())
    		RobotMap.gyro.reset();
    	if(time+4<Timer.getFPGATimestamp() && RobotMap.gyro.getAngle()>-80)
    	{
    		
    		RobotMap.driveTrain.drive(.4, -.5);		
    	}
    	
    	if(time+15<Timer.getFPGATimestamp())
    		RobotMap.driveTrain.drive(0, 0);
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.driveTrain.drive(0, 0);
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false);
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);//frontRight
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);//rearRight
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
