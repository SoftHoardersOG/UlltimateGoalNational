package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Utils.Gamepads.OneTap;
import org.firstinspires.ftc.teamcode.Utils.Logics.ChangeState;
@Disabled
@TeleOp(name = "TrowTest", group = "Tests")
public class AruncareTest extends LinearOpMode {
    private static final double push = 0.05;
    private static final double free = 0.16;
    private static final double shootSpeed = 0.9;
    private static final double boosterSpeed = 1;
    private static final int shootTime = 40;
    private static final int returnTime = 70;
    OneTap idler = new OneTap();
    OneTap motor = new OneTap();
    ChangeState shooter = new ChangeState();

    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap, telemetry);

        waitForStart();
        Hardware.shooter_idler.setPosition(free);

        boolean motorState = false;
        while (opModeIsActive() && !isStopRequested()) {

            shooter.changeMotorState(motor.onPress(gamepad1.left_bumper), shootSpeed, Hardware.shooter_left, Hardware.shooter_right);

            if (idler.onPress(gamepad1.a)) {
                Hardware.shooter_idler.setPosition(push);
                sleep(shootTime);
                Hardware.shooter_idler.setPosition(free);
            }

            if (gamepad1.b) {
                Hardware.shooter_idler.setPosition(push);
                sleep(shootTime);
                Hardware.shooter_idler.setPosition(free);
                sleep(returnTime);
            }
            if (gamepad1.x) {
                Hardware.shooter_idler.setPosition(push);
            }
            if (gamepad1.y) {
                Hardware.shooter_idler.setPosition(free);
            }
        }
    }
}
