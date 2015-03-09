package org.usfirst.frc.team4980.robot.subsystems;

import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Claw extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
     
    }
    public void	clawOpen()
    {
    	RobotMap.cylinderOpen.set(true);
    	RobotMap.cylinderClose.set(false);
    	
    }
    public void	clawClose()
    {
    	
    	RobotMap.cylinderOpen.set(false);
    	RobotMap.cylinderClose.set(true);
    }
}

