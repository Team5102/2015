package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;

public class Elevator extends RobotElement
{
	private Talon elevatorMotor;
	
	private DigitalInput topElevatorLimit;
	private DigitalInput bottomElevatorLimit;
	private AnalogPotentiometer potentiometer;
	private DigitalInput toteHeightSensor;
	private boolean userInput = true;
	private boolean practiceMode = false;
	int counter = 0;
	
	private Timer intakeTimer, elevatorMoveTimer;
	
	private double timeToLowerElevator = 2.0;
	private double elevatorLowerSpeed = -.75;
	
	private Claw claw;
	private Intake intake;
	
	enum position
	{
		top,
		bottom,
		tote
	}
	
	public Elevator()
	{
		super(1);	//1 is the  port
		
		claw = new Claw();
    	intake = new Intake();
    	
		elevatorMotor = new Talon(6);
		topElevatorLimit = new DigitalInput(0); //check port
		bottomElevatorLimit = new DigitalInput(1); //check port
		toteHeightSensor = new DigitalInput(2);
		potentiometer = new AnalogPotentiometer(0);
		intakeTimer = new Timer();
		elevatorMoveTimer = new Timer();
	}
	
	public boolean raiseElevator(double raiseAmount)
	{
		boolean topLimitStatus = topElevatorLimit.get();
		boolean bottomLimitStatus = bottomElevatorLimit.get();
		
		elevatorMotor.set(raiseAmount);
		return false;
		
	}
	
	public void gotoHeight(position height)
	{
		if(height == position.bottom)
		{
			raiseElevator(elevatorLowerSpeed);
			
			while(elevatorMoveTimer.get() < timeToLowerElevator){}
			
			elevatorMoveTimer.reset();
		}
			
		else if(height == position.tote)
		{
			raiseElevator(-elevatorLowerSpeed);
			
			while(elevatorMoveTimer.get() < timeToLowerElevator){}
			
			elevatorMoveTimer.reset();
		}
		
		else if(height == position.top)
		{
			
		}
	}
	
	public void loadTote()
	{
		intake.closeIntake();			//close the intake arms
		
		while(intakeTimer.get() < 1){}
		
		intakeTimer.reset();
		
		intake.openIntake();
		claw.openClaw();
		
		gotoHeight(position.bottom);			//move the elevator to the bottom
	
		intake.openIntake();			//open the intake arms
	
		gotoHeight(position.tote);			//go to default position
		userInput = true;
	}
	
	public void teleop()
	{
		//==========Elevator==========
		if(userInput)
		{
			raiseElevator(controller.applyDeadband(-controller.getLeftStickY()));
		}
		
		if(bottomElevatorLimit.get())
		{
			System.out.println("bottom limit triggered");
		}
		
		if(controller.getButtonA())
		{
			userInput = false;
			loadTote();
		}
		
		/*
		if(controller.getButtonX())
		 
		{ 
			userInput = false;
			
			gotoHeight(position.tote);
			
			userInput = true;
		}
		*/
		
		//==========Claw==========
		
		if(controller.getLeftTriggerButton())
		{ 
			claw.openClaw();;
		}
		
		if(controller.getRightTriggerButton())
		{ 
			claw.closeClaw();
		}
		
		//==========Intake==========
		
		if(controller.getLeftTriggerAxis() > 0.5)
		{ 
			intake.openIntake();
		}
		
		if(controller.getRightTriggerAxis() > 0.5)
		{ 
			intake.closeIntake();
		}
		
		if(controller.getButtonY())
		{ 
			intake.intakeMotors(true);
		}
		
		if(controller.getButtonB())
		{ 
			intake.intakeMotors(false);
		}
		
		//==========Other==========
		
		/*
		if(controller.getButtonA())
		{
			userInput = false;
			
			loadTote();
			
			userInput = true;
		}
		*/
	}
	
	public void autonomous()
	{
		
	}
	
	public void test()
	{
		/*
		else
		{
			if(controller.getLeftStickY() > 0.5)
			{
				if(controller.getLeftStickY() < -0.5)
				{
					raiseElevator(controller.applyDeadband(-controller.getLeftStickY()));
				}
				
				else
				{
					raiseElevator(-0.5);
				}
			}
			
			else
			{
				raiseElevator(0.5);
			}
		} 
		*/
		
		
		/*
		if(controller.getButtonBACK())
		{
			
			if(counter > 100)
			{
				if(!practiceMode)
				{
					practiceMode = true;
					System.out.println("practice mode enabled");
				}
				
				else if(practiceMode)
				{
					practiceMode = false;
					System.out.println("practice mode disabled");
				}
				counter = 0;
			}
			else
			{
				counter++;
			}
		}
		
		else
		{
			counter = 0;
		}
		*/
	}
}
