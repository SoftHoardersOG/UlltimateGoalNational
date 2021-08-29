package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Full;

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

public class TrajFull extends Trajectories {

    public static ColorCase colorCase;
    public static Pose2d diskCollectPose = new Pose2d(18, 33, Math.toRadians(178)); //10
    //public static Pose2d diskCollectPose = new Pose2d(8, 32, Math.toRadians(180)); //10
    public static Pose2d shootPose = new Pose2d(42, 33.46,Math.toRadians(180));
    public static Pose2d releaseAPose = new Pose2d(-6, 48, Math.toRadians(250));
    public static Pose2d releaseBPose = new Pose2d(-28, 27, Math.toRadians(250));
    public static Pose2d releaseCPose = new Pose2d(-53, 39, Math.toRadians(250));
    public static Pose2d backPose = new Pose2d(42, 22, Math.toRadians(180));//x=35.5
    //    public static Pose2d backAPose = new Pose2d(41, 32, Math.toRadians(180));
//    public static Pose2d backBPose = new Pose2d(41, 32, Math.toRadians(180));
//    public static Pose2d backCPose = new Pose2d(41, 32, Math.toRadians(180));
    public static Pose2d collectSecondWobblePose = new Pose2d(42, 35, Math.toRadians(180));
    public static Pose2d secWobbleAPose = new Pose2d(-4, 39, Math.toRadians(250));
    public static Pose2d secWobbleBPose = new Pose2d(-26, 19, Math.toRadians(250));
    public static Pose2d secWobbleCPose = new Pose2d(-51, 45, Math.toRadians(250));
    public static Pose2d parkPose = new Pose2d(-7, 8, Math.toRadians(180));

    public static void initSpecificTraj(ColorCase colorCase) {
        TrajFull.colorCase = colorCase;
        if (colorCase==ColorCase.RED) {
            setStartPose(new Pose2d(61.5, 33.46, Math.toRadians(180)), colorCase);
        }
        else {
            setStartPose(new Pose2d(61.5, 33.46 + ConstantsAutonomous.BLUEoffset, Math.toRadians(180)), colorCase);
        }

    }

    public static Trajectory diskCollectTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
//                .lineToSplineHeading(PositionCaseModifier.correct(diskCollectPose, colorCase),
//                        MyMecanumDrive.getVelocityConstraint(AutoCase.intakeVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH), //todo
//                        MyMecanumDrive.getAccelerationConstraint(AutoCase.intakeAcc))
                .lineToSplineHeading(PositionCaseModifier.correct(diskCollectPose, colorCase))
                .addTemporalMarker(0, AutoUtil::startIntake)
                .build();
    }

    public static Trajectory goToDiskCollectPoseWithoutIntake(Pose2d pose2d){
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(diskCollectPose, colorCase))
                .build();
    }
    //    public static Trajectory shootPoseTrajectory(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                .lineToSplineHeading(shootPose)
//                .build();
//    }

    public static Trajectory ShootTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(shootPose, colorCase))
                .addTemporalMarker(0.7, () -> {
                    //AutoUtil.wallPosition(wallState.VERTICAL);
                })
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
                .addTemporalMarker(1.5, () -> {
                    Wobble.motorArmToPosition(wobblePosition.DOWN);
                })
                .addTemporalMarker(2.0, () -> {
                    Wobble.wobbleRelease();
                })
                .build();
    }

    public static Trajectory returnBack(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(backPose, colorCase))
                .build();
    }

    public static Trajectory collectSecondWobbleTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToLinearHeading(PositionCaseModifier.correct(collectSecondWobblePose, colorCase))
                .build();
    }
//    public static Trajectory returnBackA(Pose2d pose2d) {
//        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
//                .lineToSplineHeading(backPose)
//                .splineToLinearHeading(collectSecondWobblePose, Math.toRadians(0))
//                .build();
//    }
//
//    public static Trajectory returnBackB(Pose2d pose2d) {
//        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
//                .lineToSplineHeading(backPose)
//                .splineToLinearHeading(collectSecondWobblePose, Math.toRadians(0))
//                .build();
//    }
//
//    public static Trajectory returnBackC(Pose2d pose2d) {
//        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
//                .lineToSplineHeading(backPose)
//                .splineToLinearHeading(collectSecondWobblePose, Math.toRadians(0))
//                .build();
//    }

    public static Trajectory secondWobbleTrajA(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(secWobbleAPose, colorCase))
                .build();
    }

    public static Trajectory secondWobbleTrajB(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(secWobbleBPose, colorCase))
                .build();
    }

    public static Trajectory secondWobbleTrajC(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(secWobbleCPose, colorCase))
                .build();
    }

    public static Trajectory parkTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(parkPose, colorCase))
                .addTemporalMarker(0.7, () -> {
                    Wobble.SetGrabberPosition(grabberPosition.GRAB);
                    Wobble.motorArmToPosition(wobblePosition.UP);
                })
                .build();
    }

//    public static Trajectory intakeTrajectory(Pose2d pose2d) {
//        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
//                .lineToSplineHeading(GeneralAutoParameters.intakePose)
//                .build();
//    }


}



/**package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Full;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.GeneralAutoParameters;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.One.TrajIntermOne;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PositonCaseModifier;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.wallState;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.grabberPosition;
import org.firstinspires.ftc.teamcode.TeleOperated.wobblePosition;
import org.opencv.core.Mat;

public class TrajFull extends Trajectories {

    public static ColorCase colorCase;
    public static Pose2d diskCollectPose = new Pose2d(15.5, 33.46, Math.toRadians(180)); //10
    //public static Pose2d shootPose = new Pose2d(0,0,Math.toRadians(180));
    public static Pose2d releaseAPose = new Pose2d(-6, 48, Math.toRadians(250));
    public static Pose2d releaseBPose = new Pose2d(-28, 27, Math.toRadians(250));
    public static Pose2d releaseCPose = new Pose2d(-53, 39, Math.toRadians(250));
    public static Pose2d backPose = new Pose2d(35.5, 22, Math.toRadians(180));
    //    public static Pose2d backAPose = new Pose2d(41, 32, Math.toRadians(180));
//    public static Pose2d backBPose = new Pose2d(41, 32, Math.toRadians(180));
//    public static Pose2d backCPose = new Pose2d(41, 32, Math.toRadians(180));
    public static Pose2d collectSecondWobblePose = new Pose2d(42, 34, Math.toRadians(180));
    public static Pose2d secWobbleAPose = new Pose2d(-4, 39, Math.toRadians(250));
    public static Pose2d secWobbleBPose = new Pose2d(-26, 19, Math.toRadians(250));
    public static Pose2d secWobbleCPose = new Pose2d(-51, 45, Math.toRadians(250));
    public static Pose2d parkPose = new Pose2d(-1, 30, Math.toRadians(180));

    public static void initSpecificTraj(ColorCase colorCase) {
        TrajFull.colorCase = colorCase;
        if (colorCase==ColorCase.RED) {
            setStartPose(new Pose2d(61.5, 33.46, Math.toRadians(180)), colorCase);
        }
        else {
            setStartPose(new Pose2d(61.5, 33.46 - 3, Math.toRadians(180)), colorCase);
        }

    }

    public static Trajectory diskCollectTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositonCaseModifier.correct(diskCollectPose, colorCase),
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

    public static Trajectory releaseATrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositonCaseModifier.correct(releaseAPose, colorCase))
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
                .lineToSplineHeading(PositonCaseModifier.correct(releaseBPose, colorCase))
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
                .lineToSplineHeading(PositonCaseModifier.correct(releaseCPose, colorCase))
                .addTemporalMarker(1.5, () -> {
                    Wobble.motorArmToPosition(wobblePosition.DOWN);
                })
                .addTemporalMarker(2.0, () -> {
                    Wobble.wobbleRelease();
                })
                .build();
    }

    public static Trajectory returnBack(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositonCaseModifier.correct(backPose, colorCase))
                .build();
    }

    public static Trajectory collectSecondWobbleTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToLinearHeading(PositonCaseModifier.correct(collectSecondWobblePose, colorCase))
                .build();
    }
//    public static Trajectory returnBackA(Pose2d pose2d) {
//        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
//                .lineToSplineHeading(backPose)
//                .splineToLinearHeading(collectSecondWobblePose, Math.toRadians(0))
//                .build();
//    }
//
//    public static Trajectory returnBackB(Pose2d pose2d) {
//        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
//                .lineToSplineHeading(backPose)
//                .splineToLinearHeading(collectSecondWobblePose, Math.toRadians(0))
//                .build();
//    }
//
//    public static Trajectory returnBackC(Pose2d pose2d) {
//        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
//                .lineToSplineHeading(backPose)
//                .splineToLinearHeading(collectSecondWobblePose, Math.toRadians(0))
//                .build();
//    }

    public static Trajectory secondWobbleTrajA(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositonCaseModifier.correct(secWobbleAPose, colorCase))
                .build();
    }

    public static Trajectory secondWobbleTrajB(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositonCaseModifier.correct(secWobbleBPose, colorCase))
                .build();
    }

    public static Trajectory secondWobbleTrajC(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositonCaseModifier.correct(secWobbleCPose, colorCase))
                .build();
    }

    public static Trajectory parkTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositonCaseModifier.correct(parkPose, colorCase))
                .addTemporalMarker(0.7, () -> {
                    Wobble.SetGrabberPosition(grabberPosition.GRAB);
                    Wobble.motorArmToPosition(wobblePosition.UP);
                })
                .build();
    }

//    public static Trajectory intakeTrajectory(Pose2d pose2d) {
//        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
//                .lineToSplineHeading(GeneralAutoParameters.intakePose)
//                .build();
//    }


}
*/