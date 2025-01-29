package frc.robot.teleopswerve;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

public class SwerveModule {
    public TalonFX turnMotor;
    public TalonFX driveMotor;


    public CANcoder moduleEncoder;
    public double offset = 0;
    int direction;

    public SwerveModule(TalonFX turnInput, TalonFX driveInput, CANcoder encoderInput, double offsetInput) {
        turnMotor = turnInput;
        driveMotor = driveInput;
        moduleEncoder = encoderInput;
        offset = offsetInput;
        
    }




    public double goToNewPosition(double setAngle, double setSpeed) {
        double errorReturn = 0;

        int goodIndex = 0;


        double[] alternateAngles = {
            setAngle - (2*Math.PI),
            setAngle,
            setAngle + (2*Math.PI),
        };

        double currentAngle = moduleEncoder.getAbsolutePosition().getValueAsDouble() + offset;
        currentAngle = NormalizeAngle(currentAngle);

        double[] diffAngles = {
            alternateAngles[0] - currentAngle,
            alternateAngles[1] - currentAngle,
            alternateAngles[2] - currentAngle,
        };

        if (Math.abs(diffAngles[1]) < Math.abs(diffAngles[0]) && Math.abs(diffAngles[1]) < Math.abs(diffAngles[2])) {
            goodIndex = 1;
        }

        if (Math.abs(diffAngles[2]) < Math.abs(diffAngles[0]) && Math.abs(diffAngles[2]) < Math.abs(diffAngles[1])) {
            goodIndex = 2;
        }

        double optimalAngleTwoPi = diffAngles[goodIndex];

        double currentAngleErr = optimalAngleTwoPi;
        direction = 1;

        // Optimize direction when error is greater than π/2
        if (Math.abs(currentAngleErr) > Math.PI / 2) {
            direction = -1;
            currentAngleErr += (-Math.signum(optimalAngleTwoPi) * Math.PI);
        }

        driveMotor.set(setSpeed * direction);
        turnMotor.set(currentAngleErr);

        errorReturn = (Math.abs(currentAngle - setAngle) + Math.abs(currentAngleErr));
        return errorReturn;
    }

    double NormalizeAngle(double angle) {
        while (angle > 2*Math.PI) {
            angle -= 2*Math.PI;
        }
        while (angle < 0) {
            angle += 2*Math.PI;
        }
        return angle;
    }
}
