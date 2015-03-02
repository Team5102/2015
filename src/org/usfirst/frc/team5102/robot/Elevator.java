package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class Elevator extends RobotElement
{
	private CANTalon leftElevatorMotor, rightElevatorMotor;
	
	private Claw claw;
	
	private DigitalInput topElevatorLimit;
	private DigitalInput bottomElevatorLimit;
	private AnalogPotentiometer potentiometer;
	private DigitalInput toteHeightSensor;
	private boolean userInput = true;
	
	public Elevator()
	{
		super(1);	//1 is the  port
		leftElevatorMotor = new CANTalon(10);
		rightElevatorMotor = new CANTalon(11);
		claw = new Claw();
		topElevatorLimit = new DigitalInput(0); //check port
		bottomElevatorLimit = new DigitalInput(1); //check port
		toteHeightSensor = new DigitalInput(2);
		potentiometer = new AnalogPotentiometer(0);
	}
	
	public void raiseElevator(double raiseAmount)
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
				leftElevatorMotor.set(raiseAmount);
				rightElevatorMotor.set(raiseAmount);
				return;
			}
			leftElevatorMotor.set(0.0);
			rightElevatorMotor.set(0.0);
			return;
		}
		
		if(bottomLimitStatus == false)
		{
			System.out.println("bottom limit switch triggered");
			System.out.println(raiseAmount);
			if(raiseAmount < 0.0)
			{
				System.out.println("going up...");
				
				leftElevatorMotor.set(raiseAmount);
				rightElevatorMotor.set(raiseAmount);
				return;
			}
			leftElevatorMotor.set(0.0);
			rightElevatorMotor.set(0.0);
			return;
		}
		leftElevatorMotor.set(raiseAmount);
		rightElevatorMotor.set(raiseAmount);
	}
	
	public void toteHeight()
	{
		userInput = false;
		
		double currentHeight = potentiometer.get();
		
		boolean sensorStatus = true;
		
		if(currentHeight < 0.5)
		{
			while(sensorStatus == true)
			{
				leftElevatorMotor.set(-1.0);
				rightElevatorMotor.set(-1.0);
				System.out.println("motors set to 100%");
				sensorStatus = toteHeightSensor.get();
			}
			
			leftElevatorMotor.set(0.0);
			rightElevatorMotor.set(0.0);
			System.out.println("motors set to 0%");
		}
		
		if(currentHeight > 0.5)
		{
			while(sensorStatus == true)
			{
				leftElevatorMotor.set(1.0);
				rightElevatorMotor.set(1.0);
				System.out.println("motors set to 100%");
				sensorStatus = toteHeightSensor.get();
			}
			
			leftElevatorMotor.set(0.0);
			rightElevatorMotor.set(0.0);
			System.out.println("motors set to 0%");
		}
		
		userInput = true;
	}
	
	public void teleop()
	{
		if (userInput = true)
		{
			raiseElevator(controller.applyDeadband(controller.getRightStickY()));
		}
		
		
		if(controller.getButtonX())
		{ 
			toteHeight();
		}
	}
	
	public void autonomous()
	{
		
	}
}
