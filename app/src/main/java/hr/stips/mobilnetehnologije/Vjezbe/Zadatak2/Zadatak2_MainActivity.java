package hr.stips.mobilnetehnologije.Vjezbe.Zadatak2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import hr.stips.mobilnetehnologije.R;

public class Zadatak2_MainActivity extends AppCompatActivity {

    Spinner autaDropDown;
    Spinner modeliDropDown;
    TextView rez;
    String auto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zadatak_2_activity);

        final String auta[] = getResources().getStringArray(R.array.auta);

        autaDropDown = findViewById(R.id.auta_spinner);
        modeliDropDown = findViewById(R.id.modeli_auta_spinner);
        rez = findViewById(R.id.odabranoTextView);

        autaDropDown.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, auta));

    }

    @Override
    protected void onStart() {
        super.onStart();

        autaDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                auto  = adapterView.getItemAtPosition(i).toString();
                switch (auto){

                    case "bmw":
                    {
//                        modeliDropDown.setPadding(8, 64 + 50*i, 8, 0);
                        modeliDropDown.layout(8, 64 + 50*i, 8, 0);
                        modeliDropDown.setVisibility(View.VISIBLE);
                        modeliDropDown.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.modeli_bmw)));

                        modeliDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                rez.setText(auto + " " + adapterView.getItemAtPosition(i).toString());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        break;
                    }
                    case "kia":
                    {
//                        modeliDropDown.setPadding(0, 50*i, 0, 0);
                        modeliDropDown.layout(8, 64 + 50*i, 8, 0);
                        modeliDropDown.setVisibility(View.VISIBLE);
                        modeliDropDown.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.modeli_kia)));

                        modeliDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                rez.setText(auto + " " +adapterView.getItemAtPosition(i).toString());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        break;
                    }
                    case "volvo":
                    {
                        modeliDropDown.layout(8, 64 + 50*i, 8, 0);
//                        modeliDropDown.setPadding(0, 50*i, 0, 0);
                        modeliDropDown.setVisibility(View.VISIBLE);
                        modeliDropDown.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.modeli_volvo)));

                        modeliDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                rez.setText(auto + " " + adapterView.getItemAtPosition(i).toString());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        break;
                    } case "lexus":
                    {
                        modeliDropDown.layout(8, 64 + 50*i, 8, 0);
//                        modeliDropDown.setPadding(0, 50*i, 0, 0);
                        modeliDropDown.setVisibility(View.VISIBLE);
                        modeliDropDown.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.modeli_lexus)));

                        modeliDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                rez.setText(auto + adapterView.getItemAtPosition(i).toString());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                modeliDropDown.setVisibility(View.INVISIBLE);
            }
        });

    }
}
