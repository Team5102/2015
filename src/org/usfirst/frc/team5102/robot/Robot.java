
package org.usfirst.frc.team5102.robot;

import org.usfirst.frc.team5102.robot.util.Xbox;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot 
{
	private Elevator elevator;
	private Drive drive;
	private Shifter shifter;
	private Claw claw;
	private Intake intake;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	elevator = new Elevator();
    	drive = new Drive();
    	shifter = new Shifter();
    	claw = new Claw();
    	intake = new Intake();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
    	drive.teleop();
    	elevator.teleop();
    	shifter.teleop();
    	claw.teleop();
    	intake.teleop();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
