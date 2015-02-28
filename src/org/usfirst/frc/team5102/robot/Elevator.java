package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class Elevator extends RobotElement
{
	private CANTalon leftElevatorMotor, rightElevatorMotor;
	
	private Claw claw;
	
	private DigitalInput topElevatorLimit;
	private DigitalInput bottomElevatorLimit;
	
	public Elevator()
	{
		super(1);	//1 is the  port
		leftElevatorMotor = new CANTalon(10);
		rightElevatorMotor = new CANTalon(11);
		claw = new Claw();
		topElevatorLimit = new DigitalInput(0); //check port
		bottomElevatorLimit = new DigitalInput(1); //check port
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
	
	public void teleop()
	{
		raiseElevator(controller.applyDeadband(controller.getRightStickY()));
	}
	
	public void autonomous()
	{
		
	}
}
