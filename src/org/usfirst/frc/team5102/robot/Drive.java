package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.TalonSRX;

public class Drive
{
	private TalonSRX leftDriveMotor1, leftDriveMotor2, rightDriveMotor1, rightDriveMotor2;
	
	Drive()
	{
		leftDriveMotor1 = new TalonSRX(1);
		leftDriveMotor2 = new TalonSRX(2);
		rightDriveMotor1 = new TalonSRX(3);
		rightDriveMotor2 = new TalonSRX(4);
	}
	
	public void driveSpeed(double leftDriveSpeed, double rightDriveSpeed)
	{
		leftDriveMotor1.set(leftDriveSpeed);
		leftDriveMotor2.set(leftDriveSpeed);
		rightDriveMotor1.set(rightDriveSpeed);
		rightDriveMotor2.set(rightDriveSpeed);
	}
}
