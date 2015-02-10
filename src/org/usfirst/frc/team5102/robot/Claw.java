package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Claw
{
	private DoubleSolenoid clawPiston;
	
	public Claw()
	{	
		clawPiston = new DoubleSolenoid(1,2);
	}
		
	public boolean getClawState()
	{
		return clawPiston.equals(true);
	}
}
