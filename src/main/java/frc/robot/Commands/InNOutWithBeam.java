package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.HardwareMappings;

public class InNOutWithBeam extends Command {

    public InNOutWithBeam() {}

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        HardwareMappings.intakeOuttake.set(-.15); 
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (!HardwareMappings.lightReader.get()) {
            HardwareMappings.intakeOuttake.set(0);
        }
    }

    // Called once the command ends or is interrupted.
    
    @Override
    public void end(boolean interrupted) {
        HardwareMappings.intakeOuttake.set(0);
    }


    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (!HardwareMappings.lightReader.get()) {
            HardwareMappings.intakeOuttake.set(0);
        }
        return !HardwareMappings.lightReader.get();
    }
    
}
