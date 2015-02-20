package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4980.robot.subsystems.DriveTrain;

/**
 *
 */
public class ContainerOnlyAuto extends Command {

    public ContainerOnlyAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    DriveTrain driveTrain = new DriveTrain();
    double time;
    int mode;
    boolean step;
    // Called just before this Command runs the first time
    protected void initialize() {
    	mode = 0;
    	step =(boolean) Robot.stepOrNah.getSelected();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(mode==0)
    	{
    		RobotMap.cylinderOpen.set(false);
    		RobotMap.cylinderClose.set(true);
    	}
    	
    	else if(mode==1)
    	{//back up over step
    		driveTrain.straight();
    		time = Timer.getFPGATimestamp();
    		while(time+.5>Timer.getFPGATimestamp() )
        	{
        		RobotMap.driveTrain.drive(.4, 0);//drive in reverse 
        	}
    		RobotMap.driveTrain.drive(0, 0);
		}
    	else if(mode==2)
    	{
    		time = Timer.getFPGATimestamp();
    		while(time+2>Timer.getFPGATimestamp())
    		{
    			RobotMap.backElevator.set(.5);
    		}
    		RobotMap.backElevator.set(0);
    		
    	}
    	else if(mode==3 && step==false)
    	{//back up over step
    		time = Timer.getFPGATimestamp();
    		while(time+2.65>Timer.getFPGATimestamp())
        	{
        		RobotMap.driveTrain.drive(.3, 0);//drive in reverse over step
        	}
		}
    	else if(mode==3 && step==true)
    	{//back up over step
    		time = Timer.getFPGATimestamp();
    		while(time+3.7>Timer.getFPGATimestamp())
        	{
        		RobotMap.driveTrain.drive(.3, 0);//drive in reverse over step
        	}
		}
    	else if(mode==4)
    	{//rotate to fit
    		time = Timer.getFPGATimestamp();
    		driveTrain.rotate();
    		while(time+1.85>Timer.getFPGATimestamp())
        	{
        		RobotMap.driveTrain.drive(-.35, 0);
        	}
    	}
    	else if(mode==5 && step==false)
    	{//back to corner
    		time = Timer.getFPGATimestamp();
    		driveTrain.straight();
    		while(time+1.7>Timer.getFPGATimestamp())
        	{
        		RobotMap.driveTrain.drive(.25, 0);
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
