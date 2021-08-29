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
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.wobblePosition;

import static org.firstinspires.ftc.teamcode.Autonomous.Initializations.drive;
import static java.lang.Thread.sleep;

public class C extends AutoCase {
//
//    public static double firstWobbleX = -157f; // -160f
//    public static double firstWobbleY = 100f;
//    public static double firstWobbleHeading = 180f;
//
//    public static double secondWobbleX = -105f;
//    public static double secondWobbleY = 135f;
//    public static double secondWobbleHeading = 270f;

    private final LinearOpMode opMode;

    @Override
    public void Shoot() {
        switch (autoScene) {
            case INTERMEDIATE1:
                //AutoUtil.wallPosition(wallState.VERTICAL);
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
                AutoUtil.shoot3Disks();
                //AutoUtil.wallPosition(wallState.INSIDE);
                break;
            case INTERMEDIATE2:
                ChangeShootingAngle.AngleControl(ConstantsAutonomous.intermediate2ShooterAngle);
                AutoUtil.startShooting(ConstantsAutonomous.intermediate2ShootingSpeed);
                drive.followTrajectory(TrajIntermTwo.ShootTrajectory(drive.getPoseEstimate()));
                AutoUtil.shoot3Disks();
                //AutoUtil.wallPosition(wallState.INSIDE);
                break;
            case WORST:
               // AutoUtil.wallPosition(wallState.VERTICAL);
                AutoUtil.startShooting(ConstantsAutonomous.worstShootingSpeed);
                ChangeShootingAngle.AngleControl(ConstantsAutonomous.worstShooterAngle);
                drive.followTrajectory(TrajWorst.shootTrajectory(drive.getPoseEstimate()));
                AutoUtil.shoot3Disks();
                //AutoUtil.wallPosition(wallState.INSIDE);
                break;
            case QuantumExterior:
                AutoUtil.startShooting(ConstantsAutonomous.exteriorShootingSpeedC);
                ChangeShootingAngle.AngleControl(ConstantsAutonomous.exteriorShooterAngleC);
                drive.followTrajectory(TrajQuantumExterior.ShootTrajectory(drive.getPoseEstimate()));
                AutoUtil.shoot3Disks();
               // AutoUtil.wallPosition(wallState.INSIDE);
                break;
            case QuantumInterior:
                break;
        }
    }

    @Override
    public void releaseFirstWobble() {
        switch (autoScene) {
            case FULL:
                drive.followTrajectory(TrajFull.releaseCTrajectory(drive.getPoseEstimate()));
                break;
            case INTERMEDIATE1:
                drive.followTrajectory(TrajIntermOne.releaseCTrajectory(drive.getPoseEstimate()));
                break;
            case INTERMEDIATE2:
                drive.followTrajectory(TrajIntermTwo.releaseCTrajectory(drive.getPoseEstimate()));
                //opMode.sleep(wobbleArmSleep);
                //Wobble.setGrabberPosition(grabberposition.free\grabpos.grab);
                //opMode.sleep(wobbleArmSleep);
                break;
            case WORST:
                break;
            case QuantumInterior:
                drive.followTrajectory(TrajQuantumInterior.goForwardC(drive.getPoseEstimate()));
                drive.followTrajectory(TrajQuantumInterior.releaseCTrajectory(drive.getPoseEstimate()));
                drive.followTrajectory(TrajQuantumInterior.goForwardC(drive.getPoseEstimate()));
                break;
            case QuantumExterior:
                drive.followTrajectory(TrajQuantumExterior.releaseCTrajectory(drive.getPoseEstimate()));
                break;
        }
//        drive.followTrajectory(Trajectories.firstWobbleReleaseC(drive.getPoseEstimate()));
//        Wobble.wobbleRelease();
        //opMode.sleep(wobbleArmSleep);
    }

    @Override
    public void releaseSecondWobble() {
        switch (autoScene) {
            case FULL:
                drive.followTrajectory(TrajFull.secondWobbleTrajC(drive.getPoseEstimate()));
                Wobble.motorArmToPosition(wobblePosition.DOWN);
                opMode.sleep(300);
                Wobble.wobbleRelease();
                break;
            case INTERMEDIATE1:
            case INTERMEDIATE2:
            case WORST:
            case QuantumInterior:
            case QuantumExterior:
                break;
        }
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
                AutoUtil.startShooting(ConstantsAutonomous.secondHighGoalShootingSpeed);
                ChangeShootingAngle.AngleControl(ConstantsAutonomous.secondHighGoalShooterAngle);
                AutoUtil.shoot3Disks();

                AutoUtil.startIntake();
                drive.followTrajectory(TrajQuantumExterior.collect4thDisk(drive.getPoseEstimate()));
                opMode.sleep(400);

                AutoUtil.stopIntakeMotor();
                AutoUtil.stopIntakeServo();

                drive.followTrajectory(TrajQuantumExterior.backAfter4thDisk(drive.getPoseEstimate()));

                ChangeShootingAngle.AngleControl(ConstantsAutonomous.secondHighGoalShooterAngle);
                AutoUtil.startShooting(ConstantsAutonomous.secondHighGoalShootingSpeed);
                //todo aici mai in spate
                AutoUtil.shoot3Disks();
                break;
        }
    }

//        Hardware.wall_left.setPosition(AutoUtil.leftWallDown);
//        drive.followTrajectory(Trajectories.secondWobbleReleaseC(drive.getPoseEstimate()));
//        Wobble.wobbleRelease();

    public C(LinearOpMode opMode) {
        this.opMode = opMode;
    }
}
