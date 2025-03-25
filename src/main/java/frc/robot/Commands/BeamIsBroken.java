// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.sensors.*;


/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class BeamIsBroken extends Command {
  /** Creates a new BeamIsBroken. */
  public BeamBreak beamy;
  public final boolean startState;
  // TODO: add a requirement and have a beam break sensor object to detect if it is tripped
  public BeamIsBroken(BeamBreak beamy) {
    this.beamy = beamy;
    startState = beamy.isBroken();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while(beamy.isBroken() != startState)
    {
      System.out.println("Beam is in the same state as it started");
    }
  }

  // Called once the command ends or is interrupted.
 
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
