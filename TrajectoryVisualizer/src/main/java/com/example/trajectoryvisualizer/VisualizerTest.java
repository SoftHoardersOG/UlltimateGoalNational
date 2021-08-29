package com.example.trajectoryvisualizer;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;

public class VisualizerTest {
    public static void main(String[] args) {
        Pose2d startPose = new Pose2d(-61.5, -14.6, Math.toRadians(180));
        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        Pose2d pickSecondWobble = new Pose2d(-30, -47, Math.toRadians(250));

        MeepMeep mm = new MeepMeep(800)
                // Set field image
                .setBackground(MeepMeep.Background.FIELD_ULTIMATE_GOAL_DARK)
                // Set theme
                .setTheme(new ColorSchemeRedDark())
                // Background opacity from 0-1
                .setBackgroundAlpha(1f)

                // Set constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(30, 40, 10, Math.toRadians(348.4202808227979), 14.88)
                .setStartPose(new Pose2d(-61.5, -14.6, Math.toRadians(180)))
                .followTrajectorySequence(drive ->
                        {
                            return drive.trajectorySequenceBuilder(new Pose2d(53, -44, Math.toRadians(250)))
                                    .lineToLinearHeading(pickSecondWobble)
                                    //Error: User code threw an uncaught exception: PathContinuityViolationException
                                    //.lineToSplineHeading(new Pose2d(10, 36, java.lang.Math.toRadians(180)))
                                    //.splineToConstantHeading(new Vector2d(38, 21), java.lang.Math.toRadians(20))

                                    //.waitSeconds(0.2)


//                                    .lineToConstantHeading(new Vector2d(-39, -40))
//                                    .lineToLinearHeading(new Pose2d(-20, -35, Math.toRadians(200)))

                                    //.strafeTo(new Vector2d(CmToInch.convert(0), CmToInch.convert(-12)))
                                    // .strafeLeft(100)
                                    .build();
                        }


                ).start();


    }
}
