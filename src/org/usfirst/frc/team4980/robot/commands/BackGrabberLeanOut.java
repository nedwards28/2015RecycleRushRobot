package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BackGrabberLeanOut extends Command {

	double m_timeOut, m_speed; 
    public BackGrabberLeanOut(double timeOut, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_timeOut = timeOut;
    	m_speed = speed;
    	requires(Robot.backElevator);
    	requires(Robot.backElevatorReach);
    	
    }

    // Called just before this Command runs the first time
    double time;
    protected void initialize() {
    	Robot.claw.clawOpen();
    	time = Timer.getFPGATimestamp();
    	Robot.backElevatorReach.down(m_speed);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(time+m_timeOut<Timer.getFPGATimestamp())
    		return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
