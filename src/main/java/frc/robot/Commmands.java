package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Commmands {





    public class ElevatorCommand implements Runnable {
        @Override
        public void run() {
            HardwareMappings.Ele1.set(-.5);
            // Ele2.set(setSpeed);
            SmartDashboard.putNumber("eleSpeed",-.5d);            
        }

    }
}
