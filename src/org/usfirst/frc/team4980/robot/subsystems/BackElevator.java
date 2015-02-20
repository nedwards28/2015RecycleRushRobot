package org.usfirst.frc.team4980.robot.subsystems;

import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BackElevator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void ultrasonic()
    {
    	//SmartDashboard.putNumber("HedgeHog", RobotMap.hedgehog.getRangeInches());
    }
}

