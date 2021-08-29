package org.firstinspires.ftc.teamcode.Utils.Logics;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class ChangeState {

    private boolean isMotorRunning = false;
    private boolean isServoRunning = false;
    private boolean isMotorSpeedRunning = false;

    public static boolean getIsMotorRunning(DcMotor dcMotor) {
        return Math.abs(((DcMotorEx)dcMotor).getVelocity())>200;
    }

    public void updateState(){
        isMotorRunning = !isMotorRunning;
        isServoRunning = !isServoRunning;
        isMotorSpeedRunning = !isMotorSpeedRunning;
    }

    public void changeMotorState(boolean activate, double power, DcMotor... dcMotors) {
        if (activate) {
            if (isMotorRunning) {
                for (DcMotor dcMotor : dcMotors) {
                    dcMotor.setPower(0);
                }
                isMotorRunning = false;
            } else {
                for (DcMotor dcMotor : dcMotors) {
                    dcMotor.setPower(power);
                }
                isMotorRunning = true;
            }
        }
    }

    public void changeMotorStateSpeed(boolean activate, double speed, DcMotor... dcMotors) {
        if (activate) {
            if (isMotorSpeedRunning || Math.abs(dcMotors[0].getPower()) >= 0.05) {
                for (DcMotor dcMotor : dcMotors) {
                    ((DcMotorEx) dcMotor).setVelocity(0f);
                }
                isMotorSpeedRunning = false;
            } else {
                for (DcMotor dcMotor : dcMotors) {
                    ((DcMotorEx) dcMotor).setVelocity(speed);
                }
                isMotorSpeedRunning = true;
            }
        }
    }


    public void changeServoState(boolean activate, double power, CRServo... crServos) {
        if (activate) {
            if (isServoRunning) {
                for (CRServo crServo : crServos) {
                    crServo.setPower(0);
                }
                isServoRunning = false;
            } else {
                for (CRServo crServo : crServos) {
                    crServo.setPower(power);
                }
                isServoRunning = true;
            }
        }
    }

}
