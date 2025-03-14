// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController;

import javax.lang.model.util.ElementScanner14;

import com.revrobotics.spark.SparkBase.ControlType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private final RobotContainer m_robotContainer;

  public XboxController controller1 = new XboxController(0); 
  public XboxController controller2 = new XboxController(1); 


  public Robot() {
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
    if (controller1.getRightTriggerAxis() > .05) {
      HardwareMappings.QuickMethods.setElevatorPower( controller1.getRightTriggerAxis());
    } else if (controller1.getLeftTriggerAxis() > .05) { 
      HardwareMappings.QuickMethods.setElevatorPower(-controller1.getLeftTriggerAxis());
    } else {
      HardwareMappings.QuickMethods.setElevatorPower(0);
    }


    // if (controller2.getAButton()) {
    //   HardwareMappings.intakeOuttake.set(.1);
    // } else if (controller2.getBButton()) { 
    //   HardwareMappings.intakeOuttake.set(-.1);
    // } else {
    //   HardwareMappings.intakeOuttake.set(0);
    // }


    if (controller2.getAButton()) {
      HardwareMappings.jointPIDController.setReference(-3, ControlType.kPosition);
    } else if (controller2.getBButton()) { 
      HardwareMappings.jointPIDController.setReference(-5, ControlType.kPosition);
    } else {
      HardwareMappings.jointPIDController.setReference(0, ControlType.kPosition);
    }

    if (controller2.getRightTriggerAxis() > .05) {
      HardwareMappings.QuickMethods.setJointPower(controller2.getRightTriggerAxis());


    } else if (controller2.getLeftTriggerAxis() > .05) { 
      HardwareMappings.QuickMethods.setJointPower(-controller2.getLeftTriggerAxis());
    } else {
      HardwareMappings.QuickMethods.setJointPower(0);

    }

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

    // if (controller1.getAButton()) {
    //   HardwareMappings.intakeOuttake.set(.5);
    // } else if (controller1.getYButton()) {
    //   HardwareMappings.intakeOuttake.set(-.5);
    // } else {
    //   HardwareMappings.intakeOuttake.set(0);
    // }
  }

  @Override
  public void testExit() {}

  @Override
  public void simulationPeriodic() {}
}
