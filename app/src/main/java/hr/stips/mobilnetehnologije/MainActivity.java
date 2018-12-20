package hr.stips.mobilnetehnologije;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Vector;

import hr.stips.mobilnetehnologije.Predavanja.Predavanje1.Predavanje1_MainActivity;
import hr.stips.mobilnetehnologije.Predavanja.Predavanje2.Predavanje2_MainActivity;
import hr.stips.mobilnetehnologije.Predavanja.Predavanje3.Predavanje3_MainActivity;
import hr.stips.mobilnetehnologije.Predavanja.Predavanje4.Predavanje4_MainActivity;
import hr.stips.mobilnetehnologije.Predavanja.Predavanje5.Predavanje5_MainActivity;
import hr.stips.mobilnetehnologije.Predavanja.Predavanje6.Predavanje6_MainActivity;
import hr.stips.mobilnetehnologije.Predavanja.Predavanje7.Predavanje7_MainActivity;
import hr.stips.mobilnetehnologije.Predavanja.Predavanje8.Predavanje8_MainActivity;
import hr.stips.mobilnetehnologije.Predavanja.Predavanje9.Predavanje9_MainActivity;
import hr.stips.mobilnetehnologije.Vjezbe.Zadatak1.Zadatak1_MainActivity;
import hr.stips.mobilnetehnologije.Vjezbe.Zadatak2.Zadatak2_MainActivity;
import hr.stips.mobilnetehnologije.Vjezbe.Zadatak3.Zadatak3_MainActivity;
import hr.stips.mobilnetehnologije.Vjezbe.Zadatak4.Zadatak4_MainActivity;
import hr.stips.mobilnetehnologije.Vjezbe.Zadatak5.Zadatak5_MainActivity;
import hr.stips.mobilnetehnologije.Vjezbe.Zadatak6.Zadatak6_MainActivity;
import hr.stips.mobilnetehnologije.Vjezbe.Zadatak7.Zadatak7_MainActivity;
import hr.stips.mobilnetehnologije.Vjezbe.Zadatak8.Zadatak8_MainActivity;
import hr.stips.mobilnetehnologije.Vjezbe.Zadatak9.Zadatak9_MainActivity;

public class MainActivity extends AppCompatActivity{

    //TODO Vjezbe zadatak 7 i zadatak 9
    //TODO remapirat vjezbe i predavanja

    private static final String TAG = "MainActivity";
    ListView vjezbeLW;
    ListView predavanjaLW;
    Vector<Intent> intentoviVjezbi = new Vector<>();
    Vector<Intent> intentoviPredavanja = new Vector<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] homeworks = getResources().getStringArray(R.array.zadatci);
        String[] predavanja = getResources().getStringArray(R.array.predavanja);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,homeworks);
        vjezbeLW = findViewById(R.id.listViewVjezbi);
        vjezbeLW.setAdapter(adapter);


        ArrayAdapter<String> adapterZ = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,predavanja);
        predavanjaLW = findViewById(R.id.listViewPredavanja);
        predavanjaLW.setAdapter(adapterZ);

        intentoviVjezbi.add(new Intent(getApplicationContext(), Zadatak1_MainActivity.class));
        intentoviVjezbi.add(new Intent(getApplicationContext(), Zadatak2_MainActivity.class));
        intentoviVjezbi.add(new Intent(getApplicationContext(), Zadatak3_MainActivity.class));
        intentoviVjezbi.add(new Intent(getApplicationContext(), Zadatak4_MainActivity.class));
        intentoviVjezbi.add(new Intent(getApplicationContext(), Zadatak5_MainActivity.class));
        intentoviVjezbi.add(new Intent(getApplicationContext(), Zadatak6_MainActivity.class));
        intentoviVjezbi.add(new Intent(getApplicationContext(), Zadatak7_MainActivity.class));
        intentoviVjezbi.add(new Intent(getApplicationContext(), Zadatak8_MainActivity.class));
        intentoviVjezbi.add(new Intent(getApplicationContext(), Zadatak9_MainActivity.class));

        intentoviPredavanja.add(new Intent(getApplicationContext(), Predavanje1_MainActivity.class));
        intentoviPredavanja.add(new Intent(getApplicationContext(), Predavanje2_MainActivity.class));
        intentoviPredavanja.add(new Intent(getApplicationContext(), Predavanje3_MainActivity.class));
        intentoviPredavanja.add(new Intent(getApplicationContext(), Predavanje4_MainActivity.class));
        intentoviPredavanja.add(new Intent(getApplicationContext(), Predavanje5_MainActivity.class));
        intentoviPredavanja.add(new Intent(getApplicationContext(), Predavanje6_MainActivity.class));
        intentoviPredavanja.add(new Intent(getApplicationContext(), Predavanje7_MainActivity.class));
        intentoviPredavanja.add(new Intent(getApplicationContext(), Predavanje8_MainActivity.class));
        intentoviPredavanja.add(new Intent(getApplicationContext(), Predavanje9_MainActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();

        vjezbeLW.setOnItemClickListener((adapterView, view, i, l) -> {
            Log.d(TAG, "onItemClick: ");
            startActivity(intentoviVjezbi.get(i));
        });

        predavanjaLW.setOnItemClickListener((adapterView, view, i, l) -> {
            Log.d(TAG, "onItemClick: ");
            startActivity(intentoviPredavanja.get(i));
        });

    }
}