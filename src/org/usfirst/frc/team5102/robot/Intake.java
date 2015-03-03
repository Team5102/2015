package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

public class Intake extends RobotElement
{
	private Solenoid intakePiston;
	private Talon leftIntakeMotor, rightIntakeMotor;
	
	public Intake()
	{
		super(1);
		intakePiston = new Solenoid(7);
		leftIntakeMotor = new Talon(4);
		rightIntakeMotor = new Talon(5);
	}
	
	public void closeIntake(boolean closeIntake)
	{
		intakePiston.set(closeIntake);
	}
	
	public void intakeMotors()
	{
		
	}
	
	public void teleop()
	{
		if(controller.getButtonA())
		{ 
			closeIntake(true);
		}
		
		if(controller.getButtonB())
		{ 
			closeIntake(false);
		}
	}
	
	public void autonomous()
	{
		
	}
}
