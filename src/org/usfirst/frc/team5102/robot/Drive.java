package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class Drive extends RobotElement
{
	private Talon frontRightDrive, frontLeftDrive, backRightDrive, backLeftDrive;
	
	private RobotDrive robotDrive;
	
	Drive()
	{
		super(0);	//0 is the controller port
		frontRightDrive = new Talon(1);
		frontLeftDrive = new Talon(2);
		backLeftDrive = new Talon(3);
		backRightDrive = new Talon(4);
		robotDrive = new RobotDrive(frontLeftDrive, backLeftDrive, frontRightDrive, backRightDrive);
	}
	
	public void drive(double magnitude, double curve)
	{
		robotDrive.arcadeDrive(magnitude, curve);
	}
	
	public void teleop()
	{
		
	}
	
	public void autonomous()
	{
		
	}
}
