package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AnalProbe extends Command {

    public AnalProbe() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }   

    // Called just before this Command runs the first time
    protected void initialize() {
    	//RobotMap.solenoid.set(true);
    	//RobotMap.solenoid2.set(true);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    //	RobotMap.solenoid.set(false);
    	//RobotMap.solenoid2.set(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
