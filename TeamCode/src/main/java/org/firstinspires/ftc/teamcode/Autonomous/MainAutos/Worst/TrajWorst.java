package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Worst;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Autonomous.ConstantsAutonomous;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PositionCaseModifier;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;

public class TrajWorst extends Trajectories {
    //inch and rad
    public static Pose2d shootPose = new Pose2d(7, 52, Math.toRadians(190));
    public static Pose2d parkPose = new Pose2d(2, 56, Math.toRadians(180));
    public static ColorCase colorCase;

    public static void initSpecificTraj(ColorCase colorCase){
        TrajWorst.colorCase = colorCase;

        if (colorCase == ColorCase.RED) {
            setStartPose(new Pose2d(61.5, 57.87, Math.toRadians(180)), colorCase);
        } else {
            setStartPose(new Pose2d(61.5, 57.87 + ConstantsAutonomous.BLUEoffset, Math.toRadians(180)), colorCase);
        }

        //setStartPose(new Pose2d(61.5, 57.87,Math.toRadians(180)), colorCase);
    }

    public static Trajectory shootTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(shootPose, colorCase))
                .build();
    }

    public static Trajectory ParkTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(parkPose, colorCase))
                .build();
    }
}
