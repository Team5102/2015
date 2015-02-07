package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.TalonSRX;

public class Elevator
{
	private TalonSRX leftElevatorMotor, rightElevatorMotor;
	
	Elevator()
	{
		leftElevatorMotor = new TalonSRX(8);
		rightElevatorMotor = new TalonSRX(9);
	}
	
	public void raiseElevator(double raiseAmount)
	{
		leftElevatorMotor.set(raiseAmount);
		rightElevatorMotor.set(raiseAmount);
	}
}
