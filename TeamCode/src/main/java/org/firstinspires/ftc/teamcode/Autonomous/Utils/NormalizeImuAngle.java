package org.firstinspires.ftc.teamcode.Autonomous.Utils;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.Functionalities.PoseStorage;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;

public class NormalizeImuAngle {
    private static MyMecanumDrive drive;

    public static void setDrive(MyMecanumDrive drive) {
        NormalizeImuAngle.drive = drive;
    }

    public static double convertAuto(double angle) {
        return (angle+180);
    }

    public static double convert(double angle) {

        return (angle + PoseStorage.imuAndWobble.getX() >360?angle+PoseStorage.imuAndWobble.getX()-360 :angle+ PoseStorage.imuAndWobble.getX());
    }

    public static double heading(double angle) {
        return Math.toRadians(angle);// - convert(Hardware.imu.getAngularOrientation().firstAngle) + Math.toDegrees(drive.getPoseEstimate().getHeading()) );
    }

    public static double heading(double angle, boolean execute) {
        return Math.toRadians(angle - convert(Hardware.imu.getAngularOrientation().firstAngle) + Math.toDegrees(drive.getPoseEstimate().getHeading()));
    }

}
