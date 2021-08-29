package org.firstinspires.ftc.teamcode.Autonomous.Utils;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public class PositionCaseModifier {
    public static Pose2d correct(Pose2d pose2d, ColorCase colorCase){
        switch (colorCase){
            case RED:
                return pose2d;
            case BLUE:
                return new Pose2d(pose2d.getX(),-pose2d.getY(),2*Math.PI-pose2d.getHeading());
            default:
                return  null;
        }

    }
}
