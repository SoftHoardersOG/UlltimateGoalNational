package org.firstinspires.ftc.teamcode.Autonomous.Utils;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.teamcode.Autonomous.MainAuto;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.DetectionCase;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

@Config
public class DiskAmountDetection {

    public static double multiplyVal = 19f;
    public static double redMeanThreshold = 7; // it was 6.3
    public static int xOffset = 0;
    public static  int yOffset = 0;

    public static void stopDetection() {
        try {
            Hardware.cvCamera.stopStreaming();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


    public static int getDiskAmount(double redAmount) {
        if (redAmount < 1000) {
            return 0;
        } else if (redAmount < 70000) {
            return 1;
        } else {
            return 4;
        }
    }
    // 0 -- 117--118

    // 1 -- 123

    // 4 -- 130
   @Config
    public static class UltimateGoalPipeline extends OpenCvPipeline {

        Telemetry telemetry;
        boolean update;
        LinearOpMode opMode;

        public UltimateGoalPipeline(LinearOpMode opMode, boolean update) {
            this.telemetry = opMode.telemetry;
            this.update = update;
            this.opMode = opMode;
        }

        final Scalar BLUE = new Scalar(0, 0, 255);

        @Override
        public Mat processFrame(Mat input) {

            Mat RedMat = new Mat();
            Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(input, RedMat, 1);
            //Imgproc.cvtColor(input,input,Imgproc.COLOR_RGB2GRAY );
            //Imgproc.Canny(input, input, 60, 60*3);
            //Core.extractChannel(input, RedMat, 1);

            int n = RedMat.width(), m = RedMat.height();
            //    telemetry.addData("mat size is: ", n + " " + m);
            // Point firstPoint = new Point(n / 2 - 20 + pointDiff, m / 2 - 10 + pointDiff),
            //       secondPoint = new Point(n / 2 + 20 + pointDiff, m / 2 + 30 + pointDiff);
            Point firstPoint = new Point(n / 2 - 35 + xOffset, m / 2 + 40 + yOffset), secondPoint = new Point(n / 2 + xOffset, m / 2 + yOffset);
            Mat finalMat = new Mat(RedMat, new Rect(firstPoint,secondPoint));

            Imgproc.rectangle(
                    RedMat,
                    firstPoint,
                    secondPoint,
                    BLUE,
                    1
            );
            double redMean = (int) Core.sumElems(finalMat).val[0];
            redMean /= (n * m);
            telemetry.addData("The readMean is: ", redMean);
            for (int i = 0; i < finalMat.height(); i++) {
                for (int j = 0; j< finalMat.width() ; j++) {

                    if ((finalMat.get(i, j))[0] > redMean * multiplyVal && redMean > redMeanThreshold) {
                        finalMat.put(i, j, finalMat.get(i, j)[0] * 5);
                    } else {
                    finalMat.put(i, j, 0);
                    }
                }
            }

            //TODO : increase the multiplier (18-19) and make the result 0 when all the mat is white

            double redAmount = (int) Core.sumElems(finalMat).val[0];
            DetectionCase.diskAmount = getDiskAmount(redAmount); //TODO nu merge detectarea ta adriene //2; //
            DetectionCase.updateAutoCase(opMode);
            telemetry.addData("The red amount is:", redAmount);
            telemetry.addData("The number of disks is:", DetectionCase.diskAmount);
            if (update) {
                telemetry.update();
            }

            return RedMat;
        }
    }
}