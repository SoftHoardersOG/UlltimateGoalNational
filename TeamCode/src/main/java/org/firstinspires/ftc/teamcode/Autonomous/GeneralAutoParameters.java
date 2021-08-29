package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;

@Config
public class GeneralAutoParameters {
    public static double intakeX = 20;
    public static double intakeY = 33.5;
    public static double intakeVel = 25; //todo it was 23
    public static double intakeAcc = 20;

    public static int wobble2ArmPosition = 500;
    public static int wobbleMidPosition = 600;
    public static int wobbleCollectPosition = 820;
    public static int wobbleArmSleep = 0;
    public static int sleepForGrabbing = 0;

    public static double wobblePos2X = 39;
    public static double wobblePos2Y = 39; //39
    public static double wobbleVel = 20;
    public static double wobbleAcc = 20;

    public static double shootSpeed = 1250;//1300
    public static double shootPoseX = 10.5;
    public static double shootPoseY = 35;
    public static double shootAngle = 180;

    public static int shootingSpeed = 1400;
    public static double intakeAngle = 0.5;
    public static double shooterAngleTower= 0.58; //0.68 //era 0.59, valoarea aste este pt REDintermediate1 TODO
}
