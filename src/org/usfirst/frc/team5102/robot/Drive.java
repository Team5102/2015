package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class Drive extends RobotElement
{
	private Talon frontRightDrive, frontLeftDrive, backRightDrive, backLeftDrive;
	
	private RobotDrive robotDrive;
	
	private Shifter shifter;
	
	Drive()
	{
		super(0);	//0 is the controller port
		
		shifter = new Shifter();
		
		frontRightDrive = new Talon(0);
		frontLeftDrive = new Talon(2);
		backLeftDrive = new Talon(3);
		backRightDrive = new Talon(1);
		robotDrive = new RobotDrive(frontLeftDrive, backLeftDrive, frontRightDrive, backRightDrive);
	}
	
	public void drive(double magnitude, double curve)
	{
		robotDrive.arcadeDrive(magnitude, curve);
	}
	
	public void teleop()
	{
		//==========Drive==========
		
		drive(controller.getLeftStickY(), -controller.getRightStickX());
		
		//==========Shifter==========
		
		if(controller.getLeftTriggerButton())
		{ 
			shifter.shiftGears(1);
			System.out.println("1st gear");
		}
		
		if(controller.getRightTriggerButton())
		{ 
			shifter.shiftGears(2);
			System.out.println("2nd gear");
		}
	}
	
	public void autonomous()
	{
		
	}
}
