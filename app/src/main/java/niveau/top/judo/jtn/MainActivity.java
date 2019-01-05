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

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Variables qui renseignent si les décompteurs fonctionnent ou pas
    private boolean setSolRouge, setSolBlanc, setGeneral;
    private boolean plusOuMoins;
    
    // Les deux décompteurs (objets Java)
    private CountDownTimer chronoSolRouge, chronoSolBlanc, chronoGeneral;
    
    // Les deux décompteurs (Boutons) et les RESET
    Button chronoBtn[];
    Button scoreBtn[];
    ImageButton resetBtn[];
    ImageButton paramBtn;
    ImageButton plusOuMoinsBtn;
    
    // Nombres dont on a besoin, dont des nombres de référence (Init)
    public static long solBlanc, solRouge, general, solInit, generalInit;
    
    // Nombre de minutes et de secondes (pour l'affichage)
    int min, sec;
    
    // Le tableau des scores
    int score[] = new int[8];
    
    // Imposer le format d'affichage
    DecimalFormat format = new DecimalFormat("#00");
    
    // Classe pour jouer des sons
    SoundPool soundPool;
    int clocheFin, changementTemps, marcheArret;

    // Constante
    static final int MAIN = 0;
    static final int RED = 1;
    static final int WHITE = 2;
    
    public static final int REQUEST_CODE = 1000;
    public static final int RESULT_CODE = 1;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupèrer les Boutons
        chronoBtn = new Button[3];
        chronoBtn[MAIN] = findViewById(R.id.chrono_general);
        chronoBtn[RED] = findViewById(R.id.chrono_sol_rouge);
        chronoBtn[WHITE] = findViewById(R.id.chrono_sol_blanc);
        
        resetBtn = new ImageButton[3];
        resetBtn[MAIN] = findViewById(R.id.reset_general);
        resetBtn[RED] = findViewById(R.id.reset_sol_rouge);
        resetBtn[WHITE] = findViewById(R.id.reset_sol_blanc);

        scoreBtn = new Button[8];
        scoreBtn[0] = findViewById(R.id.koka_blanc);
        scoreBtn[4] = findViewById(R.id.koka_rouge);

        scoreBtn[1] = findViewById(R.id.yuko_blanc);
        scoreBtn[5] = findViewById(R.id.yuko_rouge);

        scoreBtn[2] = findViewById(R.id.wazaari_blanc);
        scoreBtn[6] = findViewById(R.id.wazaari_rouge);

        scoreBtn[3] = findViewById(R.id.ippon_blanc);
        scoreBtn[7] = findViewById(R.id.ippon_rouge);

        paramBtn =  findViewById(R.id.param);
        plusOuMoinsBtn = findViewById(R.id.plus_ou_moins);
        
        //On cache les RESET
//        for (int i = 0; i < resetBtn.length; i++) {
//            resetBtn[i].setVisibility(View.GONE);
//        }

        for(ImageButton btn : resetBtn){
            btn.setVisibility(View.GONE);
        }

        // Initialiser variables : on n'utile pas le décompteur
        setSolBlanc = false;
        setSolRouge = false;
        setGeneral = false;
        plusOuMoins = true;

        // Initialisations
        solInit = 10000;
        generalInit = 60000;
        solBlanc = 10000;
        solRouge = 10000;
        general = 60000;

        // Affichage initial
        setText(MAIN);
        setText(WHITE);
        setText(RED);

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

                    chronoBtn[MAIN].setText(getResources().getString(R.string.java_ippon));
                    chronoBtn[MAIN].setBackgroundColor(getResources().getColor(R.color.white));
                    chronoBtn[MAIN].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                    resetBtn[MAIN].setVisibility(View.VISIBLE);
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

                    chronoBtn[MAIN].setText(getResources().getString(R.string.java_ippon));
                    chronoBtn[MAIN].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    chronoBtn[MAIN].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                    resetBtn[MAIN].setVisibility(View.VISIBLE);
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

            chronoBtn[MAIN].setText(getResources().getString(R.string.java_ippon));
            chronoBtn[MAIN].setBackgroundColor(getResources().getColor(R.color.white));
            chronoBtn[MAIN].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            resetBtn[MAIN].setVisibility(View.VISIBLE);
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

            chronoBtn[MAIN].setText(getResources().getString(R.string.java_ippon));
            chronoBtn[MAIN].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            chronoBtn[MAIN].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            resetBtn[MAIN].setVisibility(View.VISIBLE);
        }
    }

    // Cliquer sur le décompte du temps général
    public void setChronoGeneral(View view) {
        // On ne peut arrêter que si Sol ne fonctionne pas
        if (!setSolBlanc && !setSolRouge) {
            // Jouer le son
            //soundPool.play(marcheArret, 1, 1, 1, 0, 1);

            // Si le décompte ne tourne pas
            if (!setGeneral) {
                // On crée le décompte
                chronoGeneral = new CountDownTimer(general, 1000) {

                    // Affiche quelque chose de nouveau toutes les secondes
                    public void onTick(long millisUntilFinished) {
                        // Sauvegarder cette valeur pour si on arrête le décompte
                        general = millisUntilFinished;
                        setText(MAIN);
                    }

                    // A faire quand le décompte est fini
                    public void onFinish() {
                        // Modifier l'affichage
                        chronoBtn[MAIN].setText(getResources().getString(R.string.java_time));
                        chronoBtn[MAIN].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        chronoBtn[MAIN].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                        //Jouer le son que si l'autre chrono ne fonctionne pas
                        if (!setSolBlanc || !setSolRouge) {
                            soundPool.play(clocheFin, 1, 1, 1, 0, 1);
                        }

                        setGeneral = false;
                        resetBtn[MAIN].setVisibility(View.VISIBLE);
                    }
                }.start();
                // Le décompte fonctionne
                setGeneral = true;
                chronoBtn[MAIN].setTextColor(getResources().getColor(R.color.colorPrimary));

                //Masquer les Reset
                resetBtn[MAIN].setVisibility(View.GONE);

                //Cacher les paramatres
                paramBtn.setVisibility(View.GONE);

            } else {
                // Si le décompte tourne, on l'arrête
                chronoGeneral.cancel();

                // Changement de l'affichage
                chronoBtn[MAIN].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                // Le décompteur ne tourne plus
                setGeneral = false;

                //Afficher le RESET
                resetBtn[MAIN].setVisibility(View.VISIBLE);
            }
        }
    }

    // Cliquer sur le décompte de l'immobilisation
    public void setChronoSolRouge(View view) {
        // On ne peut lancer ce décompte que si le général a déjà été lancé une fois ET que si l'autre ne tourne pas
        if (setGeneral && !setSolBlanc) {
            // Jouer le son
            //soundPool.play(marcheArret, 1, 1, 1, 0, 1);

            // Si le décompte ne tourne pas
            if (!setSolRouge) {
                // On crée le décompte
                chronoSolRouge = new CountDownTimer(solRouge, 1000) {

                    // Affiche quelque chose de nouveau toutes les secondes
                    public void onTick(long millisUntilFinished) {
                        // Sauvegarder cette valeur pour si on arrête le décompte
                        solRouge = millisUntilFinished;
                        setText(RED);
                    }

                    // A faire quand le décompte est fini
                    public void onFinish() {
                        // Modifier l'affichage
                        chronoBtn[RED].setText(getResources().getString(R.string.java_time));
                        chronoBtn[RED].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        chronoBtn[RED].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                        //Jouer le son
                        soundPool.play(clocheFin, 1, 1, 1, 0, 1);

                        //Arrêter le décompteur général
                        chronoGeneral.cancel();
                        setGeneral = false;

                        //Cacher les Boutons RESET SOL
                        resetBtn[RED].setVisibility(View.GONE);
                        resetBtn[MAIN].setVisibility(View.VISIBLE);
                    }
                }.start();
                // Le décompte fonctionne
                setSolRouge = true;
                chronoBtn[RED].setTextColor(getResources().getColor(R.color.colorPrimary));

                // Masquer RESET sol
                resetBtn[MAIN].setVisibility(View.GONE);
            } else {
                // Si le décompte tourne, on l'arrête
                chronoSolRouge.cancel();

                // Le décompteur ne tourne plus
                setSolRouge = false;

                // Changement de l'affichage
                chronoBtn[RED].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                //Afficher le RESET
                resetBtn[RED].setVisibility(View.VISIBLE);
            }
        }
    }

    public void setChronoSolBlanc(View view) {
        // On ne peut lancer ce décompte que si le général a déjà été lancé une fois ET que si l'autre ne tourne pas
        if (setGeneral && !setSolRouge) {
            // Jouer le son
            //soundPool.play(marcheArret, 1, 1, 1, 0, 1);

            // Si le décompte ne tourne pas
            if (!setSolBlanc) {
                // On crée le décompte
                chronoSolBlanc = new CountDownTimer(solBlanc, 1000) {

                    // Affiche quelque chose de nouveau toutes les secondes
                    public void onTick(long millisUntilFinished) {
                        // Sauvegarder cette valeur pour si on arrête le décompte
                        solBlanc = millisUntilFinished;
                        setText(WHITE);
                    }

                    // A faire quand le décompte est fini
                    public void onFinish() {
                        // Modifier l'affichage
                        chronoBtn[WHITE].setText(getResources().getString(R.string.java_time));
                        chronoBtn[WHITE].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        chronoBtn[WHITE].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                        //Jouer le son
                        soundPool.play(clocheFin, 1, 1, 1, 0, 1);

                        //Arrêter le décompteur général
                        chronoGeneral.cancel();
                        setGeneral = false;

                        //Cacher les Boutons RESET SOL
                        resetBtn[WHITE].setVisibility(View.GONE);
                        resetBtn[MAIN].setVisibility(View.VISIBLE);
                    }
                }.start();
                // Le décompte fonctionne
                setSolBlanc = true;
                chronoBtn[WHITE].setTextColor(getResources().getColor(R.color.colorPrimary));

                // Masquer RESET sol
                resetBtn[MAIN].setVisibility(View.GONE);
            } else {
                // Si le décompte tourne, on l'arrête
                chronoSolBlanc.cancel();

                // Le décompteur ne tourne plus
                setSolBlanc = false;

                // Changement de l'affichage
                chronoBtn[WHITE].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                //Afficher le RESET
                resetBtn[WHITE].setVisibility(View.VISIBLE);
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
            if (setGeneral) {
                chronoGeneral.cancel();
                setGeneral = false;
            }
            if (setSolRouge) {
                chronoSolRouge.cancel();
                setSolRouge = false;
            }
            if (setSolBlanc){
                chronoSolBlanc.cancel();
                setSolBlanc = false;
            }

            // On reprend les valeur qui se trouve sur les boutons sélectionnés
            general = generalInit;
            solRouge = solInit;
            solBlanc = solInit;

            // Changement de l'affichage
            setText(MAIN);
            setText(RED);
            setText(WHITE);
//            for (int i = 0; i < chronoBtn.length; i++) {
//                chronoBtn[i].setBackgroundColor(getResources().getColor(R.color.colorAccent));
//                chronoBtn[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
//            }

            for (Button btn : chronoBtn){
                btn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                btn.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }

            // Cacher les Reset
//            for (int i = 0; i < resetBtn.length; i++) {
//                resetBtn[i].setVisibility(View.GONE);
//            }

            for (ImageButton btn : resetBtn){
                btn.setVisibility(View.GONE);
            }

            //Affcher les paramètres
            paramBtn.setVisibility(View.VISIBLE);
            plusOuMoinsBtn.setBackgroundResource(R.drawable.minus);
            plusOuMoins = true;

            //Réinitialiser les scores
            for (int i = 0; i < score.length; i++) {
                score[i] = 0;
                scoreBtn[i].setText(Integer.toString(score[i]));
            }
        }
    }

    // Faire un reset du chronoSolRouge uniquement
    public void resetSolRouge(View view) {
        // On ne peut remettre à zéro que si le chrono ne fonctionne pas
        if (!setSolRouge) {
            // Jouer le son
            //soundPool.play(changementTemps, 1, 1, 1, 0, 1);

            // Si les décompteurs existent, on les arrête
            if (setSolRouge) {
                chronoSolRouge.cancel();
                setSolRouge = false;
            }

            // On reprend les valeur qui se trouve sur les boutons sélectionnés
            solRouge = solInit;

            // Changement de l'affichage et la couleur
            setText(RED);
            chronoBtn[RED].setTextColor((getResources().getColor(R.color.colorPrimaryDark)));

            // Masquer RESET
            resetBtn[RED].setVisibility(View.GONE);
        }
    }

    // Faire un reset du chronoSolBlanc uniquement
    public void resetSolBlanc(View view) {
        // On ne peut remettre à zéro que si le chrono ne fonctionne pas
        if (!setSolBlanc) {
            // Jouer le son
            //soundPool.play(changementTemps, 1, 1, 1, 0, 1);

            // Si les décompteurs existent, on les arrête
            if (setSolBlanc) {
                chronoSolBlanc.cancel();
                setSolBlanc = false;
            }

            // On reprend les valeur qui se trouve sur les boutons sélectionnés
            solBlanc = solInit;

            // Changement de l'affichage et la couleur
            setText(WHITE);
            chronoBtn[WHITE].setTextColor((getResources().getColor(R.color.colorPrimaryDark)));

            // Masquer RESET
            resetBtn[WHITE].setVisibility(View.GONE);
        }
    }

    // Changer le texte des décompteurs
    public void setText(int test) {
        if (test == MAIN) {
            afficherMinute(general);
            chronoBtn[MAIN].setText(min + ":" + format.format(sec));
        } else if (test == RED) {
            afficherMinute(solRouge);
            chronoBtn[RED].setText(min + ":" + format.format(sec));
        } else if (test == WHITE) {
            afficherMinute(solBlanc);
            chronoBtn[2].setText(min + ":" + format.format(sec));
        }
    }

    public void changePlusOuMoins(View view){
        if (plusOuMoins){
            plusOuMoins = false;
            plusOuMoinsBtn.setBackgroundResource(R.drawable.plus);
        }
        else{
            plusOuMoins = true;
            plusOuMoinsBtn.setBackgroundResource(R.drawable.minus);
        }
    }

    public void goToParam(View view) {
        // Jouer le son
        //soundPool.play(changementTemps, 1, 1, 1, 0, 1);
        startActivityForResult(new Intent(this, Parameters.class),REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_CODE) {
                setText(MAIN);
                setText(RED);
                setText(WHITE);
            }
        }
    }
}



