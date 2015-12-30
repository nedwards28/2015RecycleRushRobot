package org.usfirst.frc.team4980.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4980.robot.commands.ActuateSuction;
import org.usfirst.frc.team4980.robot.commands.ActuateSuctionWhileHeld;
import org.usfirst.frc.team4980.robot.commands.SuctionCupGrabber;
import org.usfirst.frc.team4980.robot.commands.chooseButton1;
import org.usfirst.frc.team4980.robot.commands.chooseButton2;
import org.usfirst.frc.team4980.robot.commands.chooseButton5;
import org.usfirst.frc.team4980.robot.commands.frontElevatorUp;
import org.usfirst.frc.team4980.robot.commands.frontElevatorDown;
import org.usfirst.frc.team4980.robot.commands.backElevatorUp;
import org.usfirst.frc.team4980.robot.commands.backElevatorDown;
import org.usfirst.frc.team4980.robot.commands.backElevatorReachDown;
import org.usfirst.frc.team4980.robot.commands.backElevatorReachUp;
import org.usfirst.frc.team4980.robot.commands.ClawOpen;
import org.usfirst.frc.team4980.robot.commands.GetPositionFrontElevator;
import org.usfirst.frc.team4980.robot.commands.InvertDrive;
import org.usfirst.frc.team4980.robot.commands.chooseButton3;
import org.usfirst.frc.team4980.robot.commands.strafeLeft;
import org.usfirst.frc.team4980.robot.commands.strafeRight;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
	
	
	public Joystick stick, attack3, xbox;
	public JoystickButton button5;
	public JoystickButton button3;
	public JoystickButton button6;
	public JoystickButton button4;
	public JoystickButton setFrontElevatorPosition;
	public JoystickButton button8;
	public JoystickButton button7;
	public JoystickButton button1;
	public JoystickButton suction, invertDrive;
	public JoystickButton button2;
	public JoystickButton button12, buttonA;
	public JoystickButton rightBumper;
	
	
	
    public OI()
    {
    	stick = new Joystick(0);
    	attack3 = new Joystick(1);
    	xbox = new Joystick(2);
    	//forwardElevatorButton = new JoystickButton(stick, 1);
    	button5 = new JoystickButton(stick, 5);
    	button3 = new JoystickButton(stick, 3);
    	button6 = new JoystickButton(stick, 6);
    	button4 = new JoystickButton(stick, 4);
    	button12 = new JoystickButton(stick, 12);
    	button2 = new JoystickButton(stick, 2);
    	buttonA = new JoystickButton(xbox, 1);
    	rightBumper = new JoystickButton(xbox, 6);
    	//this.setFrontElevatorPosition = new JoystickButton(stick, 11);
    	button8 = new JoystickButton(stick, 8);
    	button7 = new JoystickButton(stick, 7);
    	button1 = new JoystickButton(stick, 1);
    	
    	suction = new JoystickButton(stick, 10);
    	invertDrive = new JoystickButton(stick, 11);
    	button12.whenPressed(new GetPositionFrontElevator());
    	buttonA.whenPressed(new ClawOpen());
    	
    	invertDrive.whenReleased(new InvertDrive());
    	
    	button1.whileHeld(new ActuateSuction());
    	//button2.whileHeld(new ActuateSuction());
    	button5.whileHeld(new chooseButton5());
    	button3.whileHeld(new chooseButton3());
    	//button6.whileHeld(new SuctionCupGrabber());
    	//button4.whileHeld(new backElevatorDown());
    	
    	
    	//button4.whileHeld(new ActuateSuctionWhileHeld());
    	rightBumper.whileHeld(new SuctionCupGrabber() );
    	
    	//button4.whenPressed(new ActuateSuction());
    	
    	//button8.whileHeld(new strafeRight());
    	//button7.whileHeld(new strafeLeft());
    	//this.setFrontElevatorPosition.whenReleased(new GetPositionFrontElevator());
    	
    	//suction.whileHeld(new SuctionCupGrabber());
    	
    	
    	//SmartDashboard.putNumber("stick", stick.getAxisCount());
    	//SmartDashboard.putData("EForward", new ElevatorForward());
    	//SmartDashboard.putData("EReverse", new AnalProbe());
    }
    
    
    public Joystick getJoystick1()
    {
    	return stick;
    }
}

