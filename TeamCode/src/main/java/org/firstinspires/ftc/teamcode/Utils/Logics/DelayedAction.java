package org.firstinspires.ftc.teamcode.Utils.Logics;

import com.qualcomm.robotcore.util.ElapsedTime;

public class DelayedAction {
    private boolean actionCalled;
    private double callTime;
    private final double delay;
    private final ElapsedTime timer;

    public DelayedAction(double delay, ElapsedTime timer) {
        this.delay = delay;
        this.timer = timer;
    }

    public void callAction() {
        if (!actionCalled) {
            actionCalled = true;
            callTime = timer.milliseconds();
        }
    }

    public boolean runAction() {
        boolean result = actionCalled && ((timer.milliseconds() - callTime) >= delay);
        if (result)
            actionCalled = false;
        return result;
    }
}
