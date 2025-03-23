// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private final RobotContainer m_robotContainer;

  public XboxController controller1 = new XboxController(0); 
  public XboxController controller2 = new XboxController(1); 

  boolean emergencyElevator = false;

  


  public Robot() {
    HardwareMappings.init();
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {

    CommandScheduler.getInstance().run(); 
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    if (controller2.getAButton()) {
      HardwareMappings.QuickMethods.setJointPositionsAuto(1);
    } else if (controller2.getBButton()) {
      HardwareMappings.QuickMethods.setJointPositionsAuto(2);
    } else if (controller2.getXButton()) {
      HardwareMappings.QuickMethods.setJointPositionsAuto(0);
    } else if (controller2.getYButton()) {
      HardwareMappings.QuickMethods.setJointPositionsAuto(3);
    }


    if (!emergencyElevator) {
      controller2.setRumble(RumbleType.kBothRumble, 0);
      if (controller2.getLeftTriggerAxis()> .05) {
        HardwareMappings.intakeOuttake.set(controller2.getLeftTriggerAxis() * .15);
      } else if(controller1.getLeftTriggerAxis() > 0.5) {
        HardwareMappings.intakeOuttake.set(controller1.getLeftTriggerAxis() * .15);
      }else if (controller2.getRightTriggerAxis()> .05) {
        HardwareMappings.intakeOuttake.set(controller2.getRightTriggerAxis() * -.15);
      } else if(controller1.getRightTriggerAxis() > 0.5){
        HardwareMappings.intakeOuttake.set(controller1.getRightTriggerAxis() * -.15);
      }
      else if(controller2.getRightBumperButton()){
        HardwareMappings.intakeOuttake.set(-0.9);
      } else if(controller2.getLeftBumperButton()){
        HardwareMappings.intakeOuttake.set(0.9);
      } else {
        HardwareMappings.intakeOuttake.set(0);
      }
    } else {
      controller2.setRumble(RumbleType.kBothRumble, .8);
      HardwareMappings.elevatoroffset += controller2.getRightTriggerAxis() * 1 + controller2.getLeftTriggerAxis() * -1;
      HardwareMappings.QuickMethods.setElevatorPositionsAuto(HardwareMappings.currentElevatorCase);
    }
    

    emergencyElevator = controller2.getStartButton();


    if (controller2.getPOV() == 0) {
      HardwareMappings.QuickMethods.setElevatorPositionsAuto(0);
      HardwareMappings.speedFactor = 1;
    } else if (controller2.getPOV() == 45) {
      HardwareMappings.QuickMethods.setElevatorPositionsAuto(1);
      HardwareMappings.speedFactor = 1;
    } else if (controller2.getPOV() == 90) {
      HardwareMappings.QuickMethods.setElevatorPositionsAuto(2);
      HardwareMappings.speedFactor = .15;
    } else if (controller2.getPOV() == 135) {
      HardwareMappings.QuickMethods.setElevatorPositionsAuto(3);
      HardwareMappings.speedFactor = .15;
    } else if (controller2.getPOV() == 180) {
      HardwareMappings.QuickMethods.setElevatorPositionsAuto(5);
      HardwareMappings.speedFactor = .15;
    } else if (controller2.getPOV() == 270) {
      HardwareMappings.QuickMethods.setElevatorPositionsAuto(6);
      HardwareMappings.speedFactor = .15;
    } else if (controller2.getPOV() == 225) {
      HardwareMappings.QuickMethods.setElevatorPositionsAuto(7);
      HardwareMappings.speedFactor = .15;
    } else if (controller2.getPOV() == 315) {
    HardwareMappings.QuickMethods.setElevatorPositionsAuto(8);
    HardwareMappings.speedFactor = .15;
  }
    


  }

  @Override
  public void teleopExit() {
    
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
    

    // Elevator basic control
    /*
    if (controller1.getRightTriggerAxis() > .001) {
      HardwareMappings.QuickMethods.setElevatorPower(controller1.getRightTriggerAxis()*.2);
    } else if (controller1.getLeftTriggerAxis() > .001) { 
      HardwareMappings.QuickMethods.setElevatorPower(-controller1.getLeftTriggerAxis()*.2);
    } else {
      HardwareMappings.QuickMethods.setElevatorPower(0);
    }

*/
    // if (controller2.getAButton()) {
    //   HardwareMappings.intakeOuttake.set(.1);
    // } else if (controller2.getBButton()) { 
    //   HardwareMappings.intakeOuttake.set(-.1);
    // } else {
    //   HardwareMappings.intakeOuttake.set(0);
    // }

    //shoulder basic control
    if (controller2.getAButton()) {
      HardwareMappings.QuickMethods.setJointPositionsAuto(1);
    } else if (controller2.getBButton()) { 
      HardwareMappings.QuickMethods.setJointPositionsAuto(2);
    } else if (controller2.getXButton()) {
      HardwareMappings.QuickMethods.setJointPositionsAuto(0);
    } else if (controller2.getYButton()) {
      HardwareMappings.QuickMethods.setJointPositionsAuto(3);
    }

    // if (controller2.getRightTriggerAxis() > .05) {
    //   HardwareMappings.QuickMethods.setJointPower(controller2.getRightTriggerAxis() / 3.0);
    // } else if (controller2.getLeftTriggerAxis() > .05) { 
    //   HardwareMappings.QuickMethods.setJointPower(-controller2.getLeftTriggerAxis() / 3.0);
    // } else {
    //   HardwareMappings.QuickMethods.setJointPower(0);
    // }

    // //joint basic control
    // if(controller1.getAButton())
    // {
    //   HardwareMappings.QuickMethods.setJointPower(0.3);
    // } else if(controller1.getBButton()){
    //   HardwareMappings.QuickMethods.setJointPower(-0.3);
    // }
    // else{
    //   HardwareMappings.QuickMethods.setJointPower(0);
    // }

    if (controller2.getLeftBumperButton()) {
      HardwareMappings.intakeOuttake.set(.15);
    } else if (controller2.getRightBumperButton()) {
      HardwareMappings.intakeOuttake.set(-.15);
    } else {
      HardwareMappings.intakeOuttake.set(0);
    }


    // if (controller2.getPOV() == 0) {
    //   HardwareMappings.QuickMethods.setElevatorPositionsAuto(0);
    // } else if (controller2.getPOV() == 90) {
    //   HardwareMappings.QuickMethods.setElevatorPositionsAuto(1);
    // } else if (controller2.getPOV() == 180) {
    //   HardwareMappings.QuickMethods.setElevatorPositionsAuto(2);
    // }

    SmartDashboard.putBoolean("beamReader", HardwareMappings.lightReader.get());
  }

  @Override
  public void testExit() {}

  @Override
  public void simulationPeriodic() {}
}
