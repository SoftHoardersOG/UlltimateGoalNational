package org.firstinspires.ftc.teamcode.HardwarePack;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil;
import org.openftc.easyopencv.OpenCvPipeline;

import static org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil.OpenCVSetup;
import static org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil.ResetEncoders;
import static org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil.RunToPosition;
import static org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil.directionChanging;
import static org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil.powerBehaviorChanging;

public class Hardware extends HardwareMapping {

    public static void init(HardwareMap hm, Telemetry telemetry) {
        HardwareUtil.setTelemetry(telemetry);
        telemetry.addLine("Initializing...");

        hardwareMapping(hm);
        telemetry.addLine("Hardware Mapping Done!");

        ResetEncoders(shooter_right, shooter_left, intake,grabber);
        RunToPosition(grabber);

        directionChanging(shooter_right, intake);
        directionChanging(intakeBooster_left);

        telemetry.addLine("Direction changing for DCMotors Done!");


        powerBehaviorChanging();
        telemetry.addLine("Power behavior Done!");

        telemetry.addLine("Initialization completed.");
        telemetry.update();

    }

    public static void init(HardwareMap hm, Telemetry telemetry, OpenCvPipeline process) {


        HardwareUtil.setTelemetry(telemetry);
        telemetry.addLine("Initializing...");


        hardwareMapping(hm);
        telemetry.addLine("Hardware Mapping Done!");

        try {
            OpenCVSetup(hm, process, telemetry, webcam);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        telemetry.addLine("Open CV setup Done!");
        telemetry.update();

        ResetEncoders(grabber, shooter_right, shooter_left, intake);
        RunToPosition(grabber);

        directionChanging(shooter_right, intake);
        directionChanging(intakeBooster_left);

        telemetry.addLine("Direction changing for DCMotors Done!");


        powerBehaviorChanging();
        telemetry.addLine("Power behavior Done!");

        telemetry.addLine("Initialization completed.");
        telemetry.update();

    }

}
