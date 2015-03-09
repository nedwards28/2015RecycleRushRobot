package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToLandFillZone extends Command {

    public DriveToLandFillZone() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	requires(Robot.backElevator);
    	requires(Robot.backElevatorReach);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = Timer.getFPGATimestamp();
    }
    double time;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.straight();
    	
    	while(time+1.85>Timer.getFPGATimestamp())
    	{
    		RobotMap.driveTrain.drive(-.18, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.driveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
