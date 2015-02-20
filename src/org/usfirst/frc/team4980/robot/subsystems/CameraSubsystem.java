package org.usfirst.frc.team4980.robot.subsystems;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void getAngle()
    {
    	if(Robot.oi.stick.getPOV()==180)//backwards
    	{
    		RobotMap.YAxis.setAngle(40);
    		RobotMap.XAxis.setAngle(0);
    	}
        if(Robot.oi.stick.getPOV()==0)//forward
    	{
    		RobotMap.XAxis.setAngle(180);
    		RobotMap.YAxis.setAngle(30);
    	}
        if(Robot.oi.stick.getPOV()==270)//left
    	{
    		RobotMap.XAxis.setAngle(0);
    		RobotMap.YAxis.setAngle(40);
    	}
        if(Robot.oi.stick.getPOV()==90)//right
    	{
        	RobotMap.XAxis.setAngle(0);
    		RobotMap.YAxis.setAngle(40);
    	}
    }
}

