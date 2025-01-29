package frc.robot;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.teleopswerve.SwerveModule;;

public class HardwareMappings {

    // TODO: Change CAN IDs
    
    TalonFX driveMotorFR = new TalonFX(0);
    TalonFX driveMotorFL = new TalonFX(0);
    TalonFX driveMotorBR = new TalonFX(0);
    TalonFX driveMotorBL = new TalonFX(0);

    TalonFX turnMotorFR = new TalonFX(0);
    TalonFX turnMotorFL = new TalonFX(0);
    TalonFX turnMotorBR = new TalonFX(0);
    TalonFX turnMotorBL = new TalonFX(0);


    CANcoder turnEncoderFR = new CANcoder(0);
    CANcoder turnEncoderFL = new CANcoder(0);
    CANcoder turnEncoderBR = new CANcoder(0);
    CANcoder turnEncoderBL = new CANcoder(0);
    
    double[] initialEncoderPositions = {
        0,
        0,
        0,
        0,
    };

    SwerveModule frontRight = new SwerveModule(driveMotorFR, turnMotorFR, turnEncoderFR, 
    initialEncoderPositions[0]);
    SwerveModule frontLeft  = new SwerveModule(driveMotorFL, turnMotorFL, turnEncoderFL, 
    initialEncoderPositions[1]);
    SwerveModule backRight  = new SwerveModule(driveMotorBR, turnMotorBR, turnEncoderBR, 
    initialEncoderPositions[2]);
    SwerveModule backLeft   = new SwerveModule(driveMotorBL, turnMotorBL, turnEncoderBL, 
    initialEncoderPositions[3]);
    
}
