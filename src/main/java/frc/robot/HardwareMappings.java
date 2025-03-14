package frc.robot;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class HardwareMappings {

    // TODO: reset the pigion can to something other than 0 and regenerate the swerve code if needed
    // 23 works 


    // NON- drive motors and sensors
    public static SparkMax Ele1 = new SparkMax(7, MotorType.kBrushless);
    public static SparkMax Ele2 = new SparkMax(6, MotorType.kBrushless);
    public static RelativeEncoder ele1Enc = Ele1.getEncoder();
    public static RelativeEncoder ele2Enc = Ele2.getEncoder();

    public static SparkFlex joint = new SparkFlex(21, MotorType.kBrushless);
    public static SparkFlex intakeOuttake = new SparkFlex(23, MotorType.kBrushless);
    public static RelativeEncoder joinRelativeEncoder = Ele1.getEncoder();
    public static RelativeEncoder intakEncoder = Ele2.getEncoder();
    public static SparkClosedLoopController jointPIDController = joint.getClosedLoopController();
    public static SparkFlexConfig jointConfig = new SparkFlexConfig();
    


    public static void init() {
        HardwareMappings.jointConfig.closedLoop.p(.5).outputRange(-.8, .8);
        HardwareMappings.joint.configure(HardwareMappings.jointConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    






    static class QuickMethods {

        /**
         * Sets the speed of the overall elevators (abbreviates individual speeds)
         * @param setSpeed (takes -1 to 1, no safety catch yet)
         */
        public static void setElevatorPower(double setSpeed) {
            Ele1.set(setSpeed);
            Ele2.set(setSpeed);
            SmartDashboard.putNumber("eleSpeed", setSpeed);
        }

        /**
         * Set elevator positions
         * @param case sets the case of the elevator postion: floor is zero
         */
        public static void setElevatorPositionsAuto(int caseNum) {
            switch (caseNum) {
                case 0:
                    HardwareMappings.jointPIDController.setReference(0, ControlType.kPosition);
                    break;
                case 1:
                    HardwareMappings.jointPIDController.setReference(-3, ControlType.kPosition);
                    break;
                case 2:
                    HardwareMappings.jointPIDController.setReference(-5, ControlType.kPosition);
                    break;
                default:
                    System.out.println("Oh no, make case in HardwareMappings");
                    break;
            }
        }

        /**
         * sets the speed of the joint
         * @param setSpeed
         */
        public static void setJointPower(double setSpeed) {
        	joint.set(setSpeed);
        	SmartDashboard.putNumber("jointSpeed", setSpeed);
        }

        /**
         * sets the speed of the intake outake
         * @param setSpeed
         */
        public static void setIntakeOuttakePower(double setSpeed) {
        	intakeOuttake.set(setSpeed);
        	SmartDashboard.putNumber("intakeOuttakeSpeed", setSpeed);
        }
    }
}
