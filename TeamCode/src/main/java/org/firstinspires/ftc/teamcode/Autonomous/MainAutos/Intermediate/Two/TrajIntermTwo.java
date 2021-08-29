package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.Two;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.ConstantsAutonomous;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PositionCaseModifier;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.wallState;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.grabberPosition;
import org.firstinspires.ftc.teamcode.TeleOperated.wobblePosition;

public class TrajIntermTwo extends Trajectories {

    //public static Pose2d powerShotsPose = new Pose2d(42, 33.46,Math.toRadians(195)); //195

    public static Pose2d shootPose = new Pose2d(42, 33.46,Math.toRadians(180));
    public static Pose2d releaseAPose = new Pose2d(-5, 52, Math.toRadians(50));
    public static Pose2d releaseBPose = new Pose2d(-28, 32, Math.toRadians(50));
    public static Pose2d releaseCPose = new Pose2d(-45, 49, Math.toRadians(50));
    public static Pose2d parkPose = new Pose2d(-7, 8, Math.toRadians(180));
    public static Pose2d diskCollectPose = new Pose2d(10, 32, Math.toRadians(180));
    public static ColorCase colorCase;

    public static void initSpecificTraj(ColorCase colorCase) {
        TrajIntermTwo.colorCase = colorCase;
        if (colorCase==ColorCase.RED) {
            setStartPose(new Pose2d(61.5, 33.46, Math.toRadians(180)), colorCase);
        }
        else {
            setStartPose(new Pose2d(61.5, 33.46 + ConstantsAutonomous.BLUEoffset, Math.toRadians(180)), colorCase);
        }
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
                .addTemporalMarker(1.3, () -> {
                    Wobble.motorArmToPosition(wobblePosition.DOWN);
                })
                .addTemporalMarker(1.8, () -> {
                    Wobble.wobbleRelease();
                })
                .build();
    }

    public static Trajectory releaseCTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(releaseCPose, colorCase))
                .addTemporalMarker(2.2, () -> {
                    Wobble.motorArmToPosition(wobblePosition.DOWN);
                })
                .addTemporalMarker(2.8, () -> {
                    Wobble.wobbleRelease();
                })
                .build();
    }


    public static Trajectory diskCollectTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(diskCollectPose, colorCase),
                        MyMecanumDrive.getVelocityConstraint(AutoCase.intakeVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        MyMecanumDrive.getAccelerationConstraint(AutoCase.intakeAcc))
                .addTemporalMarker(0.1, AutoUtil::startIntake)
                .build();
    }
//    public static Trajectory shootPoseTrajectory(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                .lineToSplineHeading(shootPose)
//                .build();
//    }

    public static Trajectory parkTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(parkPose, colorCase))
                .addTemporalMarker(0.7, () -> {
                    Wobble.SetGrabberPosition(grabberPosition.GRAB);
                    Wobble.motorArmToPosition(wobblePosition.UP);
                })
                .build();
    }

    public static Trajectory goToDiskCollectPoseWithoutIntake(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(diskCollectPose, colorCase))
                .build();
    }

    public static Trajectory ShootTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(shootPose, colorCase))
                .addTemporalMarker(0.7, () -> {
                    //AutoUtil.wallPosition(wallState.VERTICAL);
                })
                .build();
    }
}
