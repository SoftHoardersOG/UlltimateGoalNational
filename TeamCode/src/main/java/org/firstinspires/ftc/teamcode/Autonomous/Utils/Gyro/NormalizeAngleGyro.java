package org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Main.Teleoperated;
import org.firstinspires.ftc.teamcode.RoadRunner.Functionalities.PoseStorage;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;

public class NormalizeAngleGyro { //Gyro

    private static final double NormalizeValue = Math.toDegrees(PoseStorage.currentPose.getHeading());

    public static double GetAngle(MyMecanumDrive drive) {
        // return (Normalize(Hardware.imu));
        return Math.toDegrees(drive.getPoseEstimate().getHeading());
    }

    public static double GetRawAngle(BNO055IMU imu) {
        return imu.getAngularOrientation().firstAngle;
    }

    public static double Normalize(BNO055IMU imu) {

        return GetRawAngle(imu) + NormalizeValue;
    }
}