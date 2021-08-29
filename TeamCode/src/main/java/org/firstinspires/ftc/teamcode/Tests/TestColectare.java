//Pachetul care contine clasa curenta
//**un pachet este o grupare de clase,enumeratii,interfate..
package org.firstinspires.ftc.teamcode.Tests;

//Impotam pachetele care contin clasele ale caror instante sunt folosite in scrierea codului
//Importul se face automat la apelarea unui obiect cu definitie in afara clasei

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@TeleOp(name = "TestRecruti", group = "TeleOps")
//Se foloseste pentru o mai usoara indentificare a clasei in aplicatia mobila
//numele clasei si SuperClass-ul acesteia -- LinearOpMode
public class TestColectare extends LinearOpMode {

    /*
    Aici se pot declara variabile si obiecte globale/publice sau private
     */

    DcMotor stangaColectare;
    DcMotor dreaptaColectare;

    @Override
    //Ne ofera abilitatea de a suprascrie o metoda a superclass-ei astfel definind un comportament specific clasei subordonate (cea actuala)
    //Urmeaza apelarea functiei principale in care sunt definite toate functionalitatile acestei calsei. @Override se adreseaza acestei metode
    //throws se foloseste pentru debuging si pentru rezolvarea "erorilor" (exceptiilor) definind astfel o exceptie ce e posibil sa apara in rularea
    //programului cu scopul de a-l ajuta pe programator sa o intampine. In cazul nostru nu este obligatoriu sa scriem partea cu "throws InterruptedException "
    public void runOpMode() throws InterruptedException {
    /*
    Aici se pot scrie secvente de cod cu rulare liniara (care ruleaza o singura data)
     */

        //Facem legatura dintre configuratia de pe telefon (care arata in ce port este conectat fiecare dispozitiv hardware) si obiectul din software
        stangaColectare = hardwareMap.get(DcMotor.class, "aspira1"); //Metoda generala careia trebuie sa-i specificam clasa pe care incercam sa o configuram ca si parametru (ex. : DcMotor.class )
        dreaptaColectare = hardwareMap.dcMotor.get("aspira2"); //Metoda particulara careia ii este necesar doar numele din configuratie

        //Modificam directia motorului apeland o metoda ce cere ca paramentru o orientare ( REVERSE sau FORWARD )
        stangaColectare.setDirection(DcMotorSimple.Direction.REVERSE);
        dreaptaColectare.setDirection(DcMotorSimple.Direction.FORWARD);
        waitForStart();

        //Structura repetitiva ce se repeta cat timp codul este inca pornit (de cand apasam PLAY in aplicatia mobila si pana cand apasam STOP )
        while (opModeIsActive() && !isStopRequested()) {

            /*
            Aici este locul instructiunilor care au nevoie sa ruleze repetat/iterativ actualizand in timp real parametrii si valori
                   exemplu: controlul unui motor cu ajutorul unui joystick
             */
            double apasare = gamepad1.right_trigger; //aceasta metoda va returna un numar real intre 0 si 1 reprezentant gradul de apasaere al butonului variabil din parte superioara a controllerului
            //Aceasta valoare este acum salvata in variabila apasare

            //Aplicam obiectelor de tip DcMotor metoda setPower() pentru a le induce o miscare
            // setPower(double power) accepta ca si parametru un numar real intre -1 si 1 avand astfel control complet atat asupra directiei cat si vitezei
            dreaptaColectare.setPower(apasare);
            stangaColectare.setPower(apasare);
        }


    }
}

//                                                  V-am pupat!