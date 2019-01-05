package niveau.top.judo.jtn;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

public class Parameters extends AppCompatActivity {

    // Sound variable
    private int changementTemps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);

        SoundPool soundPool;
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
        changementTemps = soundPool.load(this, R.raw.time, 1);
    }

    // Click on "Combat" RadioButton
    public void onRadioButtonGeneralOnclick(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.trente:
                if (checked) {
                    changeValue(true, 30000);
                }
                break;
            case R.id.quarante:
                if (checked) {
                    changeValue(true, 40000);
                }
                break;
            case R.id.cinquante:
                if (checked) {
                    changeValue(true, 50000);
                }
                break;
            case R.id.minute:
                if (checked) {
                    changeValue(true, 60000);
                }
                break;
            case R.id.minute_dix:
                if (checked) {
                    changeValue(true, 70000);
                }
                break;
            case R.id.minute_vingt:
                if (checked) {
                    changeValue(true, 80000);
                }
                break;
            case R.id.minute_trente:
                if (checked) {
                    changeValue(true, 90000);
                }
                break;
            case R.id.minute_quanrante:
                if (checked) {
                    changeValue(true, 100000);
                }
                break;
            case R.id.minute_cinquante:
                if (checked) {
                    changeValue(true, 110000);
                }
                break;
            case R.id.minutes:
                if (checked) {
                    changeValue(true, 120000);
                }
                break;
        }
    }

    // Click on "Osaekomi" RaddioButton
    public void onRadioButtonSolClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.dix:
                if (checked) {
                    changeValue(false, 10000);
                }
                break;
            case R.id.quinze:
                if (checked) {
                    changeValue(false, 15000);
                }
                break;
            case R.id.vingt:
                if (checked) {
                    changeValue(false, 20000);
                }
                break;
            case R.id.vingt_cinq:
                if (checked) {
                    changeValue(false, 25000);
                }
                break;
        }
    }

    // Change values when RaddioButton clicked
    public void changeValue(boolean test, long nbr) {
        // Play sound
        //soundPool.play(changementTemps, 1, 1, 1, 0, 1);
        if (test) {
            MainActivity.general = nbr;
            MainActivity.generalInit = nbr;
        } else {
            MainActivity.solRouge = nbr;
            MainActivity.solBlanc = nbr;
            MainActivity.solInit = nbr;
        }
    }

    // Go back
    public void returnToMain(View view) {
        // Play sound
        //soundPool.play(changementTemps, 1, 1, 1, 0, 1);

        //Permet d'afficher les nouvelles valeur sur MainActivity
        setResult(MainActivity.RESULT_CODE);

        //Fermet cette activité
        finish();
    }
}
