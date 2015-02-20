package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawOpen extends Command {

    public ClawOpen() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	double x = Robot.oi.stick.getRawAxis(3);
    	x=x-1;
    	x=x/(-2);
    	RobotMap.clawMotor.set(x);
    	*/
    	Robot.clawState=!Robot.clawState;
    	if(Robot.clawState==false)
    	{
    		RobotMap.cylinderOpen.set(true);
    		RobotMap.cylinderClose.set(false);
    	}
    	else if(Robot.clawState==true)
    	{
    		RobotMap.cylinderOpen.set(false);
    		RobotMap.cylinderClose.set(true);
    		
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//RobotMap.clawMotor.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
