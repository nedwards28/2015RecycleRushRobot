package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTillSecondContainerCloses extends Command {

    public DriveTillSecondContainerCloses() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = Timer.getFPGATimestamp();
    	Robot.driveTrain.straight();
    	Robot.claw.clawOpen();
    }
    double time;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(RobotMap.sonic2.getVoltage()>.38)
    	{
    		RobotMap.driveTrain.drive(-.18, 0);
    	}
    	
    	else
    	{
    		Robot.claw.clawClose();
    	}
		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(RobotMap.sonic2.getVoltage()<.38)
    	{
    		time = Timer.getFPGATimestamp();
    		
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
		RobotMap.backElevator.set(0);
		RobotMap.driveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
