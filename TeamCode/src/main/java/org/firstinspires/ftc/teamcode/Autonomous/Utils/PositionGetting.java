package org.firstinspires.ftc.teamcode.Autonomous.Utils;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;
import org.firstinspires.ftc.teamcode.TeleOperated.Movement;

@Config
public class PositionGetting extends LinearOpMode {
    public static double x = 61.5;
    public static double y = 14.6;
    public static double heading = 180;

    private static MyMecanumDrive drive;

    private void movementInitialization() {
        drive = new MyMecanumDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Movement.setDrive(drive);
        Movement.setTelemetry(telemetry);
        Pose2d startPose = new Pose2d(x, y, heading);
        drive.setPoseEstimate(startPose);
        telemetry.addData("startPose is ", startPose);
        telemetry.update();
    }

    private void initialization() {
        Hardware.init(hardwareMap, telemetry);
        movementInitialization();

    }

    @Override
    public void runOpMode() throws InterruptedException {
        initialization();
        waitForStart();
        while (!isStopRequested() && opModeIsActive()) {
            drive.update();
            telemetry.addData("current pose is ", drive.getPoseEstimate());
            telemetry.update();
        }
    }
}
