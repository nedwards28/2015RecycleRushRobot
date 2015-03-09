package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *sets down a stack of totes
 */
public class SetDownStack extends Command {

    public SetDownStack() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.frontElevator);
    }
    double time;
    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.counter = 0;
    	time = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(time+.3>Timer.getFPGATimestamp())
    	{
    		Robot.frontElevator.frontElevatorMotor.set(.2);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(time+.3<Timer.getFPGATimestamp())
    		return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.frontElevator.frontElevatorMotor.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
