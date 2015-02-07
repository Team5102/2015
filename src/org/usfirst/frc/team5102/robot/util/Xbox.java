package org.usfirst.frc.team5102.robot.util;

import edu.wpi.first.wpilibj.Joystick;

public class Xbox 
{
	private Joystick stick;
	
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
		return stick.getRawButton(2);
	}
	
	public boolean getButtonA()
	{
		return stick.getRawButton(0);
	}
	
	public boolean getButtonB()
	{
		return stick.getRawButton(1);
	}
	
}
