package org.usfirst.frc.team4980.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;
import org.usfirst.frc.team4980.robot.commands.GatherContainer;
import org.usfirst.frc.team4980.robot.commands.GoBackOnePositionFrontElevator;


/**
 *
 */
public class chooseButton2 extends Command {

    public chooseButton2() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    GoBackOnePositionFrontElevator backLiftPosition = new GoBackOnePositionFrontElevator();
    GatherContainer gatherContainer = new GatherContainer();
    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.invert==-1)
    		gatherContainer.start();
    	if(Robot.invert==1)
    	{
    		//liftPosition.initialize();
    		backLiftPosition.start();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.invert==-1)
    		return gatherContainer.isFinished();
    	if(Robot.invert==1)
    		return backLiftPosition.isFinished();
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(Robot.invert==1)
    		RobotMap.counter--;
    	gatherContainer.end();
    	gatherContainer.cancel();
    	backLiftPosition.end();
    	backLiftPosition.cancel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
