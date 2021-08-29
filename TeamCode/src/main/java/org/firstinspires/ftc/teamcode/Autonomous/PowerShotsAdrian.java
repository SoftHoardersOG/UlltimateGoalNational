package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro.GyroPID;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.shooterState;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;
import org.firstinspires.ftc.teamcode.Utils.Logics.CmToInch;
@Config
public class PowerShotsAdrian {

    public static double shooterAnglePowerShoot = 0.61; //0.67
    public static int shootingSpeed = 1250;

    public static double firstPowerShootPositionY = 37;
    public static double firstPowerShootPositionX = 8; // 5
//61.5, 33.46,Math.toRadians(180)
    public static double distanceBetweenPowerShoots = 20; //20.5
    public static double offsetLastPowerShot = 0.5;

    private static MyMecanumDrive drive;
    private static LinearOpMode opMode;

    public static void initialization(MyMecanumDrive drive, LinearOpMode opMode) {
        PowerShotsAdrian.opMode = opMode;
        PowerShotsAdrian.drive = drive;
        GyroPID.setDrive(drive);
        AutoUtil.shooterAngle(shooterState.POWER_SHOTS);
        AutoUtil.startShooting();
    }

    private static void PowerShootDown() {
//        AutoUtil.waitForShooting(drive);
        AutoUtil.shoot(true, true);
    }

    private static void FollowTrajectoryAndShoot(Trajectory trajectory, MyMecanumDrive drive) {

        drive.followTrajectory(trajectory);
        GyroPID.rotate(-2, opMode.telemetry, opMode);
        PowerShootDown();
        GyroPID.rotate(7, opMode.telemetry, opMode);
        PowerShootDown();
        GyroPID.rotate(6, opMode.telemetry, opMode); // maybe 7
        PowerShootDown();
    }

    public static void run() {

        FollowTrajectoryAndShoot(rightPowerShot(drive.getPoseEstimate()), drive);

        //     FollowTrajectoryAndShoot(middlePowerShot(drive.getPoseEstimate()), drive);

//       FollowTrajectoryAndShoot(leftPowerShot(drive.getPoseEstimate()), drive);

        AutoUtil.stopShooting();
        AutoUtil.shooterAngle(shooterState.INTAKE);
    }

    public static Trajectory rightPowerShot(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineTo(new Vector2d(CmToInch.convert(PowerShotsAdrian.firstPowerShootPositionX),
                                CmToInch.convert(PowerShotsAdrian.firstPowerShootPositionY)),
                        MyMecanumDrive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        MyMecanumDrive.getAccelerationConstraint(35))
                .build();
    }

    public static Trajectory middlePowerShot(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineTo(new Vector2d(CmToInch.convert(PowerShotsAdrian.firstPowerShootPositionX),
                                CmToInch.convert(PowerShotsAdrian.firstPowerShootPositionY - PowerShotsAdrian.distanceBetweenPowerShoots)),
                        MyMecanumDrive.getVelocityConstraint(40, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        MyMecanumDrive.getAccelerationConstraint(40))
                .build();
    }

    public static Trajectory leftPowerShot(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineTo(new Vector2d(CmToInch.convert(PowerShotsAdrian.firstPowerShootPositionX),
                                CmToInch.convert(PowerShotsAdrian.firstPowerShootPositionY - PowerShotsAdrian.distanceBetweenPowerShoots * 2 + PowerShotsAdrian.offsetLastPowerShot)),
                        MyMecanumDrive.getVelocityConstraint(40, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        MyMecanumDrive.getAccelerationConstraint(40))
                .build();
    }

}
