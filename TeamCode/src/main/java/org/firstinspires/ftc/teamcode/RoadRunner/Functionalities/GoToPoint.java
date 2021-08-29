package org.firstinspires.ftc.teamcode.RoadRunner.Functionalities;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro.GyroPID;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro.NormalizeAngleGyro;
import org.firstinspires.ftc.teamcode.ConstantsTeleoperated;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Main.Teleoperated;
import org.firstinspires.ftc.teamcode.Main.driveCase;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.wobblePosition;
import org.firstinspires.ftc.teamcode.Utils.Gamepads.OneTap;

@Config
public class GoToPoint {
    private static LinearOpMode opMode;

    public static double allowedDistance = 15f;

    public static void init(LinearOpMode opMode, MyMecanumDrive drive) {
        GyroPID.setDrive(drive);
        GoToPoint.opMode = opMode;
    }

    public static double distanceBetween2Points(Vector2d pos1, Vector2d pos2) {
        return Math.sqrt(Math.abs(pos1.getX() - pos2.getX()) * Math.abs(pos1.getX() -
                pos2.getX()) + Math.abs(pos1.getY() - pos2.getY()) * Math.abs(pos1.getY() - pos2.getY()));
    }

    //unused
    public static void spline(boolean button, double x, double y, double heading, MyMecanumDrive drive) {
        OneTap oneTap = new OneTap();
        if (oneTap.onPress(button)) {
            Pose2d poseEstimate = drive.getPoseEstimate();
            Trajectory trajectory = drive.trajectoryBuilder(poseEstimate)
                    .splineToSplineHeading(new Pose2d(x, y, heading), 0,
                            MyMecanumDrive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                            MyMecanumDrive.getAccelerationConstraint(35))
                    .addDisplacementMarker(() -> {
                        Teleoperated.currentCase = driveCase.DRIVE;
                    })
                    .build();
            Teleoperated.currentCase = driveCase.AUTO;
            drive.followTrajectoryAsync(trajectory);
        }
    }

    public static void spline(boolean button, Pose2d pose2d, MyMecanumDrive drive) {
        OneTap oneTap = new OneTap();
        if (oneTap.onPress(button)) {
            Pose2d poseEstimate = drive.getPoseEstimate();
            Trajectory trajectory = drive.trajectoryBuilder(poseEstimate)
                    .splineToSplineHeading(pose2d, 0,
                            MyMecanumDrive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                            MyMecanumDrive.getAccelerationConstraint(35))
                    .addDisplacementMarker(() -> {
                        Teleoperated.currentCase = driveCase.DRIVE;
                    })
                    .build();
            Teleoperated.currentCase = driveCase.AUTO;
            drive.followTrajectoryAsync(trajectory);
        }
    }

    public static void strafe(boolean button, double x, double y, double heading, MyMecanumDrive drive) {
        OneTap oneTap = new OneTap();
        if (oneTap.onPress(button)) {
            Pose2d poseEstimate = drive.getPoseEstimate();
            Trajectory trajectory = drive.trajectoryBuilder(poseEstimate)
                    .lineToSplineHeading(new Pose2d(x, y, heading),
                            MyMecanumDrive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                            MyMecanumDrive.getAccelerationConstraint(35))
                    .addDisplacementMarker(() -> {
                        Teleoperated.currentCase = driveCase.DRIVE;
                    })
                    .build();
            Teleoperated.currentCase = driveCase.AUTO;
            drive.followTrajectoryAsync(trajectory);
        }
    }

    public static void strafe(boolean button, double x, double y, double heading, MyMecanumDrive drive, double accel, double vel, boolean wobble) {
        OneTap oneTap = new OneTap();
        if (oneTap.onPress(button)) {
            if (wobble) {
                Wobble.motorArmToPosition(true, wobblePosition.UP);
               // Wall.wallClose(true);
            }
            Pose2d poseEstimate = drive.getPoseEstimate();
            Trajectory trajectory = drive.trajectoryBuilder(poseEstimate)
                    .lineToSplineHeading(new Pose2d(x, y, heading),
                            MyMecanumDrive.getVelocityConstraint(vel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                            MyMecanumDrive.getAccelerationConstraint(accel))
                    .addDisplacementMarker(() -> {
                        Teleoperated.currentCase = driveCase.DRIVE;
                    })
                    .build();
            Teleoperated.currentCase = driveCase.AUTO;
            drive.followTrajectoryAsync(trajectory);
        }
    }

    public static void strafe(boolean button, Pose2d pose2d, MyMecanumDrive drive) {
        OneTap oneTap = new OneTap();
        if (oneTap.onPress(button)) {
            Pose2d poseEstimate = drive.getPoseEstimate();
            Trajectory trajectory = drive.trajectoryBuilder(poseEstimate)
                    .lineToSplineHeading(pose2d,
                            MyMecanumDrive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                            MyMecanumDrive.getAccelerationConstraint(35))
                    .build();
            Teleoperated.currentCase = driveCase.AUTO;
            drive.followTrajectoryAsync(trajectory);
        }
    }

    public static void POWERSHOTS(boolean button, MyMecanumDrive drive) {
        OneTap oneTap = new OneTap();
        if (oneTap.onPress(button)) {
            ChangeShootingAngle.changeAngle(true, 0.52);
            ChangeShootingAngle.update();
            opMode.sleep(500);
            GyroPID.rotate(14, opMode.telemetry, opMode);
            AutoUtil.shoot(true, true);
            GyroPID.rotate(7, opMode.telemetry, opMode);
            AutoUtil.shoot(true, true);
            GyroPID.rotate(4, opMode.telemetry, opMode);
            AutoUtil.shoot(true, true);
        }
    }

    public static void HighGoalAutoOrientation(boolean button, MyMecanumDrive drive) {
        OneTap oneTap = new OneTap();
        if (oneTap.onPress(button)) {
            ChangeShootingAngle.AutomaticDistanceAngle();
            Pose2d robotPosition = drive.getPoseEstimate();
            double absolutRotationNeeded = Math.atan((robotPosition.getY() - ConstantsTeleoperated.towerCoordinates.getY()) / (robotPosition.getX() - ConstantsTeleoperated.towerCoordinates.getX()));
            double neededForStraightening = 180 - Math.toDegrees(Teleoperated.drive.getPoseEstimate().getHeading());
            opMode.telemetry.addData("ANGLE FOR TURN!!!!!!! ", neededForStraightening + absolutRotationNeeded);
            opMode.telemetry.update();
            GyroPID.rotate(neededForStraightening + Math.toDegrees(absolutRotationNeeded), opMode.telemetry, opMode);

        }
    }

    public static void HighGoalAutoOrientationImu(boolean button, MyMecanumDrive drive) {
        OneTap oneTap = new OneTap();
        if (oneTap.onPress(button)) {
            if (drive.getPoseEstimate().getX() > 0) {
                ChangeShootingAngle.AutomaticDistanceAngle();
            }
            Pose2d robotPosition = drive.getPoseEstimate();
            double absolutRotationNeeded = Math.atan(robotPosition.getY() - ConstantsTeleoperated.towerCoordinates.getY()) / (robotPosition.getX() - ConstantsTeleoperated.towerCoordinates.getX());
//            double neededForStraightening = 180 - NormalizeImuAngle.convert(Hardware.imu.getAngularOrientation().firstAngle);
            double neededForStraightening = 180 - NormalizeAngleGyro.Normalize(Hardware.imu);

            GyroPID.rotate(neededForStraightening + Math.toDegrees(absolutRotationNeeded), opMode.telemetry, opMode, true);

        }
    }

    public static double shootingAngleForDistance() {
        return ConstantsTeleoperated.fA * distanceBetweenRobotAndTower() + ConstantsTeleoperated.fB;
    }

    public static double distanceBetweenRobotAndTower() {
        return distanceBetween2Points(new Vector2d(Teleoperated.drive.getPoseEstimate().getX(), Teleoperated.drive.getPoseEstimate().getY()), ConstantsTeleoperated.towerCoordinates);
    }

    public static boolean FieldWallDistanceCheck(MyMecanumDrive drive) {
        Pose2d currentPosition = drive.getPoseEstimate();
        return (Math.abs(ConstantsTeleoperated.cornerLeftUp.getX() - currentPosition.getX()) <= allowedDistance ||
                Math.abs(ConstantsTeleoperated.cornerRightUp.getX() - currentPosition.getX()) <= allowedDistance ||
                Math.abs(ConstantsTeleoperated.cornerLeftDown.getY() - currentPosition.getY()) <= allowedDistance ||
                Math.abs(ConstantsTeleoperated.cornerRightDown.getY() - currentPosition.getY()) <= allowedDistance);
        //currentPosition.getY()+allowedDistance <= PoseStorage.cornerLeftUp.getY() && currentPosition.getY() - allowedDistance >= PoseStorage.cornerRightDown.getY() );
    }
}
