package frc.robot.teleopswerve;
import edu.wpi.first.wpilibj.XboxController;


public class SwerveDrive {
    public class ControllerVariables {
    
        public static double[][] moduleError = new double[4][2];
        public static double[] defaultAngles = new double[4];
        public static double distancePerUnit = 3;
        public static double additiveAngle;
        public static double[] robotDimentions;
        public static double hypotenuse;


    }

    /**
     * Makes the robot move with the controller, unadvanced, no positioning or anything wanted.
     * @param controller
     */
    public static void teleopDrive(XboxController controller) {
        
        
    }

    public static void init(double[] dimetions, double inputDefaultAngles[]) {
        for (int i = 0; i < inputDefaultAngles.length; i++) {
            ControllerVariables.defaultAngles[i] = inputDefaultAngles[i];
            
        }
        ControllerVariables.robotDimentions = dimetions;
        ControllerVariables.hypotenuse = Math.sqrt((dimetions[0] * dimetions[0]) + (dimetions[1] * dimetions[1]));


    }
}
