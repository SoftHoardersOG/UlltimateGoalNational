package com.example.trajectoryvisualizer;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;

public class  VisualizerC {
    public static void main(String[] args) {
        Pose2d startPose = new Pose2d(-61.5, -14.6, Math.toRadians(180));
        // Declare a MeepMeep instance
        // With a field size of 800 pixels
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
                            return drive.trajectorySequenceBuilder(new Pose2d(-61.5, -14.6, Math.toRadians(180)))
                                    .lineTo(new Vector2d(CmToInch.convert(5), CmToInch.convert(48))) //right powershot
                                    .waitSeconds(0.2)
                                    .lineTo(new Vector2d(CmToInch.convert(5), CmToInch.convert(48 - 20.5))) //middle powershot
                                    .waitSeconds(0.2)
                                    .lineTo(new Vector2d(CmToInch.convert(5), CmToInch.convert(48 - 20.5 * 2 + 0.5))) //left powershot
                                    .waitSeconds(0.2)

                                    .lineToSplineHeading(new Pose2d(CmToInch.convert(-140f), CmToInch.convert(100f), Math.toRadians(180f))) //wobble release
                                    .waitSeconds(0.2)

                                    .splineToConstantHeading(new Vector2d(-38, -20), Math.toRadians(80)) //return back

                                    .lineToConstantHeading(new Vector2d(-38, -37)) //second wobble collect
                                    .waitSeconds(0.2)

                                    .lineToLinearHeading(new Pose2d(-20, -37, Math.toRadians(200))) //disk collect

                                    .lineToSplineHeading(new Pose2d(-10.5, -34.5, Math.toRadians(180))) //shoot
                                    .waitSeconds(0.2)

                                    .splineToLinearHeading(new Pose2d(CmToInch.convert(-105f), CmToInch.convert(132f), Math.toRadians(270f)), Math.toRadians(160)) //second wobble release

//                                    .lineToConstantHeading(new Vector2d(-39, -40))
//                                    .lineToLinearHeading(new Pose2d(-20, -35, Math.toRadians(200)))

                                    //.strafeTo(new Vector2d(CmToInch.convert(0), CmToInch.convert(-12)))
                                    // .strafeLeft(100)
                                    .build();
                        }


                ).start();


    }
}
