package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *close on container, then lift back elevator and move claw down for button 2
 */
public class GatherContainer extends Command {

    public GatherContainer() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.claw);
    	requires(Robot.driveTrain);
    	requires(Robot.backElevator);
    	requires(Robot.backElevatorReach);
    }
    double time;
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.claw.clawClose();
    	time = Timer.getFPGATimestamp();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(time+3>Timer.getFPGATimestamp())
    	{
	    	RobotMap.backElevatorReach.set(-.8);
	    	RobotMap.backElevator.set(-.3);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(time+3<Timer.getFPGATimestamp())
    		return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.backElevatorReach.set(0);
    	RobotMap.backElevator.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
