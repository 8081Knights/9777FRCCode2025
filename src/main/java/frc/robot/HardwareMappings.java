package frc.robot;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.teleopswerve.SwerveModule;;

public class HardwareMappings {

    // TODO: Change CAN IDs
    
    public static TalonFX driveMotorFR = new TalonFX(13);
    public static TalonFX driveMotorFL = new TalonFX(17);
    public static TalonFX driveMotorBR = new TalonFX(12);
    public static TalonFX driveMotorBL = new TalonFX(16);

    public static TalonFX turnMotorFR = new TalonFX(15);
    public static TalonFX turnMotorFL = new TalonFX(10);
    public static TalonFX turnMotorBR = new TalonFX(14);
    public static TalonFX turnMotorBL = new TalonFX(11);


    public static CANcoder turnEncoderFR = new CANcoder(26);
    public static CANcoder turnEncoderFL = new CANcoder(24);
    public static CANcoder turnEncoderBR = new CANcoder(20);
    public static CANcoder turnEncoderBL = new CANcoder(22);
    
    static double[] initialEncoderPositions = {
        0,
        0,
        0,
        0,
    };

    public static SwerveModule frontRight = new SwerveModule(driveMotorFR, turnMotorFR, turnEncoderFR, 
    initialEncoderPositions[0]);
    public static SwerveModule frontLeft  = new SwerveModule(driveMotorFL, turnMotorFL, turnEncoderFL, 
    initialEncoderPositions[1]);
    public static SwerveModule backRight  = new SwerveModule(driveMotorBR, turnMotorBR, turnEncoderBR, 
    initialEncoderPositions[2]);
    public static SwerveModule backLeft   = new SwerveModule(driveMotorBL, turnMotorBL, turnEncoderBL, 
    initialEncoderPositions[3]);
    
}
