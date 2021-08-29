package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.DiskAmountDetection;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.wallState;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;
import org.firstinspires.ftc.teamcode.TeleOperated.armCase;

public class Initializations {

    public static MyMecanumDrive drive;

    private static void MovementInit(LinearOpMode opMode, Pose2d startPose){
        drive = new MyMecanumDrive(opMode.hardwareMap);
        drive.setPoseEstimate(startPose);
    }

    private static void UtilsInit(LinearOpMode opMode, armCase armCase){
        AutoUtil.setOpMode(opMode);
        AutoUtil.initialization(armCase);
       // AutoUtil.wallPosition(wallState.INSIDE);
    }

    private static void HardwareInit(LinearOpMode  opMode){
        Hardware.init(opMode.hardwareMap, opMode.telemetry, new DiskAmountDetection.UltimateGoalPipeline(opMode, true));

        //TODO: NOW it's without image detection
        //Hardware.init(opMode.hardwareMap, opMode.telemetry);
    }

    public static void AutoInit(LinearOpMode opMode, armCase armCase, Pose2d startPose){
        HardwareInit(opMode);
        MovementInit(opMode, startPose);
        UtilsInit(opMode, armCase);
        Trajectories.setDrive(drive);
    }

}
