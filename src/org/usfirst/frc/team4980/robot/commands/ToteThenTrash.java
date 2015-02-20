package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4980.robot.subsystems.DriveTrain;

/**
 *
 */
public class ToteThenTrash extends Command {

    public ToteThenTrash() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    public DriveTrain driveTrain = new DriveTrain();
    double time;
    int mode;
    boolean stepOrNah;
    // Called just before this Command runs the first time
    protected void initialize() {
    	time = Timer.getFPGATimestamp();
    	stepOrNah = (boolean) Robot.stepOrNah.getSelected();
    
    	mode = 0;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(mode==0)
    	{
	    	driveTrain.straight();
	    	RobotMap.cylinderOpen.set(true);
	    	RobotMap.cylinderClose.set(false);
    	}
    	else if(mode==1)//lift up
    	{
    		while(time+1.6>Timer.getFPGATimestamp())
        	{
        		RobotMap.frontElevator.set(-.3);//lift tote up
        		
        	}
        	RobotMap.frontElevator.set(0);

    	}
    	else if(mode==2 && stepOrNah==true)
    	{//back up over step
    		while(time+1<Timer.getFPGATimestamp() && time+3.8>Timer.getFPGATimestamp())
        	{
        		RobotMap.driveTrain.drive(-.4, 0);//drive in reverse over step
        	}
    		RobotMap.driveTrain.drive(0, 0);
    		RobotMap.gyro.reset();
    		driveTrain.rotate();
    	}
    	else if(mode==2 && stepOrNah==false)
    	{//back up over step
    		while(time+1<Timer.getFPGATimestamp() && time+3.2>Timer.getFPGATimestamp())
        	{
        		RobotMap.driveTrain.drive(-.4, 0);//drive in reverse not over step
        	}
    		RobotMap.driveTrain.drive(0, 0);
    		RobotMap.gyro.reset();
    		driveTrain.rotate();
    	}
    	else if(mode==3)
    	{//rotae 180
    		
        	time = Timer.getFPGATimestamp();
    		while(time+.89>Timer.getFPGATimestamp())
        	{
        		RobotMap.driveTrain.drive(-.75, 0);
        	}
    	}
    	
    	else if(mode==4)
    	{//drive towards bin
    		driveTrain.straight();
    		time = Timer.getFPGATimestamp();
    		while(time+2.2>Timer.getFPGATimestamp())
    		{
    			RobotMap.driveTrain.drive(-.4, 0);
    		}
    		while(time+.25> Timer.getFPGATimestamp())
    		{
    			RobotMap.driveTrain.drive(0, 0);
    		}
    		RobotMap.cylinderOpen.set(false);
	    	RobotMap.cylinderClose.set(true);
    		
    	}
    	else if(mode==5)
    	{//raise elevator and back out
    		driveTrain.straight();
    		time = Timer.getFPGATimestamp();
    		while(time+3>Timer.getFPGATimestamp())
    		{
    			while(time+.5>Timer.getFPGATimestamp())
    			{
    				RobotMap.backElevator.set(.5);
    			}
    			RobotMap.driveTrain.drive(.4, 0);
    		}
    		
    		RobotMap.backElevator.set(0);
    		
    		
    		
    	}
    	else if(mode==6)
    	{//rotate to fit
    		time = Timer.getFPGATimestamp();
    		driveTrain.rotate();
    		while(time+1.35>Timer.getFPGATimestamp())
        	{
        		RobotMap.driveTrain.drive(-.3, 0);
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
