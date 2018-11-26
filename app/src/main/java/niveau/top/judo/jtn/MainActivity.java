package niveau.top.judo.jtn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Variables qui renseignent si les décompteurs fonctionnent ou pas
    private boolean setSol, setGeneral, plusOuMoins;
    
    // Les deux décompteurs (objets Java)
    private CountDownTimer chronoSol, chronoGeneral;
    
    // Les deux décompteurs (Boutons) et les RESET
    Button chronoBtn[];
    Button resetBtn[];
    Button scoreBtn[];
    ImageButton paramBtn;
    ImageButton plusOuMoinsButton;
    
    // Nombres dont on a besoin, dont des nombres de référence (Init)
    public static long sol, general, solInit, generalInit;
    
    // Nombre de minutes et de secondes (pour l'affichage)
    int min, sec;
    
    // Le tableau des scores
    int score[] = new int[8];
    
    // Imposer le format d'affichage
    DecimalFormat format = new DecimalFormat("#00");
    
    // Classe pour jouer des sons
    SoundPool soundPool;
    int clocheFin, changementTemps, marcheArret;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupèrer les Boutons
        chronoBtn = new Button[2];
        chronoBtn[0] = findViewById(R.id.chrono_general);
        chronoBtn[1] = findViewById(R.id.chrono_sol);
        resetBtn = new Button[2];
        resetBtn[0] = findViewById(R.id.reset_general);
        resetBtn[1] = findViewById(R.id.reset_sol);

        scoreBtn = new Button[8];
        scoreBtn[0] = findViewById(R.id.koka_blanc);
        scoreBtn[4] = findViewById(R.id.koka_rouge);

        scoreBtn[1] = findViewById(R.id.yuko_blanc);
        scoreBtn[5] = findViewById(R.id.yuko_rouge);

        scoreBtn[2] = findViewById(R.id.wazaari_blanc);
        scoreBtn[6] = findViewById(R.id.wazaari_rouge);

        scoreBtn[3] = findViewById(R.id.ippon_blanc);
        scoreBtn[7] = findViewById(R.id.ippon_rouge);

        chronoBtn[0] = findViewById(R.id.chrono_general);
        chronoBtn[1] = findViewById(R.id.chrono_sol);

        paramBtn =  findViewById(R.id.param);
        plusOuMoinsButton = findViewById(R.id.plus_ou_moins);
        
        //On cache les RESET
        for (int i = 0; i < resetBtn.length; i++) {
            resetBtn[i].setVisibility(View.GONE);
        }

        // Initialiser variables : on n'utile pas le décompteur
        setSol = false;
        setGeneral = false;
        plusOuMoins = true;

        // Initialisations
        solInit = 10000;
        generalInit = 60000;
        sol = 10000;
        general = 60000;

        // Affichage initial
        setText(true, general);
        setText(false, sol);

        // Initilisation du son
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
        clocheFin = soundPool.load(this, R.raw.ring, 1);
        changementTemps = soundPool.load(this, R.raw.time, 1);
        marcheArret = soundPool.load(this, R.raw.marche, 1);
    }

    public void koka_blanc(View view) {
        if (plusOuMoins) {
            if (score[3] != 1 && score[7] != 1) {
                score[0]++;
                // Jouer le son
                //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
            }
        } else{
            if (score[0] > 0) {
                score[0]--;
                // Jouer le son
                //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
            }
        }
        // Changer le texte
        scoreBtn[0].setText(Integer.toString(score[0]));
    }

    public void koka_rouge(View view) {
        if (plusOuMoins) {
            if (score[3] != 1 && score[7] != 1) {
                score[4]++;
                // Jouer le son
                //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
            }
        } else {
            if(score[4] > 0){
                score[4]--;
                // Jouer le son
                //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
            }
        }
        // Changer le texte
        scoreBtn[4].setText(Integer.toString(score[4]));
    }

    public void yuko_blanc(View view) {
        if (plusOuMoins) {
            if (score[3] != 1 && score[7] != 1) {
                score[1]++;
                // Jouer le son
                //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
            }
        } else {
            if (score[1] > 0){
                score[1]--;
                // Jouer le son
                //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
            }
        }
        // Changer le texte
        scoreBtn[1].setText(Integer.toString(score[1]));
    }

    public void yuko_rouge(View view) {
        if (plusOuMoins) {
            if (score[3] != 1 && score[7] != 1) {
                score[5]++;
                // Jouer le son
                //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
            }
        } else {
            if (score[5] > 0){
                score[5]--;
                // Jouer le son
                //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
            }
        }
        // Changer le texte
        scoreBtn[5].setText(Integer.toString(score[5]));
    }

    public void wazaari_blanc(View view) {
        if (plusOuMoins) {
            if (score[3] != 1 && score[7] != 1) {
                score[2]++;
                if (score[2] == 2) {
                    // Jouer le son
                    soundPool.play(clocheFin, 1, 1, 1, 0, 1);
                    score[3]++;
                    scoreBtn[2].setText(Integer.toString(score[2]));

                    //Arrêter le chrono
                    if (setGeneral) {
                        chronoGeneral.cancel();
                    }

                    chronoBtn[0].setText(getResources().getString(R.string.java_ippon));
                    chronoBtn[0].setBackgroundColor(getResources().getColor(R.color.white));
                    chronoBtn[0].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                    resetBtn[0].setVisibility(View.VISIBLE);
                } else {
                    // Jouer le son
                    //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
                    scoreBtn[2].setText(Integer.toString(score[2]));
                }
            }
        } else {
            if (score[2] > 0){
                score[2]--;

                // Jouer le son
                //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
                // Changer le texte
                scoreBtn[2].setText(Integer.toString(score[2]));
            }
        }
    }

    public void wazaari_rouge(View view) {
        if (plusOuMoins) {
            if (score[3] != 1 && score[7] != 1) {
                score[6]++;
                if (score[6] == 2) {
                    // Jouer le son
                    soundPool.play(clocheFin, 1, 1, 1, 0, 1);
                    score[7]++;
                    scoreBtn[6].setText(Integer.toString(score[6]));

                    //Arrêter le chrono
                    if (setGeneral) {
                        chronoGeneral.cancel();
                    }

                    chronoBtn[0].setText(getResources().getString(R.string.java_ippon));
                    chronoBtn[0].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    chronoBtn[0].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                    resetBtn[0].setVisibility(View.VISIBLE);
                } else {
                    // Jouer le son
                    //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
                    scoreBtn[6].setText(Integer.toString(score[6]));
                }
            }
        } else {
            if (score[6] > 0){
                score[6]--;

                // Jouer le son
                //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
                // Changer le texte
                scoreBtn[6].setText(Integer.toString(score[6]));
            }
        }
    }

    public void ippon_blanc(View view) {
        if (score[3] != 1 && score[7] != 1) {
            // Jouer le son
            soundPool.play(clocheFin, 1, 1, 1, 0, 1);
            score[3]++;
            scoreBtn[3].setText(Integer.toString(score[3]));

            //Arrêter le chrono
            if(setGeneral) {
                chronoGeneral.cancel();
            }

            chronoBtn[0].setText(getResources().getString(R.string.java_ippon));
            chronoBtn[0].setBackgroundColor(getResources().getColor(R.color.white));
            chronoBtn[0].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            resetBtn[0].setVisibility(View.VISIBLE);
        }
    }

    public void ippon_rouge(View view) {
        if (score[3] != 1 && score[7] != 1) {
            // Jouer le son
            soundPool.play(clocheFin, 1, 1, 1, 0, 1);
            score[7]++;
            scoreBtn[7].setText(Integer.toString(score[7]));

            //Arrêter le chrono
            if(setGeneral) {
                chronoGeneral.cancel();
            }

            chronoBtn[0].setText(getResources().getString(R.string.java_ippon));
            chronoBtn[0].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            chronoBtn[0].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            resetBtn[0].setVisibility(View.VISIBLE);
        }
    }

    // Cliquer sur le décompte du temps général
    public void setChronoGeneral(View view) {
        // On ne peut arrêter que si Sol ne fonctionne pas
        if (!setSol) {
            // Jouer le son
            //soundPool.play(marcheArret, 1, 1, 1, 0, 1);

            // Si le décompte ne tourne pas
            if (setGeneral == false) {
                // On crée le décompte
                chronoGeneral = new CountDownTimer(general, 1000) {

                    // Affiche quelque chose de nouveau toutes les secondes
                    public void onTick(long millisUntilFinished) {
                        setText(true, millisUntilFinished);
                        // Sauvegarder cette valeur pour si on arrête le décompte
                        general = millisUntilFinished;
                    }

                    // A faire quand le décompte est fini
                    public void onFinish() {
                        // Modifier l'affichage
                        chronoBtn[0].setText(getResources().getString(R.string.java_time));
                        chronoBtn[0].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        chronoBtn[0].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                        //Jouer le son que si l'autre chrono ne fonctionne pas
                        if (setSol == false) {
                            soundPool.play(clocheFin, 1, 1, 1, 0, 1);
                        }

                        setGeneral = false;

                        resetBtn[0].setVisibility(View.VISIBLE);
                    }
                }.start();
                // Le décompte fonctionne
                setGeneral = true;
                chronoBtn[0].setTextColor(getResources().getColor(R.color.colorPrimary));

                //Masquer les Reset
                resetBtn[0].setVisibility(View.GONE);

                //Cacher les paramatres
                paramBtn.setVisibility(View.GONE);

            } else {
                // Si le décompte tourne, on l'arrête
                chronoGeneral.cancel();

                // Changement de l'affichage
                chronoBtn[0].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                // Le décompteur ne tourne plus
                setGeneral = false;

                //Afficher le RESET
                resetBtn[0].setVisibility(View.VISIBLE);
            }
        }
    }

    // Cliquer sur le décompte de l'immobilisation
    public void setChronoSol(View view) {
        // On ne peut lancer ce décompte que si l'autre a déjà été lancé une fois
        if (setGeneral) {
            // Jouer le son
            //soundPool.play(marcheArret, 1, 1, 1, 0, 1);

            // Si le décompte ne tourne pas
            if (setSol == false) {
                // On crée le décompte
                chronoSol = new CountDownTimer(sol, 1000) {

                    // Affiche quelque chose de nouveau toutes les secondes
                    public void onTick(long millisUntilFinished) {
                        setText(false, millisUntilFinished);
                        // Sauvegarder cette valeur pour si on arrête le décompte
                        sol = millisUntilFinished;
                    }

                    // A faire quand le décompte est fini
                    public void onFinish() {
                        // Modifier l'affichage
                        chronoBtn[1].setText(getResources().getString(R.string.java_time));
                        chronoBtn[1].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        chronoBtn[1].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                        //Jouer le son
                        soundPool.play(clocheFin, 1, 1, 1, 0, 1);

                        //Arrêter le décompteur général
                        chronoGeneral.cancel();

                        setGeneral = false;

                        //Cacher les Boutons RESET SOL
                        resetBtn[1].setVisibility(View.GONE);
                        resetBtn[0].setVisibility(View.VISIBLE);
                    }
                }.start();
                // Le décompte fonctionne
                setSol = true;
                chronoBtn[1].setTextColor(getResources().getColor(R.color.colorPrimary));

                // Masquer RESET sol
                resetBtn[0].setVisibility(View.GONE);
            } else {
                // Si le décompte tourne, on l'arrête
                chronoSol.cancel();

                // Le décompteur ne tourne plus
                setSol = false;

                // Changement de l'affichage
                chronoBtn[1].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                //Afficher le RESET
                resetBtn[1].setVisibility(View.VISIBLE);
            }
        }
    }


    // Décompoer un LONG en minutes et secondes
    public void afficherMinute(long a) {
        sec = (int) a / 1000;
        min = 0;
        while (sec >= 60) {
            sec = sec - 60;
            min++;
        }
    }

    // Appuyer sur le bouton Reset
    public void resetGeneral(View view) {
        // On ne peut reset le chrono que s'il ne fonctionne pas
        if (!setGeneral) {
            // Jouer le son
            //soundPool.play(changementTemps, 1, 1, 1, 0, 1);

            // Si les décompteurs existent, on les arrête
            if (setSol) {
                chronoSol.cancel();
                setSol = false;
            }
            if (setGeneral) {
                chronoGeneral.cancel();
                setGeneral = false;
            }

            // Changement de l'affichage
            setText(true, generalInit);
            setText(false, solInit);
            for (int i = 0; i < chronoBtn.length; i++) {
                chronoBtn[i].setBackgroundColor(getResources().getColor(R.color.colorAccent));
                chronoBtn[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }

            // On reprend les valeur qui se trouve sur les boutons sélectionnés
            general = generalInit;
            sol = solInit;

            // Cacher les Reset
            for (int i = 0; i < resetBtn.length; i++) {
                resetBtn[i].setVisibility(View.GONE);
            }

            //Affcher les paramètres
            paramBtn.setVisibility(View.VISIBLE);
            plusOuMoinsButton.setBackgroundResource(R.drawable.minus);

            //Réinitialiser les scores
            for (int i = 0; i < score.length; i++) {
                score[i] = 0;
                scoreBtn[i].setText(Integer.toString(score[i]));
            }
        }
    }

    // Faire un reset du chronoSol uniquement
    public void resetSol(View view) {
        // On ne peut remettre à zéro que si le chrono ne fonctionne pas
        if (!setSol) {
            // Jouer le son
            //soundPool.play(changementTemps, 1, 1, 1, 0, 1);

            // Si les décompteurs existent, on les arrête
            if (setSol == true) {
                chronoSol.cancel();
                setSol = false;
            }

            // Changement de l'affichage et la couleur
            setText(false, solInit);
            chronoBtn[1].setTextColor((getResources().getColor(R.color.colorPrimaryDark)));

            // On reprend les valeur qui se trouve sur les boutons sélectionnés
            sol = solInit;

            // Masquer RESET
            resetBtn[1].setVisibility(View.GONE);
        }
    }

    // Changer le texte des décompteurs
    public void setText(boolean test, long nbr) {
        afficherMinute(nbr);
        if (test) {
            chronoBtn[0].setText(min + ":" + format.format(sec));
        } else {
            chronoBtn[1].setText(min + ":" + format.format(sec));
        }
    }

    public void changePlusOuMoins(View view){
        if (plusOuMoins){
            plusOuMoins = false;
            plusOuMoinsButton.setBackgroundResource(R.drawable.plus);
        }
        else{
            plusOuMoins = true;
            plusOuMoinsButton.setBackgroundResource(R.drawable.minus);
        }
    }

    public void goToParam(View view) {
        // Jouer le son
        soundPool.play(changementTemps, 1, 1, 1, 0, 1);
        startActivityForResult(new Intent(this, Parameters.class),1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == 1) {
                setText(true, general);
                setText(false, sol);
            }
        }
    }
}



