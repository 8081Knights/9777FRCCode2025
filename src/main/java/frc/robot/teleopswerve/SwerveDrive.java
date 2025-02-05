package frc.robot.teleopswerve;
import edu.wpi.first.wpilibj.XboxController;


public class SwerveDrive {
    public class ControllerVariables {
    
        double[][] moduleError = new double[4][2];
        double[] defaultAngles = new double[4];
        double addictiveAngle;

    }

    /**
     * Makes the robot move with the controller, unadvanced, no positioning or anything wanted.
     * @param controller
     */
    public static void teleopDrive(XboxController controller) {

        
    }
}
