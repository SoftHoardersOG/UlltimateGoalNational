package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Quantum.ExteriorLine;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.AutoScenes;
import org.firstinspires.ftc.teamcode.Autonomous.Initializations;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.DetectionCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.DiskAmountDetection;
import org.firstinspires.ftc.teamcode.TeleOperated.armCase;

@Autonomous(name = "QuantumExteriorBLUE")
public class QuantumExteriorBLUE extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        TrajQuantumExterior.initSpecificTraj(ColorCase.BLUE);
        Initializations.AutoInit(this, armCase.LEFT, TrajQuantumExterior.getStartPose());
        waitForStart();

        DiskAmountDetection.stopDetection();
        DetectionCase.setAutoCase("C", this);
        AutoCase.setAutoScene(AutoScenes.QuantumExterior);

        DetectionCase.autoCase.Shoot();
        //if (DetectionCase.diskAmount != 0) {
        if(true){  //B si C
            AutoCase.Intake();
            sleep(400);
            DetectionCase.autoCase.ShootSecondHighGoal();
        }

        DetectionCase.autoCase.releaseFirstWobble();

        if(false)//CAZUL A DOAR
        {
            AutoCase.ParkAQuantum();
            sleep(13000);
        }

        AutoCase.Park();


    }
}
