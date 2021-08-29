package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.RobotLog;

import java.util.Objects;
import java.util.TimerTask;

public class EncoderReader extends TimerTask {

    public EncoderReader(DcMotor leftEncoder, DcMotor rightEncoder, DcMotor centerEncoder) {
        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;
        this.centerEncoder = centerEncoder;
    }

    private final DcMotor leftEncoder;
    private final DcMotor rightEncoder;
    private final DcMotor centerEncoder;


    private String leftEncoderValue;
    private String rightEncoderValue;
    private String centerEncoderValue;


    public static String getEncoderTicks(DcMotor encoder, String name) {
        try {
            Objects.requireNonNull(encoder, "Motor " + name + "is null!");
            return name + ": " + encoder.getCurrentPosition() / 4;

        } catch (Exception e) {
            System.err.println("Exception occurred while reading encoder value: " + e.getMessage());
            return name + ": N/A";
        }
    }

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(200);
//                rightEncoderValue = getEncoderTicks(rightEncoder, "Right Encoder");
//                leftEncoderValue = getEncoderTicks(leftEncoder, "Left Encoder");
                centerEncoderValue = getEncoderTicks(centerEncoder, "Center Encoder");
                RobotLog.i(leftEncoderValue + " " + centerEncoderValue + " " + rightEncoderValue);
            } catch (Exception e) {
                RobotLog.d("Exception occurred while reading encoders from other thread: " + e.getMessage());
            }
        }
    }

    public String getLeftEncoderValue() {
        return leftEncoderValue;
    }

    public String getRightEncoderValue() {
        return rightEncoderValue;
    }

    public String getCenterEncoderValue() {
        return centerEncoderValue;
    }
}
