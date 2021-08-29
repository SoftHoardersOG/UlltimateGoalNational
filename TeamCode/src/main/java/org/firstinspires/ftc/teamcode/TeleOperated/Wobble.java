package org.firstinspires.ftc.teamcode.TeleOperated;

import android.util.Pair;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.ConstantsTeleoperated;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.Functionalities.PoseStorage;
import org.firstinspires.ftc.teamcode.Utils.Gamepads.OneTap;
import org.firstinspires.ftc.teamcode.Utils.Logics.ServoCommandGroup;
import org.firstinspires.ftc.teamcode.Utils.Logics.ServoToPosition;


public class Wobble {
    private static int lastLeftGrabberPosition = 0;
    private static int lastRightGrabberPosition = 0;
    public static wobblePosition currentPose = wobblePosition.UP;

    //TODO: // RED --> Right
    // BLUE --> Left


    private static final OneTap sumUp = new OneTap();
    private static final OneTap sumDown = new OneTap();

    private static final OneTap blockerTap = new OneTap();
    private static final ServoCommandGroup rightServo = new ServoCommandGroup(Hardware.servo_wobble_right, ConstantsTeleoperated.rightGrabPosition, ConstantsTeleoperated.rightFreePosition);
    private static final ServoCommandGroup leftServo = new ServoCommandGroup(Hardware.servo_wobble_left, ConstantsTeleoperated.leftGrabPosition, ConstantsTeleoperated.leftFreePosition);
    private static final ServoToPosition grabberServoLeft = new ServoToPosition(leftServo);
    private static final ServoToPosition grabberServoRight = new ServoToPosition(rightServo);

    private static armCase grabberCase = armCase.RIGHT;

    public static armCase getGrabberCase() {
        return grabberCase;
    }

    public static void setGrabberCase(armCase grabberCase) {
        Wobble.grabberCase = grabberCase;
        selectArm();
    }

    public static void changeGrabberCase() {
        if (grabberCase == armCase.LEFT) {
            setGrabberCase(armCase.RIGHT);
        } else {
            setGrabberCase(armCase.LEFT);
        }
    }

    public static void selectArm() {

        if (grabberCase == armCase.RIGHT) {
            Hardware.stopper_right.setPosition(ConstantsTeleoperated.freeRightBlocker);
            Hardware.stopper_left.setPosition(ConstantsTeleoperated.grabLeftBlocker);
        } else {
            Hardware.stopper_right.setPosition(ConstantsTeleoperated.grabRightBlocker);
            Hardware.stopper_left.setPosition(ConstantsTeleoperated.freeLeftBlocker);
        }
    }

    public static void SetSArmTargetPosition(int position) {
        int targetPosition = Hardware.grabber.getTargetPosition();
        if (grabberCase == armCase.LEFT) {
            Hardware.grabber.setTargetPosition(targetPosition - (position + lastLeftGrabberPosition));
            lastLeftGrabberPosition = -position;
        } else {
            Hardware.grabber.setTargetPosition(targetPosition + (position - lastRightGrabberPosition));
            lastRightGrabberPosition = position;
        }
    }

    public static void SetGrabberPosition(grabberPosition position) {
        Pair<grabberPosition, armCase> grabberPositionCasePair = new Pair<>(position, grabberCase);
        if (new Pair<>(grabberPosition.FREE, armCase.LEFT).equals(grabberPositionCasePair)) {
            Hardware.servo_wobble_left.setPosition(ConstantsTeleoperated.leftFreePosition);
        } else if (new Pair<>(grabberPosition.FREE, armCase.RIGHT).equals(grabberPositionCasePair)) {
            Hardware.servo_wobble_right.setPosition(ConstantsTeleoperated.rightFreePosition);
        } else if (new Pair<>(grabberPosition.GRAB, armCase.LEFT).equals(grabberPositionCasePair)) {
            Hardware.servo_wobble_left.setPosition(ConstantsTeleoperated.leftGrabPosition);
        } else if (new Pair<>(grabberPosition.GRAB, armCase.RIGHT).equals(grabberPositionCasePair)) {
            Hardware.servo_wobble_right.setPosition(ConstantsTeleoperated.rightGrabPosition);
        }
    }


    public static void wobbleRelease() {
        SetGrabberPosition(grabberPosition.FREE);
    }

    public static void servoPositions(boolean button) {
        if (grabberCase == armCase.LEFT) {
            grabberServoLeft.modifyPosition(button);
        } else {
            grabberServoRight.modifyPosition(button);
        }
    }

    public static void changeGrabberCaseByButton(boolean button) {
        if (blockerTap.onPress(button)) {
            changeGrabberCase();
        }
    }

    public static void motorArm(double up, double down) {
        if (down <= 0.05) Hardware.grabber.setPower(up / 2);
        if (up <= 0.05) Hardware.grabber.setPower(-down / 2);
    }

    public static void initialization() {

        selectArm();

        ConstantsTeleoperated.wobbleUpPose -= PoseStorage.imuAndWobble.getY();
        ConstantsTeleoperated.wobbleDownPose -= PoseStorage.imuAndWobble.getY();
        ConstantsTeleoperated.wobbleMidPose -= PoseStorage.imuAndWobble.getY();

        Hardware.grabber.setTargetPosition(ConstantsTeleoperated.wobbleUpPose);
        Hardware.grabber.setPower(1);
        SetGrabberPosition(grabberPosition.GRAB);

        Hardware.servo_wobble_left.setPosition(ConstantsTeleoperated.leftGrabPosition - 0.1);
        Hardware.servo_wobble_right.setPosition(ConstantsTeleoperated.rightGrabPosition + 0.1);
    }


    public static void initializationAuto(armCase armCase) {

        //TODO: very weird behaviour
        setGrabberCase(armCase);
        Hardware.grabber.setTargetPosition(ConstantsTeleoperated.wobbleUpPose);
        Hardware.grabber.setPower(1);
        SetGrabberPosition(grabberPosition.GRAB);

    }


    public static void close() {
        Hardware.servo_wobble_right.setPosition(ConstantsTeleoperated.rightGrabPosition - 0.05);
    }


    public static void motorArmToPosition(boolean button, wobblePosition position) {
        if (button) {
            currentPose = position;
        }
    }

    public static void motorArmToPosition(wobblePosition position) {
        currentPose = position;
        positionUpdate();
    }

    public static void motorArmToPosition(boolean button, int position) {
        if (button) {
            Hardware.grabber.setTargetPosition(position);
        }

    }

    private static void correctPostion(Gamepad gamepad) {
        if (sumUp.onPress(gamepad.right_trigger > 0.2)) {
            ConstantsTeleoperated.wobbleUpPose += ConstantsTeleoperated.correctionIncrementValue;
            ConstantsTeleoperated.wobbleDownPose += ConstantsTeleoperated.correctionIncrementValue;
            ConstantsTeleoperated.wobbleMidPose += ConstantsTeleoperated.correctionIncrementValue;
        }
        if (sumDown.onPress(gamepad.left_trigger > 0.2)) {
            ConstantsTeleoperated.wobbleUpPose -= ConstantsTeleoperated.correctionIncrementValue;
            ConstantsTeleoperated.wobbleDownPose -= ConstantsTeleoperated.correctionIncrementValue;
            ConstantsTeleoperated.wobbleMidPose -= ConstantsTeleoperated.correctionIncrementValue;
        }
    }

    private static void positionUpdate() {
        if (currentPose == wobblePosition.UP) {
            SetSArmTargetPosition(ConstantsTeleoperated.wobbleUpPose);
        } else if (currentPose == wobblePosition.MID) {
            SetSArmTargetPosition(ConstantsTeleoperated.wobbleMidPose);
        } else {
            SetSArmTargetPosition(ConstantsTeleoperated.wobbleDownPose);
        }
    }

    public static void wobbleArmControl(Gamepad gamepad) {
        motorArmToPosition(gamepad.dpad_up, wobblePosition.UP);
        motorArmToPosition(gamepad.dpad_right, wobblePosition.MID);
        motorArmToPosition(gamepad.dpad_down, wobblePosition.DOWN);
        changeGrabberCaseByButton(gamepad.x);
        correctPostion(gamepad);
        if (gamepad.dpad_down || gamepad.dpad_right || gamepad.dpad_up) {
            positionUpdate();
        }
    }

    public static void wobbleGrabberControl(Gamepad gamepad) {
        servoPositions(gamepad.x);
    }

}
