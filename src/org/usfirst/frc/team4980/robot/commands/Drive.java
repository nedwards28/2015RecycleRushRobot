package org.usfirst.frc.team4980.robot.commands;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends Command {

    public Drive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	/*
    	double magnitude = 0;
    	double twist = 0;
    	if(Math.abs(Robot.oi.stick.getMagnitude())>.15)
    		magnitude = 1.264*(Robot.oi.stick.getMagnitude());
    	if(Math.abs(Robot.oi.stick.getTwist())>.25)
    	{
    		if(Robot.oi.stick.getTwist()>0)
    			twist=1.3333*Robot.oi.stick.getTwist()-.3333;
    		if(Robot.oi.stick.getTwist()<0)
    			twist=1.3333*Robot.oi.stick.getTwist()+.3333;
    	}
    	SmartDashboard.putNumber("Magnitude", magnitude);
    	SmartDashboard.putNumber("Direction", Robot.oi.stick.getDirectionDegrees());
        SmartDashboard.putNumber("Twist", twist);
    	RobotMap.driveTrain.mecanumDrive_Polar(magnitude, Robot.oi.stick.getDirectionDegrees(), twist);
    	*/
    	//Robot.driveTrain.xbox();
    	Robot.driveTrain.logitech();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
