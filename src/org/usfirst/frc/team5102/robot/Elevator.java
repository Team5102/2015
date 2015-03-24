package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Talon;

public class Elevator extends RobotElement
{
	private Talon elevatorMotor;
	
	private DigitalInput bottomEmergencyLimit,topEmergencyLimit,bottomElevatorLimit,topElevatorLimit,transportHeightSensor,toteHeightSensor;
	private AnalogPotentiometer potentiometer;
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
		tote,
		transport
	}
	
	public Elevator()
	{
		super(1);	//1 is the  port
		
		claw = new Claw();
    	intake = new Intake();
    	
		elevatorMotor = new Talon(6);
		
		topEmergencyLimit = new DigitalInput(0);
		bottomEmergencyLimit = new DigitalInput(1);
		topElevatorLimit = new DigitalInput(2);
		bottomElevatorLimit = new DigitalInput(3);
		transportHeightSensor = new DigitalInput(4);
		toteHeightSensor = new DigitalInput(5);
		
		potentiometer = new AnalogPotentiometer(0);
		
		intakeTimer = new Timer();
		elevatorMoveTimer = new Timer();
	}
	
	public boolean raiseElevator(double raiseAmount)
	{
		if(topElevatorLimit.get() || topEmergencyLimit.get())
		{
			if(raiseAmount < 0.0)
			{
				elevatorMotor.set(raiseAmount);
				return true;
			}
			else
			{
				elevatorMotor.set(0.0);
				return true;
			}
		}
		if(bottomElevatorLimit.get() || bottomEmergencyLimit.get())
		{
			if(raiseAmount > 0.0)
			{
				elevatorMotor.set(raiseAmount);
				return true;
			}
			else
			{
				elevatorMotor.set(0.0);
				return true;
			}
		}
		elevatorMotor.set(raiseAmount);
		return false;
	}
	
	public void gotoHeight(position height)
	{
		if(height == position.bottom)
		{
			boolean sensor = false;
			while(sensor == false)
			{
				sensor = raiseElevator(-1.0);
			}
		}
			
		else if(height == position.tote)
		{
			while(!toteHeightSensor.get())
			{
				if(potentiometer.get() < 0.5)
				{
					raiseElevator(1.0);
				}
				else if(potentiometer.get() > 0.5)
				{
					raiseElevator(-1.0);
				}
			}
			raiseElevator(0.0);
		}
		
		else if(height == position.top)
		{
			boolean sensor = false;
			while(sensor == false)
			{
				sensor = raiseElevator(1.0);
			}
		}
		
		else if(height == position.transport)
		{
			while(!transportHeightSensor.get())
			{
				if(potentiometer.get() < 0.1)
				{
					raiseElevator(1.0);
				}
				else if(potentiometer.get() > 0.1)
				{
					raiseElevator(-1.0);
				}
			}
			raiseElevator(0.0);
		}
	}
	
	public void loadTote()
	{
		intake.closeIntake();			//close the intake arms
		
		while(intakeTimer.get() < 1){}
		
		intakeTimer.reset();
		
		intake.openIntake();
		
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
