package org.usfirst.frc.team5102.robot;

import org.usfirst.frc.team5102.robot.RobotElement.elementState;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;

public class Intake extends RobotElement
{
	private DoubleSolenoid intakePiston;
	private Talon leftIntakeMotor, rightIntakeMotor;
	private elementState intakeState;
	
	public Intake()
	{
		super(1);
		intakePiston = new DoubleSolenoid(7,6);
		leftIntakeMotor = new Talon(4);
		rightIntakeMotor = new Talon(5);
		
		intakeState = elementState.open;
	}
	
	public void setElementState(elementState state)
	{
		intakeState = state;
		
		switch(state)
		{
			case closed:
				intakePiston.set(Value.kForward);
				break;
			default:
				intakePiston.set(Value.kReverse);
				break;
		}
	}
	
	public void closeIntake()
	{
		setElementState(elementState.closed);
	}
	
	public void openIntake()
	{
		setElementState(elementState.open);
	}
	
	public void intakeMotors(boolean intakeMotors)
	{
		if(intakeMotors)
		{
			leftIntakeMotor.set(0.5);
			rightIntakeMotor.set(0.5);
		}
		
		if(!intakeMotors)
		{
			leftIntakeMotor.set(0.0);
			rightIntakeMotor.set(0.0);
		}
	}
}
