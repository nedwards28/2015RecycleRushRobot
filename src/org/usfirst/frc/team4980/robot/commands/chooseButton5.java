package org.usfirst.frc.team4980.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.commands.frontElevatorUp;
import org.usfirst.frc.team4980.robot.commands.backElevatorReachUp;


/**
 *
 */
public class chooseButton5 extends Command {

    public chooseButton5() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    frontElevatorUp frontUp = new frontElevatorUp();
    backElevatorReachUp backUp = new backElevatorReachUp();
    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//if(Robot.invert==1)
    		frontUp.start();
    	//if(Robot.invert==-1)
    		backUp.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//if(Robot.invert==1)
		return frontUp.isFinished();
    	//if(Robot.invert==-1)
    	//	return backUp.isFinished();
		
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	frontUp.end();
    	backUp.end();
    	frontUp.cancel();
    	backUp.cancel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("Interrupted", "You made it");
    	
    	end();
    }
}
