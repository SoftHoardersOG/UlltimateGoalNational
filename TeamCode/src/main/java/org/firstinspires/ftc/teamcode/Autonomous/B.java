package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Full.TrajFull;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.One.TrajIntermOne;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.Two.TrajIntermTwo;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Quantum.ExteriorLine.TrajQuantumExterior;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Quantum.InteriorLine.TrajQuantumInterior;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Worst.TrajWorst;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.wallState;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.grabberPosition;
import org.firstinspires.ftc.teamcode.TeleOperated.wobblePosition;

import static org.firstinspires.ftc.teamcode.Autonomous.Initializations.drive;

public class B extends AutoCase {

//    public static double firstWobbleX = -120f;
//    public static double firstWobbleY = 60f;
//    public static double firstWobbleHeading = 200f;
//
//    public static double secondWobbleX = -70f;
//    public static double secondWobbleY = 75f;
//    public static double secondWobbleHeading = 300f;

    private final LinearOpMode opMode;

    @Override
    public void Shoot(){
        switch (autoScene){
            case INTERMEDIATE1:
                ChangeShootingAngle.AngleControl(ConstantsAutonomous.intermediate1ShooterAngle);
                AutoUtil.startShooting(ConstantsAutonomous.intermediate1ShootingSpeed);
                drive.followTrajectory(TrajIntermOne.shootTrajectory(drive.getPoseEstimate()));
                AutoUtil.shoot3Disks();
                //AutoUtil.wallPosition(wallState.INSIDE);
                break;
            case FULL:
                ChangeShootingAngle.AngleControl(ConstantsAutonomous.fullShooterAngle);
                AutoUtil.startShooting(ConstantsAutonomous.fullShootingSpeed);
                drive.followTrajectory(TrajFull.ShootTrajectory(drive.getPoseEstimate()));
                //AutoUtil.wallPosition(wallState.VERTICAL);
                AutoUtil.shoot(true,true);
                //AutoUtil.wallPosition(wallState.INSIDE);
                break;
            case INTERMEDIATE2:
                ChangeShootingAngle.AngleControl(ConstantsAutonomous.intermediate2ShooterAngle);
                AutoUtil.startShooting(ConstantsAutonomous.intermediate2ShootingSpeed);
                drive.followTrajectory(TrajIntermTwo.ShootTrajectory(drive.getPoseEstimate()));
                //AutoUtil.wallPosition(wallState.VERTICAL);
                AutoUtil.shoot(true,true);
                //AutoUtil.wallPosition(wallState.INSIDE);
                break;
            case WORST:
                ChangeShootingAngle.AngleControl(ConstantsAutonomous.worstShooterAngle);
                AutoUtil.startShooting(ConstantsAutonomous.worstShootingSpeed);
                drive.followTrajectory(TrajWorst.shootTrajectory(drive.getPoseEstimate()));
                AutoUtil.shoot3Disks();
              //  AutoUtil.wallPosition(wallState.INSIDE);
                break;
            case QuantumInterior:
//                ChangeShootingAngle.AngleControl(ConstantsAutonomous.interiorShooterAngle);
//                AutoUtil.startShooting(ConstantsAutonomous.interiorShootingSpeed);
                //drive.followTrajectory(TrajQuantumInterior.shootPose(drive.getPoseEstimate()));
                break;
            case QuantumExterior:
                ChangeShootingAngle.AngleControl(ConstantsAutonomous.exteriorShooterAngleB);
                AutoUtil.startShooting(ConstantsAutonomous.exteriorShootingSpeedB);
                drive.followTrajectory(TrajQuantumExterior.ShootTrajectory(drive.getPoseEstimate()));
                AutoUtil.shoot3Disks();
                break;
        }


    }

    @Override
    public void releaseFirstWobble() {
        switch (autoScene){
            case FULL:
                drive.followTrajectory(TrajFull.releaseBTrajectory(drive.getPoseEstimate()));
                break;
            case INTERMEDIATE1:
//                Wobble.motorArmToPosition(true, wobblePosition.MID);
                drive.followTrajectory(TrajIntermOne.releaseBTrajectory(drive.getPoseEstimate()));
                //TODO inspect here Wobble.motorArmToPosition(true, wobblePosition.MID);
                break;

            case INTERMEDIATE2:
                drive.followTrajectory(TrajIntermTwo.releaseBTrajectory(drive.getPoseEstimate()));
                //TODO inspect here Wobble.motorArmToPosition(true, wobblePosition.MID);
                break;
                //opMode.sleep(wobbleArmSleep);
                //Wobble.setGrabberPosition(grabberposition.free\grabpos.grab);
                //opMode.sleep(wobbleArmSleep);
            case WORST:
                break;
            case QuantumExterior:
                drive.followTrajectory(TrajQuantumExterior.releaseBTrajectory(drive.getPoseEstimate()));
                //opMode.sleep(wobbleArmSleep);
                break;
            case QuantumInterior:
                drive.followTrajectory(TrajQuantumInterior.goForwardB(drive.getPoseEstimate()));
                drive.followTrajectory(TrajQuantumInterior.releaseBTrajectory(drive.getPoseEstimate()));
                drive.followTrajectory(TrajQuantumInterior.goForwardB(drive.getPoseEstimate()));
                break;
        }
//        drive.followTrajectory(Trajectories.firstWobbleReleaseB(drive.getPoseEstimate()));
//        Wobble.wobbleRelease();
//        opMode.sleep(wobbleArmSleep);
    }

    @Override
    public void releaseSecondWobble() {

        switch (autoScene){
            case FULL:
                drive.followTrajectory(TrajFull.secondWobbleTrajB(drive.getPoseEstimate()));
                Wobble.motorArmToPosition(wobblePosition.DOWN);
                opMode.sleep(500);
                Wobble.wobbleRelease();
                opMode.sleep(800);
                Wobble.motorArmToPosition(wobblePosition.UP);
                opMode.sleep(200);
                Wobble.SetGrabberPosition(grabberPosition.GRAB);
                opMode.sleep(700);
                break;
            case INTERMEDIATE1:

                break;
            case INTERMEDIATE2:

                break;
            case WORST:

                break;
        }
//        Hardware.wall_left.setPosition(AutoUtil.leftWallDown);
//        drive.followTrajectory(Trajectories.secondWobbleReleaseB(drive.getPoseEstimate()));
//        Wobble.wobbleRelease();
//        Hardware.wall_left.setPosition(AutoUtil.leftWallDown);
    }


    @Override
    public void ShootSecondHighGoal(){
        switch (autoScene) {
            case FULL:
            case INTERMEDIATE1:
            case INTERMEDIATE2:
            case WORST:
            case QuantumInterior:
                break;
            case QuantumExterior:
                AutoUtil.startShooting(ConstantsAutonomous.exteriorShootingSpeedB);
                AutoUtil.shoot3Disks();
                break;
        }
    }



    public B(LinearOpMode opMode) {
        this.opMode = opMode;
    }
}
