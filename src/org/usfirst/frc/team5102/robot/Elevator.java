package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TalonSRX;

public class Elevator extends RobotElement
{
	private TalonSRX leftElevatorMotor, rightElevatorMotor;
	
	private Claw claw;
	
	private DigitalInput topElevatorLimit;
	private DigitalInput bottomElevatorLimit;
	
	public Elevator()
	{
		super(1);	//1 is the  port
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
				return;
			}
		}
		
		if(bottomElevatorLimit.get())
		{
			if(raiseAmount > 0)
			{
				leftElevatorMotor.set(raiseAmount);
				rightElevatorMotor.set(raiseAmount);
				return;
			}
			
			leftElevatorMotor.set(raiseAmount);
			rightElevatorMotor.set(raiseAmount);
		}
	}
	
	public void teleop()
	{
		if(controller.getLeftStickY() != 0 || controller.getRightStickY() != 0)
		{
			if(controller.applyDeadband(controller.getRightStickY()) != 0)
			{
				raiseElevator(controller.getRightStickY());
			}
		}
	}
	
	public void autonomous()
	{
		
	}
}
