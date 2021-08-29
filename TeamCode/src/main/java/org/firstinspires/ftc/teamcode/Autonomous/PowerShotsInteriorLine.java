package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Quantum.InteriorLine.TrajQuantumInterior;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro.GyroPID;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.shooterState;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;

public class PowerShotsInteriorLine {
    private static MyMecanumDrive drive;
    private static LinearOpMode opMode;

    public static double shooterAnglePowerShoot = 0.55;

    public static int firstRotationRED = 11;
    public static int secondRotationRED = -5;
    public static int thirdRotationRED = -5;

    public static int firstRotationBLUE = -10;
    public static int secondRotationBLUE = 5;
    public static int thirdRotationBLUE = 5;

    public static int firstRotation = firstRotationRED;
    public static int secondRotation = secondRotationRED;
    public static int thirdRotation = thirdRotationRED;

    public static int powerShotsShooterSpeed = 1270;

    public static ColorCase colorCase;
    //public static Pose2d powerShotsPose = new Pose2d(15.5, 33.46,Math.toRadians(201)); //196

    public static void initialization(MyMecanumDrive drive, LinearOpMode opMode, ColorCase colorCase) {
        PowerShotsInteriorLine.opMode = opMode;
        PowerShotsInteriorLine.drive = drive;
        PowerShotsInteriorLine.colorCase = colorCase;
        if (colorCase == ColorCase.BLUE) {
            firstRotation = firstRotationBLUE;
            secondRotation = secondRotationBLUE;
            thirdRotation = thirdRotationBLUE;
        } else {
            firstRotation = firstRotationRED;
            secondRotation = secondRotationRED;
            thirdRotation = thirdRotationRED;
        }
        GyroPID.setDrive(drive);
        ChangeShootingAngle.AngleControl(shooterAnglePowerShoot);
        AutoUtil.startShooting();
    }

    public static void run() {
        AutoUtil.startShooting(powerShotsShooterSpeed);
        //drive.followTrajectory(TrajQuantumInterior.returnPowerShots(drive.getPoseEstimate()));
        opMode.sleep(500);
        // drive.followTrajectory(powerShotsTrajectory(drive.getPoseEstimate()));
        GyroPID.rotate(firstRotation, opMode.telemetry, opMode);
        opMode.sleep(100);
        AutoUtil.shoot(true, true);
        GyroPID.rotate(secondRotation, opMode.telemetry, opMode);
        AutoUtil.shoot(true, true);
        GyroPID.rotate(thirdRotation, opMode.telemetry, opMode);
        AutoUtil.shoot(true, true);
        // AutoUtil.wallPosition(wallState.INSIDE);
    }

}
