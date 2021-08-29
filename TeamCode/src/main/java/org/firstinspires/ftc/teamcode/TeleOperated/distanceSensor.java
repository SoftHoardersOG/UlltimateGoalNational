package org.firstinspires.ftc.teamcode.TeleOperated;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Utils.Logics.DelayedAction;

public class distanceSensor {

    public static final double DISTANCE_MINI = 60f;
    private static final double delay = 100;
    public static boolean shouldIntake = true;
    private static final ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private static final DelayedAction delayedAction = new DelayedAction(delay, timer);
    //private static final DelayedAction delayedAction2 = new DelayedAction(delay, timer);

    public static void update() {
        if (Hardware.disk_distance.getDistance(DistanceUnit.MM) < DISTANCE_MINI) {
            delayedAction.callAction();
            if(delayedAction.runAction()){
                if (Hardware.disk_distance.getDistance(DistanceUnit.MM) < DISTANCE_MINI) {
                    shouldIntake = false;
                }
            }
        }else{
                shouldIntake = true;
            }
        }
    }




