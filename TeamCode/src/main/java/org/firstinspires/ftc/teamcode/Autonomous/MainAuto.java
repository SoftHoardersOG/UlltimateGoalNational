//package org.firstinspires.ftc.teamcode.Autonomous;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.DetectionCase;
//import org.firstinspires.ftc.teamcode.Autonomous.Utils.DiskAmountDetection;
//import org.firstinspires.ftc.teamcode.TeleOperated.armCase;
//
//import static org.firstinspires.ftc.teamcode.Autonomous.Initializations.drive;
//
//@Autonomous(name = "Autonomous")
//public class MainAuto extends LinearOpMode {
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        Initializations.AutoInit(this, armCase.RIGHT);
//        waitForStart();
//
//
//        DiskAmountDetection.stopDetection();
//
//        PowerShots.initialization(drive, this);
//        PowerShots.run();
//
//
//        //DetectionCase.autoCase.releaseFirstWobble();
//
//        //AutoCase.collectSecondWobble(this);
//
//
//DetectionCase.autoCase.IntakeShoot();
////
////        autoCase.releaseSecondWobble();
////        sleep(50);
////
////
////        PoseStorage.imuAndWobble = new Pose2d(Math.toDegrees(drive.getPoseEstimate().getHeading()), Hardware.grabber.getCurrentPosition(), 0);
////        PoseStorage.currentPose = drive.getPoseEstimate();
//
////        while (opModeIsActive() && !isStopRequested()) {
////            idle();
////        }
//
//    }
//}
