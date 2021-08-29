package org.firstinspires.ftc.teamcode.HardwarePack;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;

public class HardwareDeclarations {
    public static DcMotor grabber;
    public static DcMotor shooter_left, shooter_right;
    public static DcMotor intake;
    public static Servo angle_control_left_s, angle_control_right_s;
    public static Servo shooter_idler;
    public static Servo servo_wobble_left, servo_wobble_right;
    public static Servo stopper_right,stopper_left;
    public static CRServo shooter_booster;
    public static WebcamName webcam;
    public static BNO055IMU imu;
    public static BNO055IMU imu1;
    public static int cameraMonitorViewId;
    public static DistanceSensor disk_distance;
    public static OpenCvCamera cvCamera;
    public static CRServo intakeBooster_left,intakeBooster_right;
}

