package org.firstinspires.ftc.teamcode.Debugs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;
import org.firstinspires.ftc.teamcode.TeleOperated.Shooter;
import org.firstinspires.ftc.teamcode.TeleOperated.distanceSensor;

public class Debugs {

    public static void movementSpeedDebug(Telemetry telemetry) {
//        telemetry.addData("front_left: ", Hardware.front_left.getPower());
//        telemetry.addData("front_right: ", Hardware.front_right.getPower());
//        telemetry.addData("back_left: ", Hardware.back_left.getPower());
//        telemetry.addData("back right: ", Hardware.back_right.getPower());
    }

    public static void movementSpeedDebug(Telemetry telemetry, boolean update) {
//        telemetry.addData("front_left: ", Hardware.front_left.getPower());
//        telemetry.addData("front_right: ", Hardware.front_right.getPower());
//        telemetry.addData("back_left: ", Hardware.back_left.getPower());
//        telemetry.addData("back right: ", Hardware.back_right.getPower());
        if (update) {
            telemetry.update();
        }
    }

    public static void angleDebug(Telemetry telemetry, boolean update) {
//
    }

    public static void timerDebug(Telemetry telemetry) {
        telemetry.addData("Timer value: ", Shooter.getTimer());
        telemetry.update();
    }

    public static void encoderDebug(Telemetry telemetry, boolean update) {
//        telemetry.addData("left value: ", Hardware.left_encoder.getCurrentPosition());
//        telemetry.addData("center value: ", Hardware.center_encoder.getCurrentPosition());
//        telemetry.addData("right value: ", -Hardware.right_encoder.getCurrentPosition());
        if (update) {
            telemetry.update();
        }
    }

    public static void grabberPosition(Telemetry telemetry, boolean update) {
        telemetry.addLine("grabber position: " + Hardware.grabber.getCurrentPosition());
        if (update) {
            telemetry.update();
        }
    }

    public static void motorVelocity(Telemetry telemetry, boolean update) {
        telemetry.addData("PID PARAMETERS: ", ((DcMotorEx) Hardware.shooter_right).getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER));
        telemetry.addData("shooter_right", ((DcMotorEx) Hardware.shooter_right).getVelocity());
        telemetry.addData("shooter_left", ((DcMotorEx) Hardware.shooter_left).getVelocity());
        if (update) {
            telemetry.update();
        }
    }

    public static void changeShooterAngleDebug(Telemetry telemetry, boolean update) {
        telemetry.addLine("changeShooterAngle current position is: " + ChangeShootingAngle.getAbsPosition());
        if (update) {
            telemetry.update();
        }
    }

    public static void distanceSensorDebug(Telemetry telemetry, boolean update) {
        telemetry.addLine("distance in MM: " + Hardware.disk_distance.getDistance(DistanceUnit.MM));
        if (update) {
            telemetry.update();
        }
    }

    public static void shouldIntakeDebug(Telemetry telemetry, boolean update) {
        telemetry.addLine("Should intake: " + distanceSensor.shouldIntake);
        if (update) {
            telemetry.update();
        }
    }


}
