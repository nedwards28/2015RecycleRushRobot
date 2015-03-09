package org.usfirst.frc.team4980.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;
import org.usfirst.frc.team4980.robot.commands.ClawOpen;
import org.usfirst.frc.team4980.robot.commands.GetPositionFrontElevator;

/**
 *
 */
public class chooseButton1 extends Command {

	ClawOpen clawChange = new ClawOpen();
	GetPositionFrontElevator liftPosition = new GetPositionFrontElevator();
	
    public chooseButton1() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.invert==1)
    	{
    		//liftPosition.initialize();
    		liftPosition.start();
    	}
    	if(Robot.invert==-1)
    		clawChange.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.invert==1)
    		return liftPosition.isFinished();
    	if(Robot.invert==-1)
    		return clawChange.isFinished();
    	
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(Robot.invert==1)
    		RobotMap.counter++;
    	clawChange.end();
    	liftPosition.end();
    	liftPosition.cancel();
    	clawChange.cancel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
