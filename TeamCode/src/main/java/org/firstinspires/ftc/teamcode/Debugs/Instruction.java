package org.firstinspires.ftc.teamcode.Debugs;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;

public class Instruction {
    public static void Commands(Telemetry telemetry, boolean update) {
        telemetry.addLine("\n\n**********GAMEPAD1 --> Mihnea");
        telemetry.addLine("----Movement----");
        telemetry.addLine("             All sticks - driving");
        telemetry.addLine("             Hold right_trigger, then use the sticks to drive slower");
        telemetry.addLine("----Wobble----");
        telemetry.addLine("             x -- Release/Grab Wobble");
        telemetry.addLine("----Shoot/Intake----");
        telemetry.addLine("             left_bumper -- Shooter Motors");
        telemetry.addLine("             right_bumper -- Intake Motors");
        telemetry.addLine("             dpad_right -- Shoot Position RoadRUNNER \n\n");
        telemetry.addLine("             dpad_up -- Wobble Position RoadRUNNER \n\n");


        telemetry.addLine("**********GAMEPAD2 --> Dominic");
        telemetry.addLine("----Wobble----");
        telemetry.addLine("             right_trigger -- Wobble correction UP");
        telemetry.addLine("             left_trigger -- Wobble correction DOWN");
        telemetry.addLine("             dpad_up -- Wobble UP position");
        telemetry.addLine("             dpad_right -- Wobble MIDDLE position");
        telemetry.addLine("             dpad_down -- Wobble DOWN position");
        telemetry.addLine("----Wall----");
        telemetry.addLine("             y -- Wall fully Inside");
        telemetry.addLine("             x -- Wall left control");
        telemetry.addLine("             b -- Wall right control");
        telemetry.addLine("----Shoot/Intake----");
        telemetry.addLine("             right_bumper -- Outtake");
        telemetry.addLine("             a -- Shooter's SHOOT position");
        if (update) {
            telemetry.update();
        }

    }
}
