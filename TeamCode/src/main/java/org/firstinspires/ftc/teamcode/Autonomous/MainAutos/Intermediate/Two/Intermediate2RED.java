package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.Two;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.AutoScenes;
import org.firstinspires.ftc.teamcode.Autonomous.Initializations;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.DetectionCase;
import org.firstinspires.ftc.teamcode.Autonomous.PowerShots;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.DiskAmountDetection;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro.NormalizeAngleGyro;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.Functionalities.PoseStorage;
import org.firstinspires.ftc.teamcode.TeleOperated.armCase;

@Autonomous(name = "Intermediate2RED")
public class Intermediate2RED extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        TrajIntermTwo.initSpecificTraj(ColorCase.RED);
        Initializations.AutoInit(this, armCase.LEFT, TrajIntermTwo.getStartPose());
        AutoCase.setAutoScene(AutoScenes.INTERMEDIATE2);
        waitForStart();
        PowerShots.initialization(Trajectories.getDrive(), this, ColorCase.RED);
        DiskAmountDetection.stopDetection();
        DetectionCase.setAutoCase("C", this);

        DetectionCase.autoCase.Shoot();
        //if (DetectionCase.diskAmount != 0) { //stamatescu
        if(true){
            AutoCase.Intake();
        }

        PowerShots.initialization(Trajectories.getDrive(), this, ColorCase.RED);
        PowerShots.run();

        DetectionCase.autoCase.releaseFirstWobble();
        AutoCase.Park();
        Pose2d finalPose = Initializations.drive.getPoseEstimate();
        PoseStorage.currentPose = new Pose2d(finalPose.getX(),finalPose.getY(), NormalizeAngleGyro.Normalize(Hardware.imu));
        PoseStorage.colorCase = new Pose2d(1);
    }
}
