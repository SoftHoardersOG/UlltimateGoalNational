package org.firstinspires.ftc.teamcode.Utils.Logics;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Utils.Gamepads.OneTap;

public class TwoStateMotorWithDelay {

    private static final ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    private final OneTap diskNormalizerButton = new OneTap();
    private final ChangeState diskNormalizerStateLeft = new ChangeState();
    private final ChangeState diskNormalizerStateRight = new ChangeState();
    private final DelayedAction diskNormalizerAction = new DelayedAction(250, timer);
    private boolean started = false;
    private final int speed1;
    private final int speed2;

    public TwoStateMotorWithDelay(int speed1, int speed2) {
        this.speed1 = speed1;
        this.speed2 = speed2;
    }

    public void DiskNormalizer(boolean button) {
        boolean activate = diskNormalizerButton.onPress(button);
        if (activate) {
            started = true;
            ((DcMotorEx) Hardware.shooter_right).setVelocity(speed1);
            ((DcMotorEx) Hardware.shooter_left).setVelocity(speed1);
        }
        if (started && ((DcMotorEx) Hardware.shooter_right).getVelocity() <= speed1) {
            diskNormalizerAction.callAction();
            started = false;
        }
        boolean runActionReturn = diskNormalizerAction.runAction();
        if (runActionReturn) {
            ((DcMotorEx) Hardware.shooter_right).setVelocity(speed2);
            ((DcMotorEx) Hardware.shooter_left).setVelocity(speed2);
        }

    }
}
