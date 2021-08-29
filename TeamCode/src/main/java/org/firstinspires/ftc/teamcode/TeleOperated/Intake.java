package org.firstinspires.ftc.teamcode.TeleOperated;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Utils.Gamepads.OneTap;
import org.firstinspires.ftc.teamcode.Utils.Logics.ChangeState;


public class Intake {


    private static final OneTap intakeButton = new OneTap();
    private static final OneTap outtakeButton = new OneTap();
    private static final OneTap stopWhenFull = new OneTap();

    private static final ChangeState intakeState = new ChangeState();
    private static final ChangeState outtakeState = new ChangeState();
    //public static final ChangeState diskNormalizer = new ChangeState();

    public static void IntakeOneSpeed(Gamepad gamepad) {

        oneSpeedIntake(gamepad.right_bumper);

    }

    public static void oneSpeedIntake(boolean button) {
        boolean activate = intakeButton.onPress(button);
        if (distanceSensor.shouldIntake) {
            intakeState.changeMotorState(activate, 1, Hardware.intake);
            intakeState.changeServoState(activate, 1, Hardware.shooter_booster, Hardware.intakeBooster_right, Hardware.intakeBooster_left);
            intakeState.changeMotorStateSpeed(activate, -500, Hardware.shooter_left, Hardware.shooter_right);
        }
        stopIntake();
    }

    public static void oneSpeedOutTake(boolean button) {
        boolean activate = outtakeButton.onPress(button);
        outtakeState.changeMotorState(activate, -1, Hardware.intake);
        outtakeState.changeServoState(activate, -1, Hardware.shooter_booster, Hardware.intakeBooster_right, Hardware.intakeBooster_left );
    }

    public static void stopIntake() {
        boolean stop = stopWhenFull.onPress(!distanceSensor.shouldIntake);
        if (stop) {
            Hardware.intake.setPower(0);
            Hardware.intakeBooster_left.setPower(0);
            Hardware.intakeBooster_right.setPower(0);
            Hardware.shooter_booster.setPower(0);
            ((DcMotorEx) (Hardware.shooter_left)).setVelocity(Shooter.shootSpeed);
            ((DcMotorEx) (Hardware.shooter_right)).setVelocity(Shooter.shootSpeed);
           // Wall.Vertical();
        }

    }

    public static void OutTakeOneSpeed(Gamepad gamepad) {
        if (!ChangeState.getIsMotorRunning(Hardware.shooter_left)) {
            oneSpeedOutTake(gamepad.right_bumper);
        }
    }

}
