package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class backElevatorDownTimed extends Command {

    public backElevatorDownTimed() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.backElevator);
    }
    double time;
    // Called just before this Command runs the first time
    protected void initialize() {
    	time = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.backElevator.backElevatorMotor.set(-.3);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(time+3<Timer.getFPGATimestamp())
    		return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.backElevator.backElevatorMotor.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
