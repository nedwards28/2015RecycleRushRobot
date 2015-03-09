package org.usfirst.frc.team4980.robot.subsystems;

import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BackElevatorReach extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public SpeedController backElevatorReachMotor = RobotMap.backElevatorReach;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void setSpeed(double axis)
    {
    	
    	RobotMap.backElevatorReach.set(-axis);
    }
    public void up(double speed)
    {
    	this.backElevatorReachMotor.set(-speed);
    }
    public void down(double speed)
    {
    	this.backElevatorReachMotor.set(speed);
    }
}

