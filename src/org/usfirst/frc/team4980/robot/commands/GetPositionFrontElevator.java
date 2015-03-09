package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.RobotMap;
import org.usfirst.frc.team4980.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GetPositionFrontElevator extends Command {

	public double time;
    public GetPositionFrontElevator() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    //	if(RobotMap.hall1.getVoltage()<1)
    	//	end();
    	
    	time = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//grabber.grabber();
    	//SmartDashboard.putNumber("Timer", RobotMap.time);

		while(time+.3>Timer.getFPGATimestamp())
    	{
			//SmartDashboard.putNumber("Time", Timer.getFPGATimestamp());
			if(RobotMap.counter==0)
    			RobotMap.frontElevator.set(-.22);	
    		if(RobotMap.counter==1)
    		{
    			RobotMap.frontElevator.set(-.3);
    		}
    		if(RobotMap.counter==2)
    		{
    			RobotMap.frontElevator.set(-.4);
    		}
    		if(RobotMap.counter==3)
    		{
    			RobotMap.frontElevator.set(-.4);
    		}
    		
    	}
		//RobotMap.suction.set(false);
    	if(RobotMap.hall1.getVoltage()>3)
    	{
    		if(RobotMap.counter==0)
    			RobotMap.frontElevator.set(-.22);	
    		if(RobotMap.counter==1)
    			RobotMap.frontElevator.set(-.3);
    		if(RobotMap.counter==2)
    			RobotMap.frontElevator.set(-.4);
    		
    		
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(RobotMap.hall1.getVoltage()<2)
    	{  
    		RobotMap.frontElevator.set(0);
    		
    		//SmartDashboard.putNumber("Count", RobotMap.counter);
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.frontElevator.set(0);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
