package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.One;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Autonomous.ConstantsAutonomous;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PositionCaseModifier;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.grabberPosition;
import org.firstinspires.ftc.teamcode.TeleOperated.wobblePosition;

public class TrajIntermOne extends Trajectories {

    //inch and rad
    public static Pose2d shootPose = new Pose2d(7,52,Math.toRadians(190));
    public static Pose2d releaseAPose = new Pose2d(-5,52,Math.toRadians(60));
    public static Pose2d releaseBPose = new Pose2d(-40,52,Math.toRadians(180));
    public static Pose2d releaseCPose = new Pose2d(-48,52,Math.toRadians(50));
    public static Pose2d parkPose = new Pose2d(2,56,Math.toRadians(180));
    public static ColorCase colorCase;

    public static void initSpecificTraj(ColorCase colorCase){
        TrajIntermOne.colorCase = colorCase;

        if (colorCase==ColorCase.RED) {
            setStartPose(new Pose2d(61.5, 57.87,Math.toRadians(180)), colorCase);
        }
        else {
            setStartPose(new Pose2d(61.5, 57.87+ ConstantsAutonomous.BLUEoffset, Math.toRadians(180)), colorCase);
        }


        //setStartPose(new Pose2d(61.5, 57.87,Math.toRadians(180)), colorCase);
    }

    public static Trajectory shootTrajectory(Pose2d pose2d) {

        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(shootPose,colorCase))
//                .addTemporalMarker(0.5, () -> {
//                    AutoUtil.startShooting();
//                })
                .build();
    }

    public static Trajectory releaseATrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(releaseAPose,colorCase))
                .addTemporalMarker(0.7, () -> {
                    Wobble.motorArmToPosition(wobblePosition.DOWN);
                })
                .addTemporalMarker(1.5, () -> {
                    Wobble.wobbleRelease();
                })
                .build();
    }

    public static Trajectory releaseBTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(releaseBPose,colorCase))
                .addTemporalMarker(0.7, () -> {
                    Wobble.motorArmToPosition(wobblePosition.DOWN);
                })
                .addTemporalMarker(1.5, () -> {
                    Wobble.wobbleRelease();
                })
                .build();
    }

    public static Trajectory releaseCTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(releaseCPose,colorCase))
                .addTemporalMarker(1.5, () -> {
                    Wobble.motorArmToPosition(wobblePosition.DOWN);
                })
                .addTemporalMarker(1.9, () -> {
                    Wobble.wobbleRelease();
                })
                .build();
    }

    public static Trajectory parkTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(parkPose,colorCase))
                .addTemporalMarker(1, () ->{
                    Wobble.SetGrabberPosition(grabberPosition.GRAB);
                    Wobble.motorArmToPosition(wobblePosition.UP);
                })
                .build();
    }

}
