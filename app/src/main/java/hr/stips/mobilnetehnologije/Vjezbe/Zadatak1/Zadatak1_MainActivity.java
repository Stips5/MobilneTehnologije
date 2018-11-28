package hr.stips.mobilnetehnologije.Vjezbe.Zadatak1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import hr.stips.mobilnetehnologije.R;

public class Zadatak1_MainActivity extends AppCompatActivity {

    Button button;
    EditText editTexts[] = new EditText[2];
    String listaOperatoraS[];
    Spinner operatoriDropDown;
    TextView rez;
    char operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zadatak_1_activity);

        listaOperatoraS = getResources().getStringArray(R.array.operatori);

        button = findViewById(R.id.button);
        editTexts[0] = findViewById(R.id.br1);
        editTexts[1] = findViewById(R.id.br2);
        operatoriDropDown = findViewById(R.id.lista);
        rez = findViewById(R.id.rez);

        operatoriDropDown.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, listaOperatoraS));

    }

    @Override
    protected void onStart() {
        super.onStart();


//        operatoriDropDown.setSelection(2);

        operatoriDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                operator = listaOperatoraS[i].charAt(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int br1 = Integer.valueOf(editTexts[0].getText().toString());
                int br2 = Integer.valueOf(editTexts[1].getText().toString());

                switch (operator)
                {
                    case '+':
                    {
                        rez.setText(String.valueOf(br1 + br2));
                        break;
                    }
                    case '-':
                    {
                        rez.setText(String.valueOf(br1 - br2));
                        break;
                    }
                    case '*':
                    {
                        rez.setText(String.valueOf(br1 * br2));
                        break;
                    }
                    case '/':
                    {
                        rez.setText(String.valueOf(br1 / br2));
                        break;
                    }
                    default:
                        break;
                }
            }
        });

    }

}
