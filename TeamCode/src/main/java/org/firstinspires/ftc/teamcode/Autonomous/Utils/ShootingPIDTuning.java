package org.firstinspires.ftc.teamcode.Autonomous.Utils;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;

@Disabled
//@Config
@TeleOp(name = "ShootingPIDTuning")
public class ShootingPIDTuning extends LinearOpMode {
    public static double shootingAngle = 0.4;
    public static double P = 50f;
    public static double I;
    public static double D;
    public static double F = 13.2;
    public static double targetVelocity = 2000;

    public boolean areEqual(PIDFCoefficients a, PIDFCoefficients b) {
        return a.p == b.p && a.i == b.i && a.d == b.d && a.f == b.f;
    }

    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap, telemetry);

        DcMotorEx shooter_leftEx = ((DcMotorEx) Hardware.shooter_left);
        DcMotorEx shooter_rightEx = ((DcMotorEx) Hardware.shooter_right);

        FtcDashboard ftcDashboard = FtcDashboard.getInstance();
        Telemetry dashboardTelemetry = ftcDashboard.getTelemetry();
        ChangeShootingAngle.AngleControl(shootingAngle);

        Hardware.shooter_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Hardware.shooter_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        shooter_leftEx.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(P, I, D, F));
        shooter_rightEx.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(P, I, D, F));

        Hardware.shooter_left.setPower(1);
        Hardware.shooter_right.setPower(1);

        waitForStart();


        while (opModeIsActive() && !isStopRequested()) {

            PIDFCoefficients pidfCoefficients = shooter_leftEx.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
            if (!areEqual(pidfCoefficients, new PIDFCoefficients(P, I, D, F))) {
                shooter_leftEx.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(P, I, D, F));
                shooter_rightEx.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(P, I, D, F));
            }

            shooter_leftEx.setVelocity(targetVelocity);
            shooter_rightEx.setVelocity(targetVelocity);

            dashboardTelemetry.addData("targetVelocity ", targetVelocity);
            dashboardTelemetry.addData("left ", shooter_leftEx.getVelocity());
            dashboardTelemetry.addData("right ", shooter_rightEx.getVelocity());

            dashboardTelemetry.update();
        }

    }
}
