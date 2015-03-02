package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;

public class Elevator extends RobotElement
{
	private Talon elevatorMotor;
	
	private Claw claw;
	
	private DigitalInput topElevatorLimit;
	private DigitalInput bottomElevatorLimit;
	private AnalogPotentiometer potentiometer;
	private DigitalInput toteHeightSensor;
	private boolean userInput = true;
	
	public Elevator()
	{
		super(1);	//1 is the  port
		elevatorMotor = new Talon(6);
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
				elevatorMotor.set(raiseAmount);
				return;
			}
			elevatorMotor.set(0.0);
			return;
		}
		
		if(bottomLimitStatus == false)
		{
			System.out.println("bottom limit switch triggered");
			System.out.println(raiseAmount);
			if(raiseAmount < 0.0)
			{
				System.out.println("going up...");
				
				elevatorMotor.set(raiseAmount);
				return;
			}
			elevatorMotor.set(0.0);
			return;
		}
		elevatorMotor.set(raiseAmount);
	}
	
	public void toteHeight()
	{
		userInput = false;
		
		double currentHeight = potentiometer.get();
		
		boolean sensorStatus = true;
		
		while(sensorStatus == true)
		{
			if(currentHeight < 0.5)
			{
				elevatorMotor.set(-1.0);
			}
			
			if(currentHeight > 0.5)
			{
				elevatorMotor.set(1.0);
			}
			
			sensorStatus = toteHeightSensor.get();
		}
		elevatorMotor.set(0.0);
		
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
