package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Quantum.InteriorLine;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.AutoScenes;
import org.firstinspires.ftc.teamcode.Autonomous.Initializations;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.DetectionCase;
import org.firstinspires.ftc.teamcode.Autonomous.PowerShots;
import org.firstinspires.ftc.teamcode.Autonomous.PowerShotsInteriorLine;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.DiskAmountDetection;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.armCase;
import org.firstinspires.ftc.teamcode.TeleOperated.grabberPosition;
import org.firstinspires.ftc.teamcode.TeleOperated.wobblePosition;

@Autonomous(name = "QuantumInteriorBLUE")
public class QuantumInteriorBLUE extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        TrajQuantumInterior.initSpecificTraj(ColorCase.BLUE);
        Initializations.AutoInit(this, armCase.LEFT, TrajQuantumInterior.getStartPose());
        waitForStart();

        sleep(4000);
        DiskAmountDetection.stopDetection();
        //DetectionCase.setAutoCase("A", this);
        AutoCase.setAutoScene(AutoScenes.QuantumInterior);

        DetectionCase.autoCase.releaseFirstWobble();
        sleep(5000);
        Wobble.SetGrabberPosition(grabberPosition.GRAB);
        Wobble.motorArmToPosition(wobblePosition.UP);
        AutoCase.ReturnToPowerShots();
        PowerShotsInteriorLine.initialization(Trajectories.getDrive(), this, ColorCase.BLUE);
        PowerShotsInteriorLine.run();
        AutoCase.Park();
    }
}
