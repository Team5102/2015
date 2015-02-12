package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;

public class Drive
{
	private TalonSRX frontRightDrive, frontLeftDrive, backRightDrive, backLeftDrive;
	
	private RobotDrive robotDrive;
	
	Drive()
	{
		frontRightDrive = new TalonSRX(1);
		frontLeftDrive = new TalonSRX(2);
		backLeftDrive = new TalonSRX(3);
		backRightDrive = new TalonSRX(4);
		robotDrive = new RobotDrive(frontLeftDrive, backLeftDrive, frontRightDrive, backRightDrive);
	}
	
	public void drive(double magnitude, double curve)
	{
		robotDrive.arcadeDrive(magnitude, curve);
	}
}
