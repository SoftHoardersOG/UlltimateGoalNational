package org.firstinspires.ftc.teamcode.Utils.Logics;

import com.qualcomm.robotcore.hardware.Servo;

public class ServoCommandGroup {
    public Servo servo;
    public double firstPosition;
    public double secondPosition;

    public ServoCommandGroup(Servo servo, double firstPosition, double secondPosition) {
        this.servo = servo;
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
    }
}
