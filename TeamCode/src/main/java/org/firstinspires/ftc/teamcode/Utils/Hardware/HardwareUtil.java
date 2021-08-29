package org.firstinspires.ftc.teamcode.Utils.Hardware;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.concurrent.TimeUnit;

public class HardwareUtil {
    private static Telemetry telemetry;

    public static void setTelemetry(Telemetry telemetry) {
        HardwareUtil.telemetry = telemetry;
    }

    private HardwareUtil() {
    }

    public static DcMotor getDC(String name, HardwareMap hm) {
        try {
            return hm.get(DcMotor.class, name);
        } catch (Exception exception) {
            telemetry.addLine(name + " is null");
            return null;
        }
    }

    public static BNO055IMU getIMU(String name, HardwareMap hm) {
        try {
            return hm.get(BNO055IMU.class, name);
        } catch (Exception exception) {
            telemetry.addLine(name + " is null");
            return null;
        }
    }

    public static Servo getServo(String name, HardwareMap hm) {
        try {
            return hm.get(Servo.class, name);
        } catch (Exception exception) {
            telemetry.addLine(name + " is null");
            return null;
        }
    }

    public static CRServo getCRServo(String name, HardwareMap hm) {
        try {
            return hm.get(CRServo.class, name);
        } catch (Exception exception) {
            telemetry.addLine(name + " is null");
            return null;
        }
    }

    public static DistanceSensor getDistanceSensor(String name, HardwareMap hm) {
        try {
            return hm.get(DistanceSensor.class, name);
        } catch (Exception exception) {
            telemetry.addLine(name + " is null");
            return null;
        }
    }

    public static WebcamName getWebcam(String name, HardwareMap hm) {
        try {
            return hm.get(WebcamName.class, name);
        } catch (Exception exception) {
            telemetry.addLine(name + " is null");
            return null;
        }
    }

    public static DigitalChannel getDigitalChannel(String name, HardwareMap hm) {
        try {
            return hm.get(DigitalChannel.class, name);
        } catch (Exception exception) {
            telemetry.addLine(name + " is null");
            return null;
        }
    }

    public static void ResetEncoders(DcMotor... dcMotors) {
        for (DcMotor dcMotor : dcMotors) {
            if (dcMotor != null) {
                dcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                dcMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
        }
    }

    public static void RunWithoutEncoders(DcMotor... dcMotors) {
        for (DcMotor dcMotor : dcMotors) {
            if (dcMotor != null) {
                dcMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
        }
    }

    public static void RunToPosition(DcMotor... dcMotors) {
        for (DcMotor dcMotor : dcMotors) {
            if (dcMotor != null) {
                dcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                dcMotor.setTargetPosition(0);
                dcMotor.setPower(0);
                dcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                dcMotor.setTargetPosition(0);
            }
        }
    }

    public static void powerBehaviorChanging(DcMotor... dcMotors) {
        for (DcMotor dcMotor : dcMotors) {
            if (dcMotor != null)
                dcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    public static void directionChanging(DcMotor... dcMotors) {
        for (DcMotor dcMotor : dcMotors) {
            if (dcMotor != null)
                dcMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }

    public static void directionChanging(Servo... servos) {
        for (Servo servo : servos) {
            if (servo != null)
                servo.setDirection(Servo.Direction.REVERSE);
        }
    }

    public static void directionChanging(CRServo... crServos) {
        for (CRServo servo : crServos) {
            if (servo != null)
                servo.setDirection(CRServo.Direction.REVERSE);
        }
    }

    public static void OpenCVSetup(HardwareMap hm, OpenCvPipeline process, Telemetry telemetry, WebcamName webcam) throws InterruptedException {
        if (webcam != null && process != null) {


            int cameraMonitorID = hm.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hm.appContext.getPackageName());
            telemetry.addLine("Monitor ID DONE!");
            telemetry.update();

            Hardware.cvCamera = OpenCvCameraFactory.getInstance().createWebcam(webcam, cameraMonitorID);
            FtcDashboard.getInstance().startCameraStream(Hardware.cvCamera,60);

            telemetry.addLine("CVCam initialisation DONE!");
            telemetry.update();

            for (int i = 1; i <= 10; i++) {
                Hardware.cvCamera.openCameraDevice();
                TimeUnit.MILLISECONDS.sleep(20);
            }

            telemetry.addLine("Camera opened DONE!");
            telemetry.update();

            try {
                Hardware.cvCamera.setPipeline(process);
                telemetry.addLine("Pipeline seted DONE!");
                telemetry.update();

                Hardware.cvCamera.startStreaming(176, 144, OpenCvCameraRotation.SIDEWAYS_LEFT);
                telemetry.addLine("Stream started DONE!");
                telemetry.update();
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public static void InitializeIMU(BNO055IMU imu) {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        try {
            imu.initialize(parameters);
        } catch (Exception exception) {
            telemetry.addLine("imu" + " is null");

        }
    }

}
