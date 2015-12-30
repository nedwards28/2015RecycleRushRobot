
package org.usfirst.frc.team4980.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4980.robot.commands.AllThreeTotesAuto;
import org.usfirst.frc.team4980.robot.commands.Auto;
import org.usfirst.frc.team4980.robot.commands.ClawOpen;
import org.usfirst.frc.team4980.robot.commands.ContainerFromStepAuto;
import org.usfirst.frc.team4980.robot.commands.ContainerOnlyAuto;
import org.usfirst.frc.team4980.robot.commands.Drive;
import org.usfirst.frc.team4980.robot.commands.GatherContainer;
import org.usfirst.frc.team4980.robot.commands.GetPositionFrontElevator;
import org.usfirst.frc.team4980.robot.commands.GoBackOnePositionFrontElevator;
import org.usfirst.frc.team4980.robot.commands.GrabTwoContainersAuto;
import org.usfirst.frc.team4980.robot.commands.GrayTotesAuto;
import org.usfirst.frc.team4980.robot.commands.ToteThenTrash;
import org.usfirst.frc.team4980.robot.commands.ServoUp;
import org.usfirst.frc.team4980.robot.commands.ToteOnlyAuto;
import org.usfirst.frc.team4980.robot.commands.backElevatorReachDown;
import org.usfirst.frc.team4980.robot.commands.backElevatorReachUp;
import org.usfirst.frc.team4980.robot.commands.frontElevatorDown;
import org.usfirst.frc.team4980.robot.commands.frontElevatorUp;
import org.usfirst.frc.team4980.robot.commands.getContainerFromStep;
import org.usfirst.frc.team4980.robot.subsystems.BackElevator;
import org.usfirst.frc.team4980.robot.subsystems.BackElevatorReach;
import org.usfirst.frc.team4980.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4980.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team4980.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team4980.robot.subsystems.FrontElevator;
import org.usfirst.frc.team4980.robot.subsystems.Claw;
import org.usfirst.frc.team4980.robot.subsystems.vacuum;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public Command autonomousCommand, drive;
	public static SendableChooser autoChooser, stepOrNah;
	public static OI oi;
	
	public static ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static DriveTrain driveTrain = new DriveTrain();
	public static FrontElevator frontElevator = new FrontElevator();
	public static CameraSubsystem cameraSubsystem = new CameraSubsystem();
	public static BackElevator backElevator = new BackElevator();
	public static BackElevatorReach backElevatorReach = new BackElevatorReach();
	public static Claw claw = new Claw();
	public static vacuum vacuum = new vacuum();
	public static int invert;
	public static boolean clawState, actuateSuctionState;
	//public static boolean button2;
	public static Command button1 = null, button5 = null;
    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
    	RobotMap.init();
    	invert = 1;
    	clawState = true;
    	actuateSuctionState = true;
    	//button2 = true;
		
    	//OI Must be constructed after subsytems. If the OI creates Commands
    	//(a likely scenario) subsystems are not guaranteed to be constructed yet.
    	//This would result in their Requires() call may grab a null pointer.  Bad, dont move it!
    	oi = new OI();
    	
        // instantiate the command used for the autonomous period
        drive = new Drive();
        autoChooser = new SendableChooser();
        stepOrNah = new SendableChooser();
        stepOrNah.addDefault("Scoring Platform", true);
        stepOrNah.addObject("No Scoring Platform", false);
        
        
        autoChooser.addDefault("Nothing yet", new Auto());
        autoChooser.addObject("Tote Then Trash", new ToteThenTrash());
        autoChooser.addObject("Tote Only", new ToteOnlyAuto());
        autoChooser.addObject("Container Only", new ContainerOnlyAuto());
        autoChooser.addObject("All three Totes", new AllThreeTotesAuto());
        autoChooser.addObject("Container from step", new getContainerFromStep());
        autoChooser.addObject("Two Containers", new GrabTwoContainersAuto());
        autoChooser.addObject("Container from Step", new ContainerFromStepAuto());
        autoChooser.addObject("Two Gray Totes", new GrayTotesAuto());
        
        SmartDashboard.putData("Chooser", autoChooser);
        SmartDashboard.putData("StepChooser", stepOrNah);
        
        //Setup the Gryo
        if(RobotMap.gyro != null)
        {
        	//Once initialized, the getAngle() method of the Gyro object will return
        	//the number of degrees of rotation (heading) as a positive or negative number
        	//relative to the robots position during the calibration period. 
        	//The zero heading can be reset at any time by calling the reset() method on the Gyro object.
        	RobotMap.gyro.initGyro();
        	
        	//2014 Gyro ADW22307 can measure up to 250/second of rotation. 
        	//Nominal output is 2.5V at standstill, plus 7mV//second
        	//Sensitivity = volts//sec therefore 7mV//sec would be set at 0.007V//sec
        	RobotMap.gyro.setSensitivity(0.007);//0.001655
        	RobotMap.gyro.reset();
        }
        
        RobotMap.driveTrain.drive(0, 0);
    	RobotMap.frontElevator.set(0);
    	RobotMap.backElevator.set(0);
    	RobotMap.backElevatorReach.set(0);
    	RobotMap.clawMotor.set(0);
    	//driveTrain.defaultDrive();
    	RobotMap.driveTrain.drive(0, 0);
    	
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
     //   if (autonomousCommand != null) autonomousCommand.start();
    	
    	autonomousCommand = (Command) autoChooser.getSelected();
    	autonomousCommand.start();   
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        RobotMap.counter=0;
       // SmartDashboard.putNumber("Count", RobotMap.counter);
       // RobotMap.elevatorSpeed.set(.1);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        drive.start();
        cameraSubsystem.getAngle();
        SmartDashboard.putNumber("Hall effect", RobotMap.hall1.getVoltage());
        Robot.backElevator.setSpeed(Robot.oi.xbox.getRawAxis(1));
        Robot.backElevatorReach.setSpeed(Robot.oi.xbox.getRawAxis(5));
		SmartDashboard.putNumber("Counter", RobotMap.counter);
		
		
		
		
		
		/*if(RobotMap.sonic2.getVoltage()<.3 && RobotMap.cylinderOpen.get()==true)
		{
			
			RobotMap.cylinderOpen.set(false);
    		RobotMap.cylinderClose.set(true);
		}
		if(RobotMap.sonic2.getVoltage()<.3)
		{
			
			RobotMap.cylinderOpen.set(false);
    		RobotMap.cylinderClose.set(true);
		}*/
        if(invert==-1)	
        {
        	button5 = new backElevatorReachUp();
        }
        if(invert==1)
        {
        	//button
        	button5 = new frontElevatorUp();
        }
       

        //SmartDashboard.putNumber("Hedgehog", RobotMap.hedgehog.getRangeInches());
       // SmartDashboard.putNumber("Hall", RobotMap.hall1.getVoltage());
      //  SmartDashboard.putNumber("Axis", Robot.oi.stick.getPOV());
        SmartDashboard.putNumber("Gyro", RobotMap.gyro.getAngle());
        SmartDashboard.putNumber("Ultra", RobotMap.sonic.getVoltage());
        SmartDashboard.putNumber("Back Ultra", RobotMap.sonic2.getVoltage());
    }	
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
