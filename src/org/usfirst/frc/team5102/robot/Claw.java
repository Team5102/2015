package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Claw
{
	private DoubleSolenoid clawPiston;
	
	public Claw()
	{	
		clawPiston = new DoubleSolenoid(1,2);
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
}
