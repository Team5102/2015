package org.usfirst.frc.team5102.robot.util;

import edu.wpi.first.wpilibj.Joystick;

public class Xbox 
{
	private Joystick stick;
	
	private double deadband = 0.05;
	
	private int i = 0;
	
	public Xbox(int port)
	{
		stick = new Joystick(port);
	}
	
	public double getLeftStickX()
	{
		return stick.getRawAxis(0);
	}
	
	public double getLeftStickY()
	{
		return stick.getRawAxis(1);
	}
	
	public double getRightStickX()
	{
		return stick.getRawAxis(4);
	}
	
	public double getRightStickY()
	{
		return stick.getRawAxis(5);
	}
	
	public boolean getButtonX()
	{
		return stick.getRawButton(3);
	}
	
	public boolean getButtonA()
	{
		return stick.getRawButton(1);
	}
	
	public boolean getButtonB()
	{
		return stick.getRawButton(2);
	}
	
	public boolean getButtonY()
	{
		return stick.getRawButton(4);
	}
	
	public boolean getLeftTrigger()
	{
		return stick.getRawButton(5);
	}
	
	public boolean getRightTrigger()
	{
		return stick.getRawButton(6);
	}
	
	public double applyDeadband(double magnitude, double deadband)
	{
		
		if(Math.abs(magnitude) > deadband)
		{
			return magnitude;
		}
		
		return 0.00;
	}
	
	public double applyDeadband(double magnitude)
	{
		return applyDeadband(magnitude, deadband);
	}
	
}
