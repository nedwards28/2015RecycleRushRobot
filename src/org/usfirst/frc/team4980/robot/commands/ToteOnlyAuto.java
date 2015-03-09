package org.usfirst.frc.team4980.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;
import org.usfirst.frc.team4980.robot.subsystems.DriveTrain;

/**
 *
 */
public class ToteOnlyAuto extends Command {

	int mode;
	double time = 0;
	boolean step;
	DriveTrain driveTrain = new DriveTrain();
	
    public ToteOnlyAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	
    	mode = 0;
    	step =(boolean) Robot.stepOrNah.getSelected();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	if(mode==0)
    	{
    		time = Timer.getFPGATimestamp();
    		while(time+1.6>Timer.getFPGATimestamp())
        	{
        		RobotMap.frontElevator.set(-.3);//lift tote up
        		
        	}
        	RobotMap.frontElevator.set(0);
        	driveTrain.straight();
    	}
    	
    	else if(mode==1 && step==true)
    	{//back up over step
    		while(time+1<Timer.getFPGATimestamp() && time+3.7>Timer.getFPGATimestamp())
        	{
        		RobotMap.driveTrain.drive(-.4, 0);//drive in reverse over step
        	}
    		RobotMap.driveTrain.drive(0, 0);
		}
    	else if(mode==1 && step==false)
    	{//back up over step
    		while(time+1<Timer.getFPGATimestamp() && time+6>Timer.getFPGATimestamp())
        	{
        		RobotMap.driveTrain.drive(-.25, 0);//drive in reverse over step
        	}
    		RobotMap.driveTrain.drive(0, 0);
		}
    	else if(mode==2)
    	{//rotate to fit
    		time = Timer.getFPGATimestamp();
    		driveTrain.rotate();
    		while(time+1.7>Timer.getFPGATimestamp())
        	{
        		RobotMap.driveTrain.drive(-.4, 0);
        	}
    	}
    	/*
    	else if(mode==3 && step==false)
    	{//back to corner
    		time = Timer.getFPGATimestamp();
    		driveTrain.straight();
    		while(time+.88>Timer.getFPGATimestamp())
        	{
        		RobotMap.driveTrain.drive(-.25, 0);
        	}
    		RobotMap.driveTrain.drive(0, 0);
    	}*/
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
