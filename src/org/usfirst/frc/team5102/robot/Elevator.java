package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TalonSRX;

public class Elevator
{
	private TalonSRX leftElevatorMotor, rightElevatorMotor;
	
	private Claw claw;
	
	private DigitalInput topElevatorLimit;
	private DigitalInput bottomElevatorLimit;
	
	public Elevator()
	{
		leftElevatorMotor = new TalonSRX(8);
		rightElevatorMotor = new TalonSRX(9);
		claw = new Claw();
		topElevatorLimit = new DigitalInput(1); //check port
		bottomElevatorLimit = new DigitalInput(2); //check port
	}
	
	public void raiseElevator(double raiseAmount)
	{
		if(topElevatorLimit.get())
		{
			if(raiseAmount < 0)
			{
				leftElevatorMotor.set(raiseAmount);
				rightElevatorMotor.set(raiseAmount);
			}
		}
		
		else if(bottomElevatorLimit.get())
		{
			if(raiseAmount > 0)
			{
				leftElevatorMotor.set(raiseAmount);
				rightElevatorMotor.set(raiseAmount);
			}
		}
		
		else
		{
			leftElevatorMotor.set(raiseAmount);
			rightElevatorMotor.set(raiseAmount);
		}
		
	}
	
	//what about lower limit?
}
