package hr.stips.mobilnetehnologije.Predavanja.Predavanje2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import hr.stips.mobilnetehnologije.R;

public class Predavanje2_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p2_activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> auti = ArrayAdapter.createFromResource(this, R.array.auta, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(auti);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ispisi(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void ispisi(String opcija){
        TextView tekst = (TextView) findViewById(R.id.textView);
        tekst.setText(opcija);
    }
}
