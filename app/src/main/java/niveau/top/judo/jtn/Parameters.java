package niveau.top.judo.jtn;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class Parameters extends AppCompatActivity {

    private SoundPool soundPool;
    private int changementTemps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);

        soundPool =  new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
        changementTemps = soundPool.load(this, R.raw.time,1);
    }

    // Actions quand on clique sur le temps général
    public void onRadioButtonGeneralOnclick(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
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
                if (checked){
                    changeValue(true, 80000);
                }
                break;
            case R.id.minute_trente:
                if (checked){
                    changeValue(true, 90000);
                }
                break;
            case R.id.minute_quanrante:
                if (checked){
                    changeValue(true, 100000);
                }
                break;
            case R.id.minute_cinquante:
                if (checked){
                    changeValue(true, 110000);
                }
                break;
            case R.id.minutes:
                if (checked){
                    changeValue(true, 120000);
                }
                break;
        }
    }

    // Actions quand on clique sur le temps d'immobilisation
    public void onRadioButtonSolClicked(View view){
        boolean checked =  ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.dix:
                if (checked){
                    changeValue(false, 10000);
                }
                break;
            case R.id.quinze:
                if (checked){
                    changeValue(false, 15000);
                }
                break;
            case R.id.vingt:
                if (checked) {
                    changeValue(false, 20000);
                }
                break;
        }
    }

    // Changer les valeurs quand on clique sur le boutons de temps
    public void changeValue(boolean test, long nbr){
        // Jouer le son
        soundPool.play(changementTemps, 1, 1, 1, 0, 1);
        if (test) {
            MainActivity.general = nbr;
            MainActivity.generalInit = nbr;
        } else {
            MainActivity.sol = nbr;
            MainActivity.solInit = nbr;
        }
    }

    public void returnToMain(View view) {
        // Jouer le son
        soundPool.play(changementTemps, 1, 1, 1, 0, 1);

        //Permet d'afficher les nouvelles valeur sur MainActivity
        setResult(1);

        //Fermet cette activité
        finish();
    }
}
