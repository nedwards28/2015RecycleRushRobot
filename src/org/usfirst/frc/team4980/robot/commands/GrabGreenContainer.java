package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *run grabber up until ultrasonic triggers, grabber close
 */
public class GrabGreenContainer extends Command {
	
	
	 double m_timeOut, m_speed, time;
    public GrabGreenContainer(double timeOut, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.claw);
    	requires(Robot.backElevator);
    	m_timeOut = timeOut;
    	m_speed = speed;
    }
   
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.claw.clawOpen();
    	time = Timer.getFPGATimestamp();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.backElevator.up(Math.abs(m_speed));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(RobotMap.sonic2.getVoltage()<.32)
    	{
    		Robot.backElevator.backElevatorMotor.set(0);
    		Robot.claw.clawClose();
    		return true;
    	}
    	if(time+m_timeOut<Timer.getFPGATimestamp())
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
