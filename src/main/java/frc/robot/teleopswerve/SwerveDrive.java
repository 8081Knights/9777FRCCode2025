package frc.robot.teleopswerve;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.HardwareMappings;


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
        public static double distancePerUnit = .5;
        public static double additiveAngle;
        public static double[] robotDimentions;
        public static double hypotenuse;
        public static double genericTickRotation = Math.PI / 24;
        public static double[] newSetAngles = new double[4];
        public static double[][] afterPosUnitCircle = new double[4][2];
        public static double[][] afterRotationPosition = new double[4][2];
        public static double[][] finalPosition = new double[4][2];

        public static boolean updateMovement;
        public static boolean updateRotation;


        public static double[] defaultSwerveConfiguration = new double[4];
        public static double limitOfSpeed = 3;

    }

    /**
     * Makes the robot move with the controller, unadvanced, no positioning or anything wanted.
     * @param controller
     */
    public static void teleopDrive(XboxController controller) {
        
        
        
        
        
        ControllerVariables.newSetAngle = controller.getRightX() * ControllerVariables.genericTickRotation;
        ControllerVariables.newCenetreposition[0] = controller.getLeftX() * ControllerVariables.distancePerUnit;
        ControllerVariables.newCenetreposition[1] =-controller.getLeftY() * ControllerVariables.distancePerUnit;



        if (Math.abs(controller.getLeftX()) < .07 && Math.abs(controller.getLeftY()) < .07 ) {
            ControllerVariables.updateMovement = false;
            ControllerVariables.newCenetreposition[0] = 0;
            ControllerVariables.newCenetreposition[1] = 0;
        } else {
            ControllerVariables.updateMovement = true;
        }
        
        if (Math.abs(controller.getRightX()) < .07) {
            ControllerVariables.updateRotation = false;
            ControllerVariables.newSetAngle = 0;
        } else {
         ControllerVariables.updateRotation = true;
        }

        ControllerVariables.newSetAngles[0] = ControllerVariables.defaultAngles[0] + ControllerVariables.newSetAngle;
        ControllerVariables.newSetAngles[1] = ControllerVariables.defaultAngles[1] + ControllerVariables.newSetAngle;
        ControllerVariables.newSetAngles[2] = ControllerVariables.defaultAngles[2] + ControllerVariables.newSetAngle;
        ControllerVariables.newSetAngles[3] = ControllerVariables.defaultAngles[3] + ControllerVariables.newSetAngle;

        ControllerVariables.afterRotationPosition[0][0] = Math.cos(ControllerVariables.newSetAngles[0]) * ControllerVariables.hypotenuse;
        ControllerVariables.afterRotationPosition[0][1] = Math.sin(ControllerVariables.newSetAngles[0]) * ControllerVariables.hypotenuse;
        ControllerVariables.afterRotationPosition[1][0] = Math.cos(ControllerVariables.newSetAngles[1]) * ControllerVariables.hypotenuse;
        ControllerVariables.afterRotationPosition[1][1] = Math.sin(ControllerVariables.newSetAngles[1]) * ControllerVariables.hypotenuse;
        ControllerVariables.afterRotationPosition[2][0] = Math.cos(ControllerVariables.newSetAngles[2]) * ControllerVariables.hypotenuse;
        ControllerVariables.afterRotationPosition[2][1] = Math.sin(ControllerVariables.newSetAngles[2]) * ControllerVariables.hypotenuse;
        ControllerVariables.afterRotationPosition[3][0] = Math.cos(ControllerVariables.newSetAngles[3]) * ControllerVariables.hypotenuse;
        ControllerVariables.afterRotationPosition[3][1] = Math.sin(ControllerVariables.newSetAngles[3]) * ControllerVariables.hypotenuse;


        ControllerVariables.finalPosition[0][0] = ControllerVariables.afterRotationPosition[0][0] + ControllerVariables.newCenetreposition[0];
        ControllerVariables.finalPosition[0][1] = ControllerVariables.afterRotationPosition[0][1] + ControllerVariables.newCenetreposition[1];
        ControllerVariables.finalPosition[1][0] = ControllerVariables.afterRotationPosition[1][0] + ControllerVariables.newCenetreposition[0];
        ControllerVariables.finalPosition[1][1] = ControllerVariables.afterRotationPosition[1][1] + ControllerVariables.newCenetreposition[1];
        ControllerVariables.finalPosition[2][0] = ControllerVariables.afterRotationPosition[2][0] + ControllerVariables.newCenetreposition[0];
        ControllerVariables.finalPosition[2][1] = ControllerVariables.afterRotationPosition[2][1] + ControllerVariables.newCenetreposition[1];
        ControllerVariables.finalPosition[3][0] = ControllerVariables.afterRotationPosition[3][0] + ControllerVariables.newCenetreposition[0];
        ControllerVariables.finalPosition[3][1] = ControllerVariables.afterRotationPosition[3][1] + ControllerVariables.newCenetreposition[1];



        if (!ControllerVariables.updateMovement && !ControllerVariables.updateRotation) {
            return;
        }
        double[] hypotenuseeee = new double[4];
        for (int i = 0; i < 4; i++) {
            hypotenuseeee[i] = (ControllerVariables.finalPosition[i][0] * ControllerVariables.finalPosition[i][0]) + 
                (ControllerVariables.finalPosition[i][1] * ControllerVariables.finalPosition[i][1]);


            if (ControllerVariables.finalPosition[i][1] < 0) {
                ControllerVariables.moduleError[i][1] = Math.acos(-ControllerVariables.finalPosition[i][1] / hypotenuseeee[i]) + Math.PI;
            } else {
                ControllerVariables.moduleError[i][1] = Math.acos(ControllerVariables.finalPosition[i][1] / hypotenuseeee[i]);
            }
        }



        
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
