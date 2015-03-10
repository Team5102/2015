package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;

public class Intake extends RobotElement
{
	private DoubleSolenoid intakePiston;
	private Talon leftIntakeMotor, rightIntakeMotor;
	
	public Intake()
	{
		super(1);
		intakePiston = new DoubleSolenoid(7,6);
		leftIntakeMotor = new Talon(4);
		rightIntakeMotor = new Talon(5);
	}
	
	public void closeIntake(boolean closeIntake)
	{
		if(closeIntake == true)
		{
			intakePiston.set(Value.kForward);
		}
		
		else if(closeIntake == false)
		{
			intakePiston.set(Value.kReverse);
		}
	}
	
	public void intakeMotors(boolean intakeMotors)
	{
		if(intakeMotors == true)
		{
			leftIntakeMotor.set(0.5);
			rightIntakeMotor.set(0.5);
		}
		
		if(intakeMotors == false)
		{
			leftIntakeMotor.set(0.0);
			rightIntakeMotor.set(0.0);
		}
	}
}
