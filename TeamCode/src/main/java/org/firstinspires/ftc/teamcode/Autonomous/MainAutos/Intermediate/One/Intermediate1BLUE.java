package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.One;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.AutoScenes;
import org.firstinspires.ftc.teamcode.Autonomous.Initializations;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.DetectionCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.DiskAmountDetection;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro.NormalizeAngleGyro;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.Functionalities.PoseStorage;
import org.firstinspires.ftc.teamcode.TeleOperated.armCase;

@Autonomous(name = "Intermediate1BLUE")
public class Intermediate1BLUE extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        TrajIntermOne.initSpecificTraj(ColorCase.BLUE);
        Initializations.AutoInit(this, armCase.RIGHT, TrajIntermOne.getStartPose());
        waitForStart();
        DiskAmountDetection.stopDetection();
        AutoCase.setAutoScene(AutoScenes.INTERMEDIATE1);


        DetectionCase.autoCase.Shoot();
        DetectionCase.autoCase.releaseFirstWobble();
        AutoCase.Park();
        Pose2d finalPose = Initializations.drive.getPoseEstimate();
        PoseStorage.currentPose = new Pose2d(finalPose.getX(),finalPose.getY(), NormalizeAngleGyro.Normalize(Hardware.imu));

        PoseStorage.colorCase = new Pose2d(0);
    }
}

//TODO date nationala
//23 august zoom
//hibrid
//juriz online 28 aug
//5copii+mentor in sala
//m multi?? org
//2 de joc + 1 antr
//fara stand, doar masa+priza+scaune
//rollup/banner
//fara piese rezerva de la natie
//+ringuri bune
//cazare de la noi
//polivalenta - parc tineretului
