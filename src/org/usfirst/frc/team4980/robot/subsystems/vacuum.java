package org.usfirst.frc.team4980.robot.subsystems;

import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class vacuum extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    double time = Timer.getFPGATimestamp();
    public void vacuumOn()
    {
		while(time+1.5>Timer.getFPGATimestamp() )
		{
			//RobotMap.cylinder.set(true);
			RobotMap.suction.set(true);
		}
    }
	//RobotMap.cylinder.set(false);
	//RobotMap.time =Timer.getFPGATimestamp();
}

