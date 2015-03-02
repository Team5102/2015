package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;

public class Claw extends RobotElement
{
	private DoubleSolenoid clawPiston;
	
	public Claw()
	{	
		super(1);
		clawPiston = new DoubleSolenoid(7,6);
	}
		
	public int getClawState()
	{
		int clawState = 0;
		
		if(clawPiston.get() == Value.kForward)
		{
			clawState = 1;
		}
		else if(clawPiston.get() == Value.kReverse)
		{
			clawState = -1;
		}
		return clawState;
	}
	
	public void closeClaw(boolean closeClaw)
	{
		if(closeClaw == true)
		{
			clawPiston.set(DoubleSolenoid.Value.kForward);
		}
		
		else if(closeClaw == false)
		{
			clawPiston.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
	public void teleop()
	{
		if(controller.getLeftTrigger())
		{ 
			closeClaw(true);
		}
		
		if(controller.getRightTrigger())
		{ 
			closeClaw(false);
		}
	}
	
	public void autonomous()
	{
		
	}
}
