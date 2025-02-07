package frc.robot.teleopswerve;
import javax.xml.stream.events.Namespace;

import edu.wpi.first.wpilibj.XboxController;


public class SwerveDrive {



    /** 
     * Variables for the robot that controll everything
     * @
     */
    public class ControllerVariables {
    
        public static double[][] moduleError = new double[4][2];
        public static double[] defaultAngles = new double[4];
        public static double[] newCenetreposition = new double[2];
        public static double newSetAngle;
        public static double distancePerUnit = 3;
        public static double additiveAngle;
        public static double[] robotDimentions;
        public static double hypotenuse;
        public static double genericTickRotation = Math.PI / 24;
        public static double[] newSetAngles = new double[4];
        public static double[][] afterPosUnitCircle = new double[4][2];
        public static double[][] afterRotationPosition = new double[4][2];


        public static double[] defaultSwerveConfiguration = new double[4];

    }

    /**
     * Makes the robot move with the controller, unadvanced, no positioning or anything wanted.
     * @param controller
     */
    public static void teleopDrive(XboxController controller) {
        ControllerVariables.newSetAngle = controller.getRightX() * ControllerVariables.genericTickRotation;
        ControllerVariables.newCenetreposition[0] = controller.getLeftX();
        ControllerVariables.newCenetreposition[1] =-controller.getLeftY();

        ControllerVariables.newSetAngles[0] = ControllerVariables.defaultAngles[0] + ControllerVariables.newSetAngle;
        ControllerVariables.newSetAngles[1] = ControllerVariables.defaultAngles[1] + ControllerVariables.newSetAngle;
        ControllerVariables.newSetAngles[2] = ControllerVariables.defaultAngles[2] + ControllerVariables.newSetAngle;
        ControllerVariables.newSetAngles[3] = ControllerVariables.defaultAngles[3] + ControllerVariables.newSetAngle;

        ControllerVariables.afterPosUnitCircle[0][0] = Math.cos(ControllerVariables.newSetAngles[0]);
        ControllerVariables.afterPosUnitCircle[0][1] = Math.sin(ControllerVariables.newSetAngles[0]);
        ControllerVariables.afterPosUnitCircle[1][0] = Math.cos(ControllerVariables.newSetAngles[1]);
        ControllerVariables.afterPosUnitCircle[1][1] = Math.sin(ControllerVariables.newSetAngles[1]);
        ControllerVariables.afterPosUnitCircle[2][0] = Math.cos(ControllerVariables.newSetAngles[2]);
        ControllerVariables.afterPosUnitCircle[2][1] = Math.sin(ControllerVariables.newSetAngles[2]);
        ControllerVariables.afterPosUnitCircle[3][0] = Math.cos(ControllerVariables.newSetAngles[3]);
        ControllerVariables.afterPosUnitCircle[3][1] = Math.sin(ControllerVariables.newSetAngles[3]);

        
    }

    public static void init(double[] dimetions, double inputDefaultAngles[]) {
        for (int i = 0; i < inputDefaultAngles.length; i++) {
            ControllerVariables.defaultSwerveConfiguration[i] = inputDefaultAngles[i];

        }
        ControllerVariables.robotDimentions = dimetions;
        ControllerVariables.hypotenuse = Math.sqrt((dimetions[0] * dimetions[0]) + (dimetions[1] * dimetions[1]));
        
        ControllerVariables.defaultAngles[0] = Math.acos( dimetions[0] / ControllerVariables.hypotenuse);
        ControllerVariables.defaultAngles[1] = Math.acos(-dimetions[0] / ControllerVariables.hypotenuse);
        ControllerVariables.defaultAngles[2] = Math.acos( dimetions[0] / ControllerVariables.hypotenuse) + Math.PI;
        ControllerVariables.defaultAngles[3] = Math.acos(-dimetions[0] / ControllerVariables.hypotenuse) + Math.PI;



    }
}
