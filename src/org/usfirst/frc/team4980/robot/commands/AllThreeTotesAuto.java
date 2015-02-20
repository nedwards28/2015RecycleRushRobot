package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4980.robot.subsystems.DriveTrain;

/**
 *
 */
public class AllThreeTotesAuto extends Command {

    public AllThreeTotesAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    int mode;
    double time;
    DriveTrain driveTrain = new DriveTrain();
    // Called just before this Command runs the first time
    protected void initialize() {
    	mode = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	if(mode==0)
    	{
    		time = Timer.getFPGATimestamp();
			while(time+.26>Timer.getFPGATimestamp())
			{
				RobotMap.frontElevator.set(-.4);
			}
    	}
    	else if(mode==1)
    	{
    		driveTrain.straight();
    		time = Timer.getFPGATimestamp();
    		while(time+.2>Timer.getFPGATimestamp())
    		{
    			RobotMap.driveTrain.drive(-.3, 0);
    		}
    		
    	}
    	else if(mode==2)
    	{
    		driveTrain.strafe();
    		time = Timer.getFPGATimestamp();
    		while(time+1.78>Timer.getFPGATimestamp())
    		{
    			RobotMap.driveTrain.drive(1, 0);
    		}
    		RobotMap.frontElevator.set(0);
    	}
    	else if(mode==3)
    	{
    		driveTrain.straight();
    		time = Timer.getFPGATimestamp();
    		while(time+.15>Timer.getFPGATimestamp())
    		{
    			RobotMap.driveTrain.drive(.3, 0);
    		}
    	}
    	else if(mode==4)
    	{
    		driveTrain.straight();
    		time = Timer.getFPGATimestamp();
    		while(time+.25>Timer.getFPGATimestamp())
    		{
    			RobotMap.driveTrain.drive(.16, 0);
    		}
    	}
    	else if(mode==5)
    	{
    		time = Timer.getFPGATimestamp();
			while(time+.3>Timer.getFPGATimestamp())
			{
				RobotMap.frontElevator.set(-.5);
			}
			RobotMap.driveTrain.drive(0, 0);
    	}
    	mode++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.driveTrain.drive(0, 0);
    	RobotMap.suction.set(false);
    	RobotMap.frontElevator.set(0);
    	RobotMap.backElevator.set(0);
    	RobotMap.backElevatorReach.set(0);
    	RobotMap.clawMotor.set(0);
    	driveTrain.defaultDrive();
    	RobotMap.driveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
