package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;

public class Elevator extends RobotElement
{
	private Talon elevatorMotor;
	
	private DigitalInput topElevatorLimit;
	private DigitalInput bottomElevatorLimit;
	private AnalogPotentiometer potentiometer;
	private DigitalInput toteHeightSensor;
	private boolean userInput = true;
	
	private Claw claw;
	private Intake intake;
	
	public Elevator()
	{
		super(1);	//1 is the  port
		
		claw = new Claw();
    	intake = new Intake();
    	
		elevatorMotor = new Talon(6);
		topElevatorLimit = new DigitalInput(0); //check port
		bottomElevatorLimit = new DigitalInput(1); //check port
		toteHeightSensor = new DigitalInput(2);
		potentiometer = new AnalogPotentiometer(0);
		
	}
	
	public boolean raiseElevator(double raiseAmount)
	{
		boolean topLimitStatus = topElevatorLimit.get();
		boolean bottomLimitStatus = bottomElevatorLimit.get();
		
		if(topLimitStatus == false)
		{
			System.out.println("top limit switch triggered");
			System.out.println(raiseAmount);
			if(raiseAmount > 0.0)
			{
				System.out.println("going down...");
				elevatorMotor.set(raiseAmount);
				return false;
			}
			elevatorMotor.set(0.0);
			return true;
		}
		
		if(bottomLimitStatus == false)
		{
			System.out.println("bottom limit switch triggered");
			System.out.println(raiseAmount);
			if(raiseAmount < 0.0)
			{
				System.out.println("going up...");
				
				elevatorMotor.set(raiseAmount);
				return false;
			}
			elevatorMotor.set(0.0);
			return true;
		}
		elevatorMotor.set(raiseAmount);
		return false;
	}
	
	public void defaultPosition()
	{
		claw.closeClaw(false);
		intake.closeIntake(true);
		
		double currentHeight = potentiometer.get();
		
		boolean sensorStatus = true;
		
		while(sensorStatus == true)
		{
			if(currentHeight < 0.5)
			{
				raiseElevator(-1.0);
			}
			
			if(currentHeight > 0.5)
			{
				raiseElevator(1.0);
			}
			
			sensorStatus = toteHeightSensor.get();
		}
		elevatorMotor.set(0.0);
	}
	
	public void gotoBottom()
	{
		while(raiseElevator(-1.0) == false)
		{
			
		}
	}
	
	public void loadTote()
	{
		intake.closeIntake(true);
		
		Timer temp = new Timer();
		
		while(temp.get() < 1)
		{
			
		}
		
		temp.reset();
		
		gotoBottom();
	
		intake.closeIntake(false);
	
		defaultPosition();
	}
	
	public void teleop()
	{
		//==========Elevator==========
		if (userInput = true)
		{
			raiseElevator(controller.applyDeadband(controller.getLeftStickY()));
		}
		
		if(controller.getButtonX())
		{ 
			userInput = false;
			
			defaultPosition();
			
			userInput = true;
		}
		
		//==========Claw==========
		
		if(controller.getLeftTriggerButton())
		{ 
			claw.closeClaw(true);
		}
		
		if(controller.getRightTriggerButton())
		{ 
			claw.closeClaw(false);
		}
		
		//==========Intake==========
		
		if(controller.getLeftTriggerAxis() > 0.5)
		{ 
			intake.closeIntake(true);
		}
		
		if(controller.getRightTriggerAxis() > 0.5)
		{ 
			intake.closeIntake(false);
		}
		
		if(controller.getButtonY())
		{ 
			intake.intakeMotors(true);
		}
		
		if(controller.getButtonB())
		{ 
			intake.intakeMotors(false);
		}
		
		//==========Other==========
		
		if(controller.getButtonA())
		{
			userInput = false;
			
			loadTote();
			
			userInput = true;
		}
	}
	
	public void autonomous()
	{
		
	}
}
