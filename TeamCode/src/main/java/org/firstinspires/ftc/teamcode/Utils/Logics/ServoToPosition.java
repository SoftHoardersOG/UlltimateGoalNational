package org.firstinspires.ftc.teamcode.Utils.Logics;

import org.firstinspires.ftc.teamcode.Utils.Gamepads.OneTap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServoToPosition {
    private boolean wasPressed = false;
    private final OneTap tap = new OneTap();
    private final List<ServoCommandGroup> commandList = new ArrayList<>();


    public ServoToPosition(ServoCommandGroup... servoCommandGroups) {
        commandList.addAll(Arrays.asList(servoCommandGroups));
    }

    public void ResetForSecond() {
        wasPressed = false;
    }

    public void ResetForFirst() {
        wasPressed = true;
    }

    public void modifyPosition(boolean button) {
        if (tap.onPress(button)) {
            if (wasPressed) {
                for (ServoCommandGroup command :
                        commandList) {
                    command.servo.setPosition(command.firstPosition);
                }
                wasPressed = false;
            } else {
                for (ServoCommandGroup command :
                        commandList) {
                    command.servo.setPosition(command.secondPosition);
                }
                wasPressed = true;
            }
        }
    }
}
