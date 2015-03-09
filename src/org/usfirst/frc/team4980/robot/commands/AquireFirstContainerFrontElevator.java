package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AquireFirstContainerFrontElevator extends Command {

    public AquireFirstContainerFrontElevator() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = Timer.getFPGATimestamp();
    }
    double time;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	while(time+.8>Timer.getFPGATimestamp())
    	{
    		RobotMap.frontElevator.set(-.5);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.frontElevator.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
