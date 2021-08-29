package org.firstinspires.ftc.teamcode.Autonomous.Utils;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;

public class Trajectories {
    public static Trajectory returnBack, trajectory;//,returnBack1,returnBack2;
    protected static MyMecanumDrive drive;
    protected static Pose2d startPose;

    public static Pose2d getStartPose() {
        return startPose;
    }

    public static void setStartPose(Pose2d startPose, ColorCase colorCase) {
        Trajectories.startPose = PositionCaseModifier.correct(startPose,colorCase);
    }

    public static void setDrive(MyMecanumDrive drive) {
        Trajectories.drive = drive;
        NormalizeImuAngle.setDrive(drive);
    }
    public static MyMecanumDrive getDrive(){
        return drive;
    }
//
//    public static Trajectory rightPowerShot(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                .lineTo(new Vector2d(CmToInch.convert(PowerShots.firstPowerShootPositionX),
//                                CmToInch.convert(PowerShots.firstPowerShootPositionY)),
//                        MyMecanumDrive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
//                        MyMecanumDrive.getAccelerationConstraint(35))
//                .build();
//    }
//
//    public static Trajectory middlePowerShot(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                .lineTo(new Vector2d(CmToInch.convert(PowerShots.firstPowerShootPositionX),
//                                CmToInch.convert(PowerShots.firstPowerShootPositionY - PowerShots.distanceBetweenPowerShoots)),
//                        MyMecanumDrive.getVelocityConstraint(40, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
//                        MyMecanumDrive.getAccelerationConstraint(40))
//                .build();
//    }
//
//    public static Trajectory leftPowerShot(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                .lineTo(new Vector2d(CmToInch.convert(PowerShots.firstPowerShootPositionX),
//                                CmToInch.convert(PowerShots.firstPowerShootPositionY - PowerShots.distanceBetweenPowerShoots * 2 + PowerShots.offsetLastPowerShot)),
//                        MyMecanumDrive.getVelocityConstraint(40, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
//                        MyMecanumDrive.getAccelerationConstraint(40))
//                .build();
//    }
//
//    public static Trajectory firstWobbleReleaseC(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                .lineToSplineHeading(new Pose2d(CmToInch.convert(C.firstWobbleX), CmToInch.convert(C.firstWobbleY), NormalizeImuAngle.heading(C.firstWobbleHeading)))
//                .addTemporalMarker(0.5, () -> {
//                    Wobble.motorArmToPosition(true, wobbleMidPosition);
//                })
//                .build();
//    }
//
//    public static Trajectory firstWobbleReleaseB(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                .lineToSplineHeading(new Pose2d(CmToInch.convert(B.firstWobbleX), CmToInch.convert(B.firstWobbleY), NormalizeImuAngle.heading(B.firstWobbleHeading)))
//                .addTemporalMarker(0, () -> {
//                    Wobble.motorArmToPosition(true, wobbleMidPosition);
//                })
//                .build();
//    }
//
//    public static Trajectory firstWobbleReleaseA(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                .lineToSplineHeading(new Pose2d(CmToInch.convert(A.firstWobbleX), CmToInch.convert(A.firstWobbleY), NormalizeImuAngle.heading(A.firstWobbleHeading)))
//                .addTemporalMarker(0, () -> {
//                    Wobble.motorArmToPosition(true, wobbleMidPosition);
//                })
//                .build();
//    }
//
//
//    public static Trajectory returnBackC(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                .lineToSplineHeading(new Pose2d(pose2d.getX(), 36, Math.toRadians(180)),
//                        MyMecanumDrive.getVelocityConstraint(50, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
//                        MyMecanumDrive.getAccelerationConstraint(30))
//                .splineToConstantHeading(new Vector2d(38, 21), Math.toRadians(20),
//                        MyMecanumDrive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
//                        MyMecanumDrive.getAccelerationConstraint(30))
//                .addTemporalMarker(2, () -> {
//                    AutoUtil.wallPosition(wallState.VERTICAL);
//                })
//                .build();
//    }
//
//    public static Trajectory returnBackA(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                .lineToSplineHeading(new Pose2d(pose2d.getX(), 36, Math.toRadians(180)),
//                        MyMecanumDrive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
//                        MyMecanumDrive.getAccelerationConstraint(35))
//                .splineToConstantHeading(new Vector2d(38, 20), Math.toRadians(0),
//                        MyMecanumDrive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
//                        MyMecanumDrive.getAccelerationConstraint(35))
//                .addTemporalMarker(2, () -> {
//                    AutoUtil.wallPosition(wallState.VERTICAL);
//                })
//                .build();
//    }
//
//
//    public static Trajectory returnBackB(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                .lineToSplineHeading(new Pose2d(pose2d.getX(), 20, Math.toRadians(180)),
//                        MyMecanumDrive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
//                        MyMecanumDrive.getAccelerationConstraint(35))
//                .splineToConstantHeading(new Vector2d(38, 20), Math.toRadians(0),
//                        MyMecanumDrive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
//                        MyMecanumDrive.getAccelerationConstraint(35))
//                .addTemporalMarker(2, () -> {
//                    AutoUtil.wallPosition(wallState.VERTICAL);
//                })
//                .build();
//    }
//
//
//    public static Trajectory secondWobbleCollect(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                .lineToConstantHeading(new Vector2d(AutoCase.wobblePos2X, AutoCase.wobblePos2Y), MyMecanumDrive.getVelocityConstraint(AutoCase.wobbleVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
//                        MyMecanumDrive.getAccelerationConstraint(AutoCase.wobbleAcc))
//                .addTemporalMarker(1.5, () -> {
//                    Wobble.motorArmToPosition(true, GeneralAutoParameters.wobbleCollectPosition + 80);
//                    Wobble.close();
//                })
//                .build();
//    }
//
//    public static Trajectory diskCollect(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                .lineToLinearHeading(new Pose2d(intakeX, intakeY, NormalizeImuAngle.heading(200)), MyMecanumDrive.getVelocityConstraint(intakeVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
//                        MyMecanumDrive.getAccelerationConstraint(intakeAcc))
//                .build();
//    }
//
//    public static Trajectory shootPoseTrajectory(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                .lineToSplineHeading(new Pose2d(shootPoseX, shootPoseY, Math.toRadians(shootAngle)))
//                .addTemporalMarker(0.1, AutoUtil::stopIntakeMotor)
//                .addTemporalMarker(0.8, AutoUtil::stopIntakeServo)
//                .addTemporalMarker(0.9, () -> {
//                    AutoUtil.startShooting(AutoCase.shootSpeed);
//                })
//                .build();
//    }
//
//    public static Trajectory secondWobbleReleaseC(Pose2d pose2d) {
//        trajectory = drive.trajectoryBuilder(pose2d)
//                .splineToSplineHeading(new Pose2d(CmToInch.convert(C.secondWobbleX), CmToInch.convert(C.secondWobbleY), NormalizeImuAngle.heading(C.secondWobbleHeading)), Math.toRadians(160))
//                .addTemporalMarker(0.3, () -> {
//                    Wobble.motorArmToPosition(true, wobbleMidPosition);
//                })
//                .addTemporalMarker(2.1, Wobble::wobbleRelease)
//                .build();
//
//        return trajectory;
//    }
//
//    public static Trajectory secondWobbleReleaseA(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                .lineToSplineHeading(new Pose2d(CmToInch.convert(A.secondWobbleX), CmToInch.convert(A.secondWobbleY), NormalizeImuAngle.heading(A.secondWobbleHeading)))
//                .build();
//    }
//
//    public static Trajectory secondWobbleReleaseB(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                .lineToSplineHeading(new Pose2d(CmToInch.convert(B.secondWobbleX), CmToInch.convert(B.secondWobbleY), NormalizeImuAngle.heading(B.secondWobbleHeading)))
//                .build();
//    }


}
