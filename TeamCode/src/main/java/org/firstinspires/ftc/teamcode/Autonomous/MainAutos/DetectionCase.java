package org.firstinspires.ftc.teamcode.Autonomous.MainAutos;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.A;
import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.B;
import org.firstinspires.ftc.teamcode.Autonomous.C;

import java.util.concurrent.CancellationException;

public class DetectionCase {
    public static int diskAmount = 0;
    public static AutoCase autoCase;

    public static void updateAutoCase(LinearOpMode opMode) {
        switch (diskAmount) {
            case 0:
                autoCase = new A(opMode);
                break;
            case 1:
                autoCase = new B(opMode);
                break;
            case 4:
                autoCase = new C(opMode);
                break;
        }
    }

    public static void setAutoCase(String Case, LinearOpMode opMode) {
        switch (Case) {
            case "A":
                autoCase = new A(opMode);
                break;
            case "B":
                autoCase = new B(opMode);
                break;
            default:
                autoCase = new C(opMode);
                break;
        }
    }
}
