package org.usfirst.frc.team4980.robot.commands;

import java.awt.Robot;

import org.usfirst.frc.team4980.robot.RobotMap;
import org.usfirst.frc.team4980.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ContainerFromStepAuto extends Command {

    public ContainerFromStepAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    DriveTrain driveTrain = new DriveTrain();
    // Called just before this Command runs the first time
    protected void initialize() {
    	time = Timer.getFPGATimestamp();
    	loopCounter = 0;
    	RobotMap.cylinderOpen.set(true);
		RobotMap.cylinderClose.set(false);
    }
    double time;
    int loopCounter;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (loopCounter == 0)
    	{
    		driveTrain.straight();
    		while(time+.4 > Timer.getFPGATimestamp())
    		{
    			RobotMap.driveTrain.drive(-.3, 0);
    		}
    		time = Timer.getFPGATimestamp();
    		while (time+.2 > Timer.getFPGATimestamp())
    		{
    			
    		}
    	} 
    	else if (loopCounter == 1)
    	{
    		while(time+1.6 > Timer.getFPGATimestamp())
    		{
    			RobotMap.backElevator.set(.45);
    			RobotMap.backElevatorReach.set(.8);
    		}
    		time = Timer.getFPGATimestamp();
    		while(time +.5 > Timer.getFPGATimestamp())
    		{
    			
    		}
    		RobotMap.cylinderOpen.set(false);
    		RobotMap.cylinderClose.set(true);
    	} 
    	else if (loopCounter == 2)
    	{
    		while (time+.225 > Timer.getFPGATimestamp())
    		{
    			RobotMap.backElevator.set(.65);
    		}
    		RobotMap.backElevator.set(0);
    		
    	}
    	else if (loopCounter == 3)
    	{
    		while(time+.3 > Timer.getFPGATimestamp())
    		{
    			RobotMap.backElevatorReach.set(-.9);
    		}
    	}
    	else if (loopCounter == 4)
    	{
    		while (time+.2 > Timer.getFPGATimestamp())
    		{
    			RobotMap.backElevator.set(-.5);
    			RobotMap.backElevatorReach.set(-.8);
    		}
    		
    		time = Timer.getFPGATimestamp();
    		while ( time + .5 > Timer.getFPGATimestamp())
    		{
    			
    		}
    		RobotMap.backElevator.set(0);
    	}
    	
    	else if (loopCounter == 5)
    	{
    		driveTrain.straight();
    		while(time+1.75 > Timer.getFPGATimestamp())
        	{
        		RobotMap.driveTrain.drive(.4, 0);
        	}
    		RobotMap.driveTrain.drive(0, 0);
    	}
    	else if (loopCounter == 6)
    	{
    		driveTrain.rotate();
    		while(time+1.7>Timer.getFPGATimestamp())
        	{
        		RobotMap.driveTrain.drive(-.4, 0);
        	}
    	}
    	time = Timer.getFPGATimestamp();
    	loopCounter++;
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.driveTrain.drive(0, 0);
    	driveTrain.defaultDrive();
    	RobotMap.backElevator.set(0);
    	RobotMap.backElevatorReach.set(0);
    	
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
