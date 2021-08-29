package org.firstinspires.ftc.teamcode.Control;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;

public class Instruction {
    public static void Commands(Telemetry telemetry, boolean update) {
        telemetry.addLine("                GAMEPAD 1");
        telemetry.addLine("***Joysticks for driving");
        telemetry.addLine("       Shooting");
        telemetry.addLine("***Press left bumper to activate/deactivate the shooting motors");
        telemetry.addLine("***Press a to shoot one ring");
        telemetry.addLine("***Hold b for continuous mode shooting");
        telemetry.addLine("***Press x to go to \"shoot\" or \"free\" position ");

        telemetry.addLine("                GAMEPAD 2");
        telemetry.addLine("       Gathering");
        telemetry.addLine("---Press right_trigger to control the gathering speed");
        telemetry.addLine("       Angle control");
        telemetry.addLine("***Press x for incrementing the shooter angle with " + ChangeShootingAngle.getIncrementValue());
        telemetry.addLine("***Press y for incrementing the shooter angle with -" + ChangeShootingAngle.getIncrementValue());
        telemetry.addLine("***The current abs position is: " + ChangeShootingAngle.getAbsPosition());
        if (update) {
            telemetry.update();
        }

    }
}
