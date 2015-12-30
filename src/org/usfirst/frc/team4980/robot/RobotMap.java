package org.usfirst.frc.team4980.robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.AnalogInput;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap 
{
    public static SpeedController  rearLeft, rearRight, frontLeft, frontRight, frontElevator, backElevator, backElevatorReach, clawMotor;
    public static RobotDrive driveTrain;
    public static Gyro gyro;
    public static Ultrasonic hedgehog;
    public static BuiltInAccelerometer accel;
    public static Solenoid cylinderOpen, cylinderClose, suction, channelB, actuateOut, actuateIn;
    public static Relay relay;
    public static AnalogInput hall1;
    public static AnalogInput sonic, sonic2;
    public static double counter, time;
    public static Servo YAxis, XAxis;
    
  //  public static AxisCamera camera;
    
    public static void init()
    {
       // camera= new AxisCamera("10.49.80.20");
    	counter = 0;
    	time = 0;
    	rearRight = new Talon(0);
    	frontRight = new Talon(1);       
    	frontLeft = new Talon(2);
    	rearLeft = new Talon(3);
    	frontElevator = new Talon(5);
    	backElevator = new Talon(4);
    	backElevatorReach = new Talon(8);
    	clawMotor = new Talon(9);
    	driveTrain = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
    	//    driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
      	gyro = new Gyro(0); 
    	YAxis = new Servo(7);
    	XAxis = new Servo(6);
    	cylinderOpen = new Solenoid(1);
    	cylinderClose = new Solenoid(2);
    	actuateOut = new Solenoid(4);
    	actuateIn = new Solenoid(5);
    	channelB = new Solenoid(0);
    	suction = new Solenoid(3);
    	//solenoid2 = new Solenoid(1);
    	hall1 = new AnalogInput(1);
    	sonic = new AnalogInput(2);
    	sonic2 = new AnalogInput(3);
    	hedgehog = new Ultrasonic(0, 1);
    	driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false);
    	driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
    	driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);//frontRight
    	driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);//rearRight
    	
    	suction.set(false);
    	channelB.set(true);
    	
    	driveTrain.setSafetyEnabled(true);
    	driveTrain.setExpiration(.1);  
    	driveTrain.setSensitivity(1);
    	driveTrain.setMaxOutput(1.0);
    	
    	
    }
}
