package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Devices {
    private static Telemetry telemetry;


    public static void setTelemetry(Telemetry telemetry) {
        Devices.telemetry = telemetry;
    }

    public static void setServoPosition(Servo servo, String name, double position) {
        try {
            servo.setPosition(position);
        } catch (Exception e) {
            System.out.println(name + " is null");
            telemetry.addLine(name + " is null");
        }
    }
}
