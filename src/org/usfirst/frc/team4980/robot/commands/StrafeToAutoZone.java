package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StrafeToAutoZone extends Command {

    public StrafeToAutoZone() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.strafe();
    	time = Timer.getFPGATimestamp();
    }
    double time;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	while(time+3.3>Timer.getFPGATimestamp())
    	{
    		RobotMap.driveTrain.drive(-1, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
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
