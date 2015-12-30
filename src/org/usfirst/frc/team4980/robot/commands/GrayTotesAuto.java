package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.RobotMap;
import org.usfirst.frc.team4980.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GrayTotesAuto extends Command {

    public GrayTotesAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    double time;
    DriveTrain driveTrain = new DriveTrain();
    // Called just before this Command runs the first time
    protected void initialize() {
    	time = Timer.getFPGATimestamp();
    	loopCounter = 0;
    }
    int loopCounter;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (loopCounter == 0)
    	{
    		driveTrain.straight();
    		while(time+1.45 > Timer.getFPGATimestamp())
    		{
    			RobotMap.driveTrain.drive(.3, 0);
    		}
    	}
    	else if (loopCounter == 1)
    	{
    		while (time+.5 > Timer.getFPGATimestamp())
    		{
    			RobotMap.frontElevator.set(-.5);
    		}
    	}
    	else if (loopCounter == 2)
    	{
    		RobotMap.frontElevator.set(0);
    		while (time+1.1 > Timer.getFPGATimestamp())
    		{
    			RobotMap.driveTrain.drive(-.35, 0);
    		}
    		RobotMap.driveTrain.drive(0, 0);
    	}
    	loopCounter++;
    	time = Timer.getFPGATimestamp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveTrain.defaultDrive();
    	RobotMap.frontElevator.set(0);
    	RobotMap.driveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
