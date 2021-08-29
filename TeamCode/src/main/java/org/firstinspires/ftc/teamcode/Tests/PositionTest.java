package org.firstinspires.ftc.teamcode.Tests;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.NormalizeImuAngle;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Main.driveCase;
import org.firstinspires.ftc.teamcode.RoadRunner.Functionalities.GoToPoint;
import org.firstinspires.ftc.teamcode.RoadRunner.Functionalities.PoseStorage;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;
import org.firstinspires.ftc.teamcode.TeleOperated.Movement;

@TeleOp(name = "PositionTest", group = "Tests")
public class PositionTest extends LinearOpMode {

    //TODO wobble arm after auto
    //       NOT SURE IF IT WORKS -without reset encoder
    //        NEAH -touch sensor ?????????
    //       DONE -manual
    //TODO wall on separate buttons
    //TODO wall inside for wobble delivery

    MyMecanumDrive drive;
    public static driveCase currentCase = driveCase.DRIVE;

    //public static Pose2d shootPose = new Pose2d(8, 5, Math.toRadians(163));
    public static double wobbleX = 58;


    private void movementInitialization() {
        drive = new MyMecanumDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Movement.setDrive(drive);
        Movement.setTelemetry(telemetry);
        Pose2d startPose = PoseStorage.currentPose;
        drive.setPoseEstimate(startPose);


    }

    private void initialization() {
        Hardware.init(hardwareMap, telemetry);
        movementInitialization();
        NormalizeImuAngle.setDrive(drive);
        GoToPoint.init(this, drive);
    }


    @Override
    public void runOpMode() {
        initialization();
        waitForStart();


        while (!isStopRequested() && opModeIsActive()) {

            drive.update();

            telemetry.addData("Road runner position is: ", drive.getPoseEstimate());
            telemetry.update();


        }

    }
}
