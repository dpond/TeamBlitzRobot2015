// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future. Nate Craig made an edit here.


package org.usfirst.frc2083.TeamBlitzRobot2015;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANJaguar.ControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc2083.TeamBlitzRobot2015.commands.*;
import org.usfirst.frc2083.TeamBlitzRobot2015.subsystems.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    DriveCommand driveCommand;
//    ClawCommand clawCommand;
//    ShootCommand shootCommand;
    double shootTimer = -1;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {

        RobotMap.leftFront = new CANJaguar(RobotMap.leftForwardMotor);
        RobotMap.leftBack = new CANJaguar(RobotMap.leftBackMotor);
        RobotMap.rightFront = new CANJaguar(RobotMap.rightForwardMotor);
        RobotMap.rightBack = new CANJaguar(RobotMap.rightBackMotor);
        RobotMap.leftFront.configNeutralMode(CANJaguar.NeutralMode.Brake);
        RobotMap.leftBack.configNeutralMode(CANJaguar.NeutralMode.Brake);
        RobotMap.rightFront.configNeutralMode(CANJaguar.NeutralMode.Brake);
        RobotMap.rightBack.configNeutralMode(CANJaguar.NeutralMode.Brake);
        
//            RobotMap.upperClaw = new CANJaguar(RobotMap.upperClawMotor, ControlMode.Voltage);
//            RobotMap.upperClaw.configNeutralMode(CANJaguar.NeutralMode.Brake);
//            RobotMap.upperClaw.setX(0);
//            RobotMap.lowerClaw = new CANJaguar(RobotMap.lowerClawMotor, ControlMode.Voltage);
//            RobotMap.lowerClaw.configNeutralMode(CANJaguar.NeutralMode.Brake);
//            RobotMap.lowerClaw.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
//            RobotMap.lowerClaw.configEncoderCodesPerRev(360);
//            RobotMap.lowerClaw.setX(0);
//            RobotMap.lowerClaw.enableControl();

        // FIXME Figure out the replacement.
        RobotMap.leftFront.setSpeedMode(CANJaguar.kQuadEncoder, 1, 1, 1, 1);
        RobotMap.rightFront.setSpeedMode(CANJaguar.kQuadEncoder, 1, 1, 1, 1);
        RobotMap.leftFront.setPositionMode(CANJaguar.kQuadEncoder, 1,1, 1, 1);
        RobotMap.rightFront.setPositionMode(CANJaguar.kQuadEncoder, 1,1, 1, 1);
//        RobotMap.leftFront.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);
//        RobotMap.rightFront.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);
//        RobotMap.leftFront.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
//        RobotMap.rightFront.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
        
        RobotMap.leftFront.configEncoderCodesPerRev(360);
        RobotMap.rightFront.configEncoderCodesPerRev(250);
        
//            RobotMap.compressorRelay = new Relay(1);
//            RobotMap.compressorRelay.setDirection(Relay.Direction.kForward);
//            RobotMap.solenoidRelay = new Relay(2);
//            RobotMap.solenoidRelay.setDirection(Relay.Direction.kForward);
//            RobotMap.shooterValveSolenoid = new Solenoid(1);
//            RobotMap.shooterValveSolenoid.set(false);
                    
        // Initialize all subsystems
        CommandBase.init();
        driveCommand = new DriveCommand();
//            clawCommand = new ClawCommand();
        DriveCommand.xbox = new Joystick(1);
//            ClawCommand.xbox = DriveCommand.xbox;
//            shootCommand = new ShootCommand();
//            ShootCommand.xbox = DriveCommand.xbox;
        
       
        
//            clawCommand.disableControl();
        driveCommand.disableControl();            
    }

    public void autonomousInit() {
        RobotMap.auto = true;
        RobotMap.autoTimer = System.currentTimeMillis()+500;
        driveCommand.enableControl();
        driveCommand.start();
//        clawCommand.enableControl();
//        clawCommand.start();
//        shootCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        if (!RobotMap.auto) return;
        // Start closing claw
        if (System.currentTimeMillis()-RobotMap.autoTimer > 000) {
            RobotMap.autoClose = true;
            RobotMap.autoOpen = false;
        }
        // Start driving forward
        if (System.currentTimeMillis()-RobotMap.autoTimer > 1500) {
            RobotMap.autoY = 0.40;
        }
        // Raise bottom claw
        if (System.currentTimeMillis()-RobotMap.autoTimer > 2000) {
            RobotMap.autoUp = true;
            RobotMap.autoDown = false;
        }
        // Open the claw
        if (System.currentTimeMillis()-RobotMap.autoTimer > 3500) {
            RobotMap.autoClose = false;
            RobotMap.autoOpen = true;
        }
        // Stop opening
        if (System.currentTimeMillis()-RobotMap.autoTimer > 4000) {
            RobotMap.autoOpen = false;
        }
        /*try {
            // Shoot
            System.out.println(RobotMap.leftFront.getPosition()-RobotMap.leftOffset);
            if (Math.abs(RobotMap.leftFront.getPosition()-RobotMap.leftOffset) >= 8.5 && shootTimer == -1) {
                RobotMap.autoShoot = true;
                shootTimer = System.currentTimeMillis();
                RobotMap.autoY = 0;
            }
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }*/
        if (System.currentTimeMillis()-RobotMap.autoTimer > 5000 && shootTimer ==-1) {
//            RobotMap.autoShoot = true;
//            shootTimer = System.currentTimeMillis();
            RobotMap.autoY = 0;
        }
        if (shootTimer != -1 && System.currentTimeMillis()-shootTimer > 500) {
//            RobotMap.autoShoot = false;
            RobotMap.auto = false;
            RobotMap.autoTimer = -1;
        }
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        RobotMap.auto = false;
        System.out.println("TELEOP INIT");
        driveCommand.enableControl();
        driveCommand.start();
//        clawCommand.enableControl();
//        clawCommand.start();
//        shootCommand.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        RobotMap.auto = false;
//        RobotMap.autoShoot = false;
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
