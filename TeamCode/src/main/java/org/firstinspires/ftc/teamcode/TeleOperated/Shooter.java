package org.firstinspires.ftc.teamcode.TeleOperated;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.ConstantsTeleoperated;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Utils.Devices;
import org.firstinspires.ftc.teamcode.Utils.Gamepads.OneTap;
import org.firstinspires.ftc.teamcode.Utils.Logics.ChangeState;
import org.firstinspires.ftc.teamcode.Utils.Logics.DelayedAction;
import org.firstinspires.ftc.teamcode.Utils.Logics.ServoCommandGroup;
import org.firstinspires.ftc.teamcode.Utils.Logics.ServoToPosition;

public class Shooter {
    public static final double push = ConstantsTeleoperated.idlerShootPose;
    public static final double free = ConstantsTeleoperated.idlerFreePose;
    public static final int shootSpeed = ConstantsTeleoperated.shootingSpeed;
    private static final int shootTime = ConstantsTeleoperated.idlerShootTime;
    private static final int returnTime = ConstantsTeleoperated.idlerReturnTme;
    private static final OneTap idlerSequential = new OneTap();
    private static final OneTap motor = new OneTap();
    private static final OneTap idlerContinuous = new OneTap();
    private static final ServoToPosition singleShooting = new ServoToPosition(new ServoCommandGroup(Hardware.shooter_idler, free, push));
    public static final ChangeState shooter = new ChangeState();
    private static final ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private static final DelayedAction returnServo = new DelayedAction(shootTime, timer);
    private static final DelayedAction shootServoContinuous = new DelayedAction(returnTime, timer);
    private static final DelayedAction returnServoContinuous = new DelayedAction(shootTime, timer);

    public static ElapsedTime getTimer() {
        return timer;
    }

    public static void shooterInitialization(LinearOpMode opMode) {
        ((DcMotorEx)(Hardware.shooter_left)).setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,AutoUtil.shooterPid);
        Devices.setServoPosition(Hardware.shooter_idler, "shooter_idler", free);
        AutoUtil.setOpMode(opMode);
    }


    public static void SequentialShooting(boolean buttonForSequentialShooting) {

        if (idlerSequential.onPress(buttonForSequentialShooting)) {
            Devices.setServoPosition(Hardware.shooter_idler, "shooter_idler", push);
            returnServo.callAction();
        }
        if (returnServo.runAction()) {
            Devices.setServoPosition(Hardware.shooter_idler, "shooter_idler", free);
        }
    }

    public static void ShootingMotors(boolean buttonForStartingTheShooter) {
        boolean start = motor.onPress(buttonForStartingTheShooter);
        shooter.changeMotorStateSpeed(start, shootSpeed, Hardware.shooter_left, Hardware.shooter_right);
    }


    public static void ShooterControl(Gamepad gamepad) {
        if (!ChangeState.getIsMotorRunning(Hardware.intake)) {
            ShootingMotors(gamepad.left_bumper);
        }
        SequentialShooting(gamepad.a);
        if(idlerContinuous.onPress(gamepad.b)){
            AutoUtil.shoot3Disks();
        }
    }


}
