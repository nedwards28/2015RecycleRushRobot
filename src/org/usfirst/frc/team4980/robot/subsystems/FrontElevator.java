package org.usfirst.frc.team4980.robot.subsystems;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FrontElevator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public SpeedController frontElevatorMotor = RobotMap.frontElevator;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void isItCloseEnoughBruhh()
    {
    	if(RobotMap.sonic.getVoltage()<.315)
    	{
    		
    		double time = Timer.getFPGATimestamp();
    		while(time+.05>Timer.getFPGATimestamp())
    		{
    			RobotMap.frontElevator.set(.4);
    		}
    		
    	}
    	//Robot.oi.frontElevatorUp.whileActive(getCurrentCommand());
    	else
    		RobotMap.frontElevator.set(0);
    }
    public void pickupPosition()
    {
    	while(RobotMap.hall1.getVoltage()>3)
    	{
    		boolean direction=true;
    		while(RobotMap.hall1.getVoltage()>3 && direction == true)
    		{
    			
    				RobotMap.frontElevator.set(.2);
    		}
    		
    		while(RobotMap.hall1.getVoltage()>3 && direction == false)
    		{
    			RobotMap.frontElevator.set(-.1);
    		}  	
    		direction=!direction;
    		
    	}
    	
    	RobotMap.frontElevator.set(0);
    }
    public boolean checkPosition()
    {
    	if(RobotMap.hall1.getVoltage()<1)
    		return true;
    	
    	return false;
    }
    public boolean pickupSucceeded()
    {
    	
    	
    	return false;
    }
    public void drop()
    {
    	
    }
}

