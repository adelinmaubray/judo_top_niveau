package niveau.top.judo.jtn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    static final int RESULT_CODE = 1;
    private static final int KOKA_BLANC = 0;
    private static final int YUKO_BLANC = 1;
    private boolean plusOuMoins;
    private static final int WAZAARI_BLANC = 2;
    private static final int IPPON_BLANC = 3;
    private static final int KOKA_ROUGE = 4;
    private static final int YUKO_ROUGE = 5;
    private static final int WAZAARI_ROUGE = 6;
    private static final int IPPON_ROUGE = 7;
    // Constants
    private static final int MAIN = 0;
    private static final int RED = 1;
    private static final int WHITE = 2;
    private static final int REQUEST_CODE = 1000;
    private static final int GENERAL_INIT_VALUE = 60000;
    private static final int SOL_INIT_VALUE = 10000;
    private static final int ADVANTAGE_WAZAARI = 5000;
    // Time and initTime
    static long solBlanc;
    static long solRouge;
    static long general;
    static long solInit;
    static long generalInit;
    private static long solInitRouge;
    private static long solInitBlanc;
    // Counter running variables
    private boolean setSolRouge;
    private boolean setSolBlanc;
    private boolean setGeneral;
    // Counter objects
    private CountDownTimer chronoSolRouge;
    private CountDownTimer chronoSolBlanc;
    private CountDownTimer chronoGeneral;
    // Counter and Reset Buttons
    private Button[] chronoBtn;
    private Button[] scoreBtn;
    private ImageButton paramBtn;
    // private int changementTemps;
    // private int marcheArret;
    private ImageButton plusOuMoinsBtn;
    private ImageButton[] resetBtn;
    // Number in min and sec
    private int min;
    private int sec;
    // Score Table
    private int[] score = new int[8];
    // Display Format (0:00)
    private DecimalFormat format = new DecimalFormat("#00");
    // Sound classes
    private SoundPool soundPool;
    private int clocheFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Button from View
        chronoBtn = new Button[3];
        chronoBtn[MAIN] = findViewById(R.id.chrono_general);
        chronoBtn[RED] = findViewById(R.id.chrono_sol_rouge);
        chronoBtn[WHITE] = findViewById(R.id.chrono_sol_blanc);

        resetBtn = new ImageButton[3];
        resetBtn[MAIN] = findViewById(R.id.reset_general);
        resetBtn[RED] = findViewById(R.id.reset_sol_rouge);
        resetBtn[WHITE] = findViewById(R.id.reset_sol_blanc);

        scoreBtn = new Button[8];
        scoreBtn[KOKA_BLANC] = findViewById(R.id.koka_blanc);
        scoreBtn[KOKA_ROUGE] = findViewById(R.id.koka_rouge);

        scoreBtn[YUKO_BLANC] = findViewById(R.id.yuko_blanc);
        scoreBtn[YUKO_ROUGE] = findViewById(R.id.yuko_rouge);

        scoreBtn[WAZAARI_BLANC] = findViewById(R.id.wazaari_blanc);
        scoreBtn[WAZAARI_ROUGE] = findViewById(R.id.wazaari_rouge);

        scoreBtn[IPPON_BLANC] = findViewById(R.id.ippon_blanc);
        scoreBtn[IPPON_ROUGE] = findViewById(R.id.ippon_rouge);

        paramBtn = findViewById(R.id.param);
        plusOuMoinsBtn = findViewById(R.id.plus_ou_moins);

        // Hide Reset Button
        for (ImageButton btn : resetBtn) {
            btn.setVisibility(View.GONE);
        }

        // Initialisate variables init
        setSolBlanc = false;
        setSolRouge = false;
        setGeneral = false;
        plusOuMoins = true;

        // Initialisation
        solInit = SOL_INIT_VALUE;
        solInitRouge = solInit;
        solInitBlanc = solInit;
        generalInit = GENERAL_INIT_VALUE;
        solBlanc = SOL_INIT_VALUE;
        solRouge = SOL_INIT_VALUE;
        general = GENERAL_INIT_VALUE;

        // Initial display
        setText(MAIN);
        setText(WHITE);
        setText(RED);

        // Sound initialisation
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
        clocheFin = soundPool.load(this, R.raw.ring, 1);
        // changementTemps = soundPool.load(this, R.raw.time, 1);
        // marcheArret = soundPool.load(this, R.raw.marche, 1);
    }

    @SuppressLint("SetTextI18n")
    public void koka_blanc(View view) {
        if (score[IPPON_BLANC] != 1 && score[IPPON_ROUGE] != 1) {
            if (plusOuMoins) {
                score[KOKA_BLANC]++;
                // Play sound
                //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
            } else {
                if (score[KOKA_BLANC] > 0) {
                    score[KOKA_BLANC]--;
                    // Play sound
                    //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
                }
            }
            // Change text
            scoreBtn[KOKA_BLANC].setText(Integer.toString(score[KOKA_BLANC]));
        }
    }

    @SuppressLint("SetTextI18n")
    public void koka_rouge(View view) {
        if (score[IPPON_BLANC] != 1 && score[IPPON_ROUGE] != 1) {
            if (plusOuMoins) {
                score[KOKA_ROUGE]++;
                // Play sound
                //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
            } else {
                if (score[KOKA_ROUGE] > 0) {
                    score[KOKA_ROUGE]--;
                    // Play sound
                    //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
                }
            }
            // Change text
            scoreBtn[KOKA_ROUGE].setText(Integer.toString(score[KOKA_ROUGE]));
        }
    }

    @SuppressLint("SetTextI18n")
    public void yuko_blanc(View view) {
        if (score[IPPON_BLANC] != 1 && score[IPPON_ROUGE] != 1) {
            if (plusOuMoins) {
                score[YUKO_BLANC]++;
                // Play sound
                //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
            } else {
                if (score[YUKO_BLANC] > 0) {
                    score[YUKO_BLANC]--;
                    // Play sound
                    //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
                }
            }
            // Change text
            scoreBtn[YUKO_BLANC].setText(Integer.toString(score[YUKO_BLANC]));
        }
    }

    @SuppressLint("SetTextI18n")
    public void yuko_rouge(View view) {
        if (score[IPPON_BLANC] != 1 && score[IPPON_ROUGE] != 1) {
            if (plusOuMoins) {
                score[YUKO_ROUGE]++;
                // Play sound
                //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
            } else {
                if (score[YUKO_ROUGE] > 0) {
                    score[YUKO_ROUGE]--;
                    // Play sound
                    //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
                }
            }
            // Change text
            scoreBtn[YUKO_ROUGE].setText(Integer.toString(score[YUKO_ROUGE]));
        }
    }

    @SuppressLint("SetTextI18n")
    public void wazaari_blanc(View view) {
        if (score[IPPON_BLANC] != 1 && score[IPPON_ROUGE] != 1) {
            if (plusOuMoins) {
                score[WAZAARI_BLANC]++;
                if (score[WAZAARI_BLANC] == 2) {
                    // Play sound
                    soundPool.play(clocheFin, 1, 1, 1, 0, 1);
                    score[IPPON_BLANC]++;
                    scoreBtn[WAZAARI_BLANC].setText(Integer.toString(score[WAZAARI_BLANC]));

                    // Stop counter
                    if (setGeneral) {
                        chronoGeneral.cancel();
                        setGeneral = false;
                    }

                    chronoBtn[MAIN].setText(getResources().getString(R.string.java_ippon));
                    chronoBtn[MAIN].setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    chronoBtn[MAIN].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));

                    resetBtn[MAIN].setVisibility(View.VISIBLE);
                } else {
                    // Play sound
                    //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
                    scoreBtn[WAZAARI_BLANC].setText(Integer.toString(score[WAZAARI_BLANC]));
                    solBlanc -= ADVANTAGE_WAZAARI;
                    solInitBlanc -= ADVANTAGE_WAZAARI;
                    setText(WHITE);
                }
            } else {
                if (score[WAZAARI_BLANC] > 0) {
                    score[WAZAARI_BLANC]--;

                    // Play sound
                    //soundPool.play(marcheArret, 1, 1, 1, 0, 1);

                    // Change text
                    scoreBtn[WAZAARI_BLANC].setText(Integer.toString(score[WAZAARI_BLANC]));
                    solInitBlanc += ADVANTAGE_WAZAARI;
                    resetSolBlanc(view);
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void wazaari_rouge(View view) {
        if (score[IPPON_BLANC] != 1 && score[IPPON_ROUGE] != 1) {
            if (plusOuMoins) {
                score[WAZAARI_ROUGE]++;
                if (score[WAZAARI_ROUGE] == 2) {
                    // Play sound
                    soundPool.play(clocheFin, 1, 1, 1, 0, 1);
                    score[IPPON_ROUGE]++;
                    scoreBtn[WAZAARI_ROUGE].setText(Integer.toString(score[WAZAARI_ROUGE]));

                    // Stop counter
                    if (setGeneral) {
                        chronoGeneral.cancel();
                        setGeneral = false;
                    }

                    chronoBtn[MAIN].setText(getResources().getString(R.string.java_ippon));
                    chronoBtn[MAIN].setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                    chronoBtn[MAIN].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));

                    resetBtn[MAIN].setVisibility(View.VISIBLE);
                } else {
                    // Play sound
                    //soundPool.play(marcheArret, 1, 1, 1, 0, 1);
                    scoreBtn[WAZAARI_ROUGE].setText(Integer.toString(score[WAZAARI_ROUGE]));
                    solRouge -= ADVANTAGE_WAZAARI;
                    solInitRouge -= ADVANTAGE_WAZAARI;
                    setText(RED);
                }
            } else {
                if (score[WAZAARI_ROUGE] > 0) {
                    score[WAZAARI_ROUGE]--;

                    // Play sound
                    //soundPool.play(marcheArret, 1, 1, 1, 0, 1);

                    // Change text
                    scoreBtn[WAZAARI_ROUGE].setText(Integer.toString(score[WAZAARI_ROUGE]));
                    solInitRouge += ADVANTAGE_WAZAARI;
                    resetSolRouge(view);
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void ippon_blanc(View view) {
        if (score[IPPON_BLANC] != 1 && score[IPPON_ROUGE] != 1) {
            // Play sound
            soundPool.play(clocheFin, 1, 1, 1, 0, 1);
            score[IPPON_BLANC]++;
            scoreBtn[IPPON_BLANC].setText(Integer.toString(score[IPPON_BLANC]));

            // Stop counter
            if (setGeneral) {
                chronoGeneral.cancel();
                setGeneral = false;
            }

            chronoBtn[MAIN].setText(getResources().getString(R.string.java_ippon));
            chronoBtn[MAIN].setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            chronoBtn[MAIN].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));

            resetBtn[MAIN].setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("SetTextI18n")
    public void ippon_rouge(View view) {
        if (score[IPPON_BLANC] != 1 && score[IPPON_ROUGE] != 1) {
            // Play sound
            soundPool.play(clocheFin, 1, 1, 1, 0, 1);
            score[IPPON_ROUGE]++;
            scoreBtn[IPPON_ROUGE].setText(Integer.toString(score[IPPON_ROUGE]));

            // Stop counter
            if (setGeneral) {
                chronoGeneral.cancel();
                setGeneral = false;
            }

            chronoBtn[MAIN].setText(getResources().getString(R.string.java_ippon));
            chronoBtn[MAIN].setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
            chronoBtn[MAIN].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));

            resetBtn[MAIN].setVisibility(View.VISIBLE);
        }
    }

    // Click main counter
    public void setChronoGeneral(View view) {
        // Can stop only if SOL not counting
        if (!setSolBlanc && !setSolRouge) {
            // Play sound
            //soundPool.play(marcheArret, 1, 1, 1, 0, 1);

            // If counter not running
            if (!setGeneral) {
                // Counter creation
                chronoGeneral = new CountDownTimer(general, 500) {

                    // Uptate every seconde
                    public void onTick(long millisUntilFinished) {
                        // Save new value
                        general = millisUntilFinished;
                        setText(MAIN);
                    }

                    // When counter is over
                    public void onFinish() {
                        // Block scores
                        // score[IPPON_ROUGE]++;

                        // Update display
                        chronoBtn[MAIN].setText(getResources().getString(R.string.java_time));
                        chronoBtn[MAIN].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));

                        setGeneral = false;
                        resetBtn[MAIN].setVisibility(View.VISIBLE);

                        // Play sound only if other counters are not running
                        if (!setSolBlanc && !setSolRouge) {
                            soundPool.play(clocheFin, 1, 1, 1, 0, 1);
                            // Allow to stop SOL chrono
                            setGeneral = true;
                        }


                    }
                }.start();
                // Counter is running
                setGeneral = true;
                chronoBtn[MAIN].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.brown));

                // Hide Reset
                resetBtn[MAIN].setVisibility(View.GONE);

                // Hide Param
                paramBtn.setVisibility(View.GONE);

            } else {
                // If counter running, stop it
                chronoGeneral.cancel();
                setGeneral = false;

                // Update display
                chronoBtn[MAIN].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));

                // Display Reset
                resetBtn[MAIN].setVisibility(View.VISIBLE);
            }
        }
    }

    // SolRed clicked
    public void setChronoSolRouge(View view) {
        // Can strat only if main counter is running
        // AND the other is not running
        if (setGeneral && !setSolBlanc) {
            // Play sound
            //soundPool.play(marcheArret, 1, 1, 1, 0, 1);

            // If counter not running
            if (!setSolRouge) {
                // Counter creation
                chronoSolRouge = new CountDownTimer(solRouge, 500) {

                    // Update every second
                    public void onTick(long millisUntilFinished) {
                        // Save new value
                        solRouge = millisUntilFinished;
                        setText(RED);
                    }

                    // When counter is over
                    @SuppressLint("SetTextI18n")
                    public void onFinish() {
                        // Update score
                        if (score[WAZAARI_ROUGE] == 1) {
                            score[WAZAARI_ROUGE]++;
                            scoreBtn[WAZAARI_ROUGE].setText(Integer.toString(score[WAZAARI_ROUGE]));
                        } else {
                            score[IPPON_ROUGE]++;
                            scoreBtn[IPPON_ROUGE].setText(Integer.toString(score[IPPON_ROUGE]));
                        }

                        // Update display
                        chronoBtn[RED].setText(getResources().getString(R.string.java_time));
                        chronoBtn[RED].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));

                        chronoBtn[MAIN].setText(getResources().getString(R.string.java_ippon));
                        chronoBtn[MAIN].setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                        chronoBtn[MAIN].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));

                        //Play sound
                        soundPool.play(clocheFin, 1, 1, 1, 0, 1);

                        // Stop main counter
                        chronoGeneral.cancel();
                        setGeneral = false;

                        // Hide Reset SOL
                        resetBtn[RED].setVisibility(View.GONE);
                        resetBtn[MAIN].setVisibility(View.VISIBLE);
                    }
                }.start();
                // Counter is running
                setSolRouge = true;
                chronoBtn[RED].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.brown));

                // Hide Reset SOL
                resetBtn[MAIN].setVisibility(View.GONE);
            } else {
                // If counter running, stop it
                chronoSolRouge.cancel();

                // Counter is not running anymore
                setSolRouge = false;

                // Update display
                chronoBtn[RED].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));

                // Display Reset
                resetBtn[RED].setVisibility(View.VISIBLE);
            }
        }
    }

    // SolWhite clicked
    public void setChronoSolBlanc(View view) {
        // Can strat only if main counter is running
        // AND the other is not running
        if (setGeneral && !setSolRouge) {
            // Play sound
            //soundPool.play(marcheArret, 1, 1, 1, 0, 1);

            // If counter not running
            if (!setSolBlanc) {
                // Counter creation
                chronoSolBlanc = new CountDownTimer(solBlanc, 500) {

                    // Update every second
                    public void onTick(long millisUntilFinished) {
                        // Save new value
                        solBlanc = millisUntilFinished;
                        setText(WHITE);
                    }

                    // When counter is over
                    @SuppressLint("SetTextI18n")
                    public void onFinish() {
                        // Update score
                        if (score[WAZAARI_BLANC] == 1) {
                            score[WAZAARI_BLANC]++;
                            scoreBtn[WAZAARI_BLANC].setText(Integer.toString(score[WAZAARI_BLANC]));
                        } else {
                            score[IPPON_BLANC]++;
                            scoreBtn[IPPON_BLANC].setText(Integer.toString(score[IPPON_BLANC]));
                        }

                        // Update display
                        chronoBtn[WHITE].setText(getResources().getString(R.string.java_time));
                        chronoBtn[WHITE].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));

                        chronoBtn[MAIN].setText(getResources().getString(R.string.java_ippon));
                        chronoBtn[MAIN].setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                        chronoBtn[MAIN].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));

                        //Play sound
                        soundPool.play(clocheFin, 1, 1, 1, 0, 1);

                        // Stop main counter
                        chronoGeneral.cancel();
                        setGeneral = false;

                        // Hide Reset SOL
                        resetBtn[WHITE].setVisibility(View.GONE);
                        resetBtn[MAIN].setVisibility(View.VISIBLE);
                    }
                }.start();
                // Counter is running
                setSolBlanc = true;
                chronoBtn[WHITE].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.brown));

                // Hide Reset SOL
                resetBtn[MAIN].setVisibility(View.GONE);
            } else {
                // If counter running, stop it
                chronoSolBlanc.cancel();

                // Counter is not running anymore
                setSolBlanc = false;

                // Update display
                chronoBtn[WHITE].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));

                // Display Reset
                resetBtn[WHITE].setVisibility(View.VISIBLE);
            }
        }
    }


    // Slip LONG into MIN and SEC
    private void afficherMinute(long a) {
        sec = (int) a / 1000;
        min = 0;
        while (sec >= 60) {
            sec = sec - 60;
            min++;
        }
    }

    // Main reset button clicked
    public void resetGeneral(View view) {
        // Reset counter only if it is not running (normally, it is always the case)
        if (!setGeneral) {
            // Play sound
            //soundPool.play(changementTemps, 1, 1, 1, 0, 1);

            // If counters exists, cancel them
            if (setSolRouge) {
                chronoSolRouge.cancel();
                setSolRouge = false;
            }
            if (setSolBlanc) {
                chronoSolBlanc.cancel();
                setSolBlanc = false;
            }

            // Take INIT values
            general = generalInit;
            solRouge = solInit;
            solBlanc = solInit;
            solInitRouge = solInit;
            solInitBlanc = solInit;

            // Update display
            setText(MAIN);
            setText(RED);
            setText(WHITE);

            chronoBtn[MAIN].setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
            chronoBtn[MAIN].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));

            // Hide Reset
            for (ImageButton btn : resetBtn) {
                btn.setVisibility(View.GONE);
            }

            // Display Param
            paramBtn.setVisibility(View.VISIBLE);
            plusOuMoinsBtn.setBackgroundResource(R.drawable.minus);
            plusOuMoins = true;

            // Init scores
            for (int i = 0; i < score.length; i++) {
                score[i] = 0;
                scoreBtn[i].setText(Integer.toString(score[i]));
            }
        }
    }

    // Reset SolRed only
    public void resetSolRouge(View view) {
        // Can Reset only is counter is not running (normally, it is always the case)
        if (!setSolRouge) {
            // Play sound
            //soundPool.play(changementTemps, 1, 1, 1, 0, 1);

            // Take INIT values
            solRouge = solInitRouge;

            // Update display and color
            setText(RED);
            chronoBtn[RED].setTextColor((ContextCompat.getColor(getApplicationContext(), R.color.colorAccent)));

            // Hide Reset
            resetBtn[RED].setVisibility(View.GONE);
        }
    }

    // Reset SolWhite only
    public void resetSolBlanc(View view) {
        // Can Reset only is counter is not running (normally, it is always the case)
        if (!setSolBlanc) {
            // Play sound
            //soundPool.play(changementTemps, 1, 1, 1, 0, 1);

            // Take INIT values
            solBlanc = solInitBlanc;

            // Update display et la couleur
            setText(WHITE);
            chronoBtn[WHITE].setTextColor((ContextCompat.getColor(getApplicationContext(), R.color.colorAccent)));

            // Hide Reset
            resetBtn[WHITE].setVisibility(View.GONE);
        }
    }

    // Change counter text
    @SuppressLint("SetTextI18n")
    private void setText(int test) {
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

    // Button which increment or decrement score
    public void changePlusOuMoins(View view) {
        // TRUE = increment
        // FALSE = decrement
        if (plusOuMoins) {
            plusOuMoins = false;
            plusOuMoinsBtn.setBackgroundResource(R.drawable.plus);
        } else {
            plusOuMoins = true;
            plusOuMoinsBtn.setBackgroundResource(R.drawable.minus);
        }
    }

    // Go to ParamPage
    public void goToParam(View view) {
        // Play sound
        //soundPool.play(changementTemps, 1, 1, 1, 0, 1);
        startActivityForResult(new Intent(this, Parameters.class), REQUEST_CODE);
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



