package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Quantum.InteriorLine;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Autonomous.ConstantsAutonomous;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Full.TrajFull;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PositionCaseModifier;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.grabberPosition;
import org.firstinspires.ftc.teamcode.TeleOperated.wobblePosition;

public class TrajQuantumInterior extends Trajectories {

    public static ColorCase colorCase;
    public static Pose2d diskCollectPose = new Pose2d(10, 32, Math.toRadians(180)); //10
    public static Pose2d shootPose = new Pose2d(42, 33.46, Math.toRadians(180));
    public static Pose2d releaseAPose = new Pose2d(-10, 36, Math.toRadians(180)); //-10 for blue go back to +10
    public static Pose2d releaseBPose = new Pose2d(-18, 10, Math.toRadians(230));
    public static Pose2d releaseCPose = new Pose2d(-53, 37, Math.toRadians(200));
    public static Pose2d slideReturnPS = new Pose2d(-53, 39, Math.toRadians(180));
    public static Pose2d backReturnPS = new Pose2d(12, 15, Math.toRadians(181));
    public static Pose2d forwardApose = new Pose2d(-20, 15, Math.toRadians(180));
    public static Pose2d forwardBpose = new Pose2d(-26, 4, Math.toRadians(180));
    public static Pose2d forwardCpose = new Pose2d(-53.5, 15, Math.toRadians(186));
    public static Pose2d parkPose = new Pose2d(-5, 10, Math.toRadians(180));


    public static void initSpecificTraj(ColorCase colorCase) {
        TrajQuantumInterior.colorCase = colorCase;
        if (colorCase == ColorCase.RED) {
            setStartPose(new Pose2d(61.5, 15.46, Math.toRadians(180)), colorCase);
        } else {
            setStartPose(new Pose2d(61.5, 15.46 + ConstantsAutonomous.BLUEoffset, Math.toRadians(180)), colorCase);
        }
    }

    public static Trajectory goForwardA(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(forwardApose, colorCase))
                .build();
    }

    public static Trajectory goForwardB(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(forwardBpose, colorCase))
                .build();
    }

    public static Trajectory goForwardC(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(forwardCpose, colorCase))
                .build();
    }

    public static Trajectory releaseATrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(releaseAPose, colorCase))
                .addTemporalMarker(1.5, () -> {
                    Wobble.motorArmToPosition(wobblePosition.DOWN);
                })
                .addTemporalMarker(2.0, () -> {
                    Wobble.wobbleRelease();
                })
                .build();
    }

    public static Trajectory releaseBTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(releaseBPose, colorCase))
                .addTemporalMarker(0.7, () -> {
                    Wobble.motorArmToPosition(wobblePosition.DOWN);
                })
                .addTemporalMarker(1, () -> {
                    Wobble.wobbleRelease();
                })
                .build();
    }

    public static Trajectory releaseCTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(releaseCPose, colorCase))
                .addTemporalMarker(1.7, () -> {
                    Wobble.motorArmToPosition(wobblePosition.DOWN);
                })
                .addTemporalMarker(2, () -> {
                Wobble.wobbleRelease();
                })
                .build();
    }


    public static Trajectory returnPowerShots(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(backReturnPS, colorCase))
                .addTemporalMarker(0, () -> {
                    Wobble.SetGrabberPosition(grabberPosition.GRAB);
                    Wobble.motorArmToPosition(wobblePosition.UP);
                })
                .build();
    }

    public static Trajectory ParkTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(parkPose, colorCase))

                .build();
    }
}
