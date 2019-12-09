package niveau.top.judo.jtn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

public class Parameters extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);
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
        //Permet d'afficher les nouvelles valeur sur MainActivity
        setResult(MainActivity.RESULT_CODE);

        //Fermet cette activité
        finish();
    }
}
