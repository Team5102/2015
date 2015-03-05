package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public class Shifter extends RobotElement
{
	private DoubleSolenoid shifter;
	
	public Shifter()
	{
		super(0);
		shifter = new DoubleSolenoid(0,1);
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
