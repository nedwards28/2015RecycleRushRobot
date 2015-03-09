package org.usfirst.frc.team4980.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;
import org.usfirst.frc.team4980.robot.commands.backElevatorReachDown;
import org.usfirst.frc.team4980.robot.commands.frontElevatorDown;

/**
 *
 */

public class chooseButton3 extends Command {

	frontElevatorDown frontDown = new frontElevatorDown();
	backElevatorReachDown backDown = new backElevatorReachDown();
    public chooseButton3() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    double time;
    // Called just before this Command runs the first time
    protected void initialize() {
    	//if(Robot.invert==1)
    	//{
    		frontDown.start();
    	//}
    	/*
    	else if(Robot.invert==-1)
    		backDown.start();
    		*/
    	time = Timer.getFPGATimestamp();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
    	//if(Robot.invert==1)
    	//{
    		if(Timer.getFPGATimestamp()>time+3)
    			RobotMap.counter=0;
    	//}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//if(Robot.invert==1)
    //	{
    		return frontDown.isFinished();
    	//}
    	/*
    	else if(Robot.invert==-1)
    		return backDown.isFinished();
    	*/
		
    }

    // Called once after isFinished returns true
    protected void end() {
    	frontDown.end();
    	backDown.end();
    	frontDown.cancel();
    	backDown.cancel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
