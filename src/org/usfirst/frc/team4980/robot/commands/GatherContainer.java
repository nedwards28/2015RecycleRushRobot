package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GatherContainer extends Command {

    public GatherContainer() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.button2=!Robot.button2;
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.button2==true)
    	{
    	RobotMap.backElevatorReach.set(-.3);
    	RobotMap.cylinderClose.set(true);
    	RobotMap.cylinderOpen.set(false);
    	RobotMap.backElevator.set(-.3);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.button2==false)
    		return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.backElevatorReach.set(0);
    	RobotMap.cylinderClose.set(false);
    	RobotMap.cylinderOpen.set(true);
    	RobotMap.backElevator.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
