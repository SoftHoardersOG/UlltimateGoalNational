package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro.GyroPID;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PositionCaseModifier;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.shooterState;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.wallState;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;

public class PowerShots {

    private static MyMecanumDrive drive;
    private static LinearOpMode opMode;

    public static double shooterAnglePowerShoot = 0.55;

    public static int firstRotationRED = 21;
    public static int secondRotationRED = -5;
    public static int thirdRotationRED = -5;

    public static int firstRotationBLUE = -21;
    public static int secondRotationBLUE = 5;
    public static int thirdRotationBLUE = 5;

    public static int firstRotation = firstRotationRED;
    public static int secondRotation = secondRotationRED;
    public static int thirdRotation = thirdRotationRED;

    public static int powerShotsShooterSpeed = 1270;

    public static ColorCase colorCase;
    public static Pose2d powerShotsPose = new Pose2d(8, 32, Math.toRadians(180));

    public static void initialization(MyMecanumDrive drive, LinearOpMode opMode, ColorCase colorCase) {
        PowerShots.opMode = opMode;
        PowerShots.drive = drive;
        PowerShots.colorCase = colorCase;
        if (colorCase == ColorCase.BLUE) {
            firstRotation = firstRotationBLUE;
            secondRotation = secondRotationBLUE;
            thirdRotation = thirdRotationBLUE;
        } else{
            firstRotation = firstRotationRED;
            secondRotation = secondRotationRED;
            thirdRotation = thirdRotationRED;
        }
        GyroPID.setDrive(drive);
        AutoUtil.shooterAngle(shooterState.POWER_SHOTS);
        AutoUtil.startShooting();
    }

    public static void run() {
        AutoUtil.startShooting(powerShotsShooterSpeed);
        opMode.sleep(500);
        drive.followTrajectory(powerShotsTrajectory(drive.getPoseEstimate()));
        GyroPID.rotate(firstRotation, opMode.telemetry, opMode);
        opMode.sleep(100);
        AutoUtil.shoot(true, true);
        GyroPID.rotate(secondRotation, opMode.telemetry, opMode);
        AutoUtil.shoot(true, true);
        GyroPID.rotate(thirdRotation, opMode.telemetry, opMode);
        AutoUtil.shoot(true, true);
       // AutoUtil.wallPosition(wallState.INSIDE);
    }
    public static Trajectory powerShotsTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(powerShotsPose, colorCase))
                .build();
    }

}





/**package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro.GyroPID;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PositonCaseModifier;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.shooterState;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.wallState;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;

public class PowerShots {

    private static MyMecanumDrive drive;
    private static LinearOpMode opMode;

    public static double shooterAnglePowerShoot = 0.58; //0.67

    public static int secondRotationRED = -3;
    public static int thirdRotationRED = -4;

    public static int secondRotationBLUE = 3;
    public static int thirdRotationBLUE = 4;

    public static int firstRotation = secondRotationRED;
    public static int secondRotation = thirdRotationRED;

    public static int shootingSpeed = 1270; //todo maybe 1260
    public static ColorCase colorCase;
    public static Pose2d powerShotsPose = new Pose2d(42, 33.46,Math.toRadians(196)); //195

    public static void initialization(MyMecanumDrive drive, LinearOpMode opMode, ColorCase colorCase) {
        PowerShots.opMode = opMode;
        PowerShots.drive = drive;
        PowerShots.colorCase = colorCase;
        if (colorCase == ColorCase.BLUE) {
            firstRotation = secondRotationBLUE;
            secondRotation = thirdRotationBLUE;
        } else{
            firstRotation = secondRotationRED;
            secondRotation = thirdRotationRED;
        }
        GyroPID.setDrive(drive);
        AutoUtil.shooterAngle(shooterState.POWER_SHOTS);
        AutoUtil.startShooting();
    }

    public static void run() {

        drive.followTrajectory(powerShotsTrajectory(drive.getPoseEstimate()));
        AutoUtil.shoot(true, true);
        GyroPID.rotate(firstRotation, opMode.telemetry, opMode);
        AutoUtil.shoot(true, true);
        //todo lasa mai jos
        ChangeShootingAngle.AngleControl(0.57);
        GyroPID.rotate(secondRotation, opMode.telemetry, opMode);
        AutoUtil.shoot(true, true);
        AutoUtil.wallPosition(wallState.VERTICAL);
    }
    public static Trajectory powerShotsTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositonCaseModifier.correct(powerShotsPose, colorCase))
                .build();
    }
}
*/