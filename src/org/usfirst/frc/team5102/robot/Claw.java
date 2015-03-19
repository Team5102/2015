package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;

public class Claw extends RobotElement
{
	private DoubleSolenoid clawPiston;
	private elementState clawState;
	
	public Claw()
	{
		super(1);
		clawPiston = new DoubleSolenoid(2,3);
		clawState = elementState.open;
	}
	
	public void setElementState(elementState state)
	{
		clawState = state;
		
		switch(state)
		{
			case closed:
				clawPiston.set(Value.kForward);
				break;
			default:
				clawPiston.set(Value.kReverse);
				break;
		}
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
	
	public void closeClaw()
	{
		setElementState(elementState.closed);
	}
	
	public void openClaw()
	{
		setElementState(elementState.open);
	}
}
