package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Shifter
{
	private DoubleSolenoid shifter;
	
	public Shifter()
	{
		shifter = new DoubleSolenoid(3,4);
	}
	
	public void shiftGears(int gear)
	{
		if(gear == 1)
		{
			shifter.set(DoubleSolenoid.Value.kForward);
		}
		
		else if(gear == 2)
		{
			shifter.set(DoubleSolenoid.Value.kReverse);
		}
		
		else
		{
			System.out.println(gear + " is not a valid gear");
		}
	}
}
