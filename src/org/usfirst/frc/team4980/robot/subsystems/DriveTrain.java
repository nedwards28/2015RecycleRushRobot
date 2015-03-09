package org.usfirst.frc.team4980.robot.subsystems;

import org.usfirst.frc.team4980.robot.Robot;
import org.usfirst.frc.team4980.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	SpeedController rearRight = RobotMap.rearRight;
	SpeedController rearLeft = RobotMap.rearLeft;
	SpeedController frontRight = RobotMap.frontRight;
	SpeedController frontLeft = RobotMap.frontLeft;
	public RobotDrive driveTrain = RobotMap.driveTrain;
	
	public void customMecanumDrive_Polar(double magnitude, double direction, double rotation, double weightFactor) {
	    // Normalized for full power along the Cartesian axes.
	    magnitude = limit(magnitude) * Math.sqrt(2.0);
	    // The rollers are at 45 degree angles.
	    double dirInRad = (direction + 45.0) * 3.14159 / 180.0;
	    double cosD = Math.cos(dirInRad);
	    double sinD = Math.sin(dirInRad);
	    
	    int kRearRight = 0;
	    int kFrontRight = 1;
	    int kFrontLeft = 2;
	    int kRearLeft = 3;
	    
	    double kSlowRotation = 0.5;
	    rotation *= kSlowRotation;
	    
	    double wheelSpeeds[] = new double[4];
	    wheelSpeeds[kFrontLeft] = (sinD * magnitude + rotation);
	    wheelSpeeds[kFrontRight] = (cosD * magnitude - rotation);
	    wheelSpeeds[kRearLeft] = (cosD * magnitude + rotation);
	    wheelSpeeds[kRearRight] = (sinD * magnitude - rotation);
	    
	    normalize(wheelSpeeds);
	    byte syncGroup = (byte)0x80;

	    RobotMap.frontLeft.set(wheelSpeeds[kFrontLeft]  * 1, syncGroup);
	    RobotMap.frontRight.set(wheelSpeeds[kFrontRight] * -1 /*Inverted*/ * 1, syncGroup);
	    RobotMap.rearLeft.set(wheelSpeeds[kRearLeft]* 1, syncGroup);
	    RobotMap.rearRight.set(wheelSpeeds[kRearRight] * -1 /*Inverted*/ * 1, syncGroup);
	    
	}
	public void xbox()
	{
		double rightTrigger = Robot.oi.stick.getRawAxis(3);
		double leftTrigger = -1*(Robot.oi.stick.getRawAxis(2));
		double rightX = (Robot.oi.stick.getRawAxis(4));
		double rightY = (Robot.oi.stick.getRawAxis(5));
		double twist = rightTrigger+leftTrigger;
		RobotMap.driveTrain.mecanumDrive_Polar(-rightY, rightX, twist);
		//RobotMap.driveTrain.mecanumDrive_Polar(Robot.oi.stick.getMagnitude(), Robot.oi.stick.getDirectionDegrees(), twist);
	}
	public void logitech()
	{
		
		double magnitude = 0;
    	double twist = 0;
    	double Kg = .0000001;
    	double direction = Robot.oi.stick.getDirectionDegrees();
    	
    	//RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    	//RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);
    	
    	
    	double angle = RobotMap.gyro.getAngle(); // get current heading
      //  myRobot.drive(-1.0, -angle*Kg); // drive towards heading 0
    	
    	//driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    	//driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);
    	
    	if(Math.abs(Robot.oi.stick.getMagnitude())<.4)
    		magnitude = .5*(Robot.oi.stick.getMagnitude());
    	else if(Math.abs(Robot.oi.stick.getMagnitude())<.6 && Math.abs(Robot.oi.stick.getMagnitude())>=.4)
    		magnitude = (2*Robot.oi.stick.getMagnitude())-.6;
    	else
    		magnitude = Robot.oi.stick.getMagnitude();
    	
    	if(Math.abs(Robot.oi.stick.getTwist())>.25)
    	{
    		if(Robot.oi.stick.getTwist()>0)
    			twist=1.3333*Robot.oi.stick.getTwist()-.3333;
    		if(Robot.oi.stick.getTwist()<0)
    			twist=1.3333*Robot.oi.stick.getTwist()+.3333;
    	}
    	//SmartDashboard.putNumber("Magnitude", magnitude);
    	
    	
      //  SmartDashboard.putNumber("Twist", twist);
        
        
       /*
        while(direction<20 && direction>-20)
        {
        	//RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
        	//RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        	if(count == 0)
        		RobotMap.gyro.reset();
        	direction = -angle*Kg;
        	RobotMap.driveTrain.mecanumDrive_Polar(magnitude, direction, twist);
        //	RobotMap.driveTrain.drive(magnitude, -direction);
        	count++;
        	if(Math.abs(Robot.oi.stick.getMagnitude())<.4)
        		magnitude = .5*(Robot.oi.stick.getMagnitude());
        	else if(Math.abs(Robot.oi.stick.getMagnitude())<.6 && Math.abs(Robot.oi.stick.getMagnitude())>=.4)
        		magnitude = (2*Robot.oi.stick.getMagnitude())-.6;
        	else
        		magnitude = Robot.oi.stick.getMagnitude();
        	direction = Robot.oi.stick.getDirectionDegrees();
        	Timer.delay(.004);
        	SmartDashboard.putNumber("Direction", direction);
        }
        */
        
       
    	twist*=.58;
        magnitude *= .85;
        
    	RobotMap.driveTrain.mecanumDrive_Polar(magnitude, direction, twist*Robot.invert);
		
	}
    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void strafe()
    {
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false);
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);//frontRight
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);//rearRight
    }   
    public void straight()
    {
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false);
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);//frontRight
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);//rearRight
    }
    
    public void rotate()
    {
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false);
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);//frontRight
    	RobotMap.driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);//rearRight
    }
    public void defaultDrive()
    {
    	driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false);
    	driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
    	driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);//frontRight
    	driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);//rearRight
    }
    public void invertDrive()
    {
    	driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
    	driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
    	driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);//frontRight
    	driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);//rearRight
    }
    protected static double limit(double num) 
    {
        if (num > 1.0) 
        {
            return 1.0;
        }
        if (num < -1.0) 
        {
            return -1.0;
        }
        return num;
    }
    protected static void normalize(double wheelSpeeds[]) 
    {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);
        int i;
        for (i=1; i<4; i++) {
            double temp = Math.abs(wheelSpeeds[i]);
            if (maxMagnitude < temp) maxMagnitude = temp;
        }
        if (maxMagnitude > 1.0) {
            for (i=0; i<4; i++) {
                wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
            }
        }
    }
}

