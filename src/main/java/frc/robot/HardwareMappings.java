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
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.sensors.BeamBreak;


public class HardwareMappings {

    // Drivetrain objects for random stuff
    CommandXboxController joystick1Controller = new CommandXboxController(0);


    // TODO: reset the pigion can to something other than 0 and regenerate the swerve code if needed
    // 23 works 


    // NON- drive motors and sensors
    public static SparkMax Ele1 = new SparkMax(7, MotorType.kBrushless);
    public static SparkMax Ele2 = new SparkMax(2, MotorType.kBrushless);
    public static RelativeEncoder ele1Enc = Ele1.getEncoder();
    public static RelativeEncoder ele2Enc = Ele2.getEncoder();
    public static SparkClosedLoopController ele1Ctrlr = Ele1.getClosedLoopController();
    public static SparkMaxConfig ele1Conf = new SparkMaxConfig();
    public static SparkClosedLoopController ele2Ctrlr = Ele2.getClosedLoopController();
    public static SparkMaxConfig ele2Conf = new SparkMaxConfig();
    
    

    public static SparkFlex joint = new SparkFlex(31, MotorType.kBrushless);
    public static SparkFlex intakeOuttake = new SparkFlex(33, MotorType.kBrushless);
    public static RelativeEncoder joinRelativeEncoder = joint.getEncoder();
    public static RelativeEncoder intakEncoder = Ele2.getEncoder();
    public static SparkClosedLoopController jointPIDController = joint.getClosedLoopController();
    public static SparkFlexConfig jointConfig = new SparkFlexConfig();


    // I think your stuff isnt connected properly run "git restore e17ebfd1f3a3ff8d8f9dfbbd0b2614ad30481a83 to go back to what you had."

    public static DigitalInput lightReader = new DigitalInput(0);

    // public static BeamBreak beamy = new BeamBreak(0);


    public static double elevatoroffset = 0;
    public static int currentElevatorCase = 0;
    public static boolean safeUp;
    public static boolean safeDown;
    public static double speedFactor = 1;
    public static double realSpeedFactor = 1;
    


    double encoderPOSJoint = joinRelativeEncoder.getPosition();

    public static void init() {
        jointConfig.closedLoop.p(.11d).d(.01d).outputRange(-.2, .4);
        joint.configure(jointConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        ele1Conf.closedLoop.p(.09d).d(.01d).outputRange(-.7, .9);
        Ele1.configure(ele1Conf, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        ele2Conf.closedLoop.p(.09d).d(.01d).outputRange(-.7, .9);
        Ele2.configure(ele2Conf, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    






    public static class QuickMethods {

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
         * @param caseNum sets the case of the elevator postion: floor is zero
         */
        public static double setElevatorPositionsAuto(int caseNum) {
            if(!safeUp && caseNum > 0) {
                return -0;
            }
            if (!safeDown && caseNum < 3) {
                //when this method was void, it just did return;
                return -0;
            }
            switch (caseNum) {
                case 0:
                    // zero-pos
                    ele1Ctrlr.setReference(0   + elevatoroffset, ControlType.kPosition);
                    ele2Ctrlr.setReference(0   + elevatoroffset, ControlType.kPosition);
                    return ele1Enc.getPosition();
                case 1:
                    //level-1 / trough
                    ele1Ctrlr.setReference(15  + elevatoroffset, ControlType.kPosition);
                    ele2Ctrlr.setReference(15  + elevatoroffset, ControlType.kPosition);
                    return ele1Enc.getPosition();
                case 2:
                    //level-2
                    ele1Ctrlr.setReference(35  + elevatoroffset, ControlType.kPosition);
                    ele2Ctrlr.setReference(35  + elevatoroffset, ControlType.kPosition);
                    return ele1Enc.getPosition();
                case 3:
                    //level-3
                    ele1Ctrlr.setReference(75  + elevatoroffset, ControlType.kPosition);
                    ele2Ctrlr.setReference(75  + elevatoroffset, ControlType.kPosition);
                    return ele1Enc.getPosition();
                case 5: 
                    //level-4
                    ele1Ctrlr.setReference(140 + elevatoroffset, ControlType.kPosition);
                    ele2Ctrlr.setReference(140 + elevatoroffset, ControlType.kPosition);
                    return ele1Enc.getPosition();
                case 6:
                    // begin algea code
                    //low algea
                    ele1Ctrlr.setReference(57  + elevatoroffset, ControlType.kPosition);
                    ele2Ctrlr.setReference(57  + elevatoroffset, ControlType.kPosition);
                    return ele1Enc.getPosition();
                case 7:
                    //high algea
                    ele1Ctrlr.setReference(93  + elevatoroffset, ControlType.kPosition);
                    ele2Ctrlr.setReference(93  + elevatoroffset, ControlType.kPosition);
                    return ele1Enc.getPosition();
                case 8:
                    // processor
                    ele1Ctrlr.setReference(5  + elevatoroffset, ControlType.kPosition);
                    ele2Ctrlr.setReference(5  + elevatoroffset, ControlType.kPosition);
                    return ele1Enc.getPosition();
                case 10:
                    ele1Ctrlr.setReference(160 + elevatoroffset, ControlType.kPosition);
                    ele2Ctrlr.setReference(160 + elevatoroffset, ControlType.kPosition);
                    return ele1Enc.getPosition();
                default:
                    System.out.println("Oh no, make case in HardwareMappings - setElevatorPositionsAuto");
                    return ele1Enc.getPosition();
            }
          //  currentElevatorCase = caseNum; 

        }

        public static double setJointPositionsAuto(int caseNum) {
            double targetPos;
            double currentEncoderPos = joinRelativeEncoder.getPosition();
            switch (caseNum) {
                
                case 0:
                    targetPos = -0.3;
                    HardwareMappings.jointPIDController.setReference(targetPos, ControlType.kPosition);
                    safeDown = true;
                    safeUp = false;
                    double currentEncoderPos1 = joinRelativeEncoder.getPosition();
                    double difference2 = targetPos - currentEncoderPos1;
                    return difference2;
                case 1:
                targetPos = -1.05;
                    HardwareMappings.jointPIDController.setReference(targetPos, ControlType.kPosition);
                    safeDown = true;
                    safeUp = true;
                    double currentEncoderPos2 = joinRelativeEncoder.getPosition();
                    double difference1 = targetPos - currentEncoderPos2;
                    return difference1;
                case 2:
                //for l4
                targetPos = -1.4;
                    HardwareMappings.jointPIDController.setReference(targetPos, ControlType.kPosition);
                    safeDown = true;
                    safeUp = true;
                    double difference3 = targetPos - currentEncoderPos;
                    return difference3;
                case 3:
                targetPos = -4.5;
                    HardwareMappings.jointPIDController.setReference(targetPos, ControlType.kPosition);
                    safeDown = false;
                    safeUp = true;
                    double difference4 = targetPos - currentEncoderPos;
                    return difference4;
                default:
                    System.out.println("Oh no, make case in HardwareMappings");
                    return 10;
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
