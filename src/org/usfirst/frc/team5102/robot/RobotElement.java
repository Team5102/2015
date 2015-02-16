package org.usfirst.frc.team5102.robot;

import org.usfirst.frc.team5102.robot.util.Xbox;

import edu.wpi.first.wpilibj.Timer;

public class RobotElement
{
	private Timer localTimer;
	
	protected Xbox controller;
	
	RobotElement(int port)
	{
		controller = new Xbox(port);
	}
	
	public void teleop()
	{
		
	}
	public void autonomous()
	{
		
	}
}
