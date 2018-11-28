package hr.stips.mobilnetehnologije;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Vector;

import hr.stips.mobilnetehnologije.Predavanja.Predavanje1.Predavanje1_MainActivity;
import hr.stips.mobilnetehnologije.Predavanja.Predavanje2.Predavanje2_MainActivity;
import hr.stips.mobilnetehnologije.Predavanja.Predavanje3.Predavanje3_MainActivity;
import hr.stips.mobilnetehnologije.Predavanja.Predavanje4.Predavanje4_MainActivity;
import hr.stips.mobilnetehnologije.Predavanja.Predavanje5.Predavanje5_MainActivity;
import hr.stips.mobilnetehnologije.Vjezbe.Zadatak1.Zadatak1_MainActivity;
import hr.stips.mobilnetehnologije.Vjezbe.Zadatak2.Zadatak2_MainActivity;
import hr.stips.mobilnetehnologije.Vjezbe.Zadatak3.Zadatak3_MainActivity;
import hr.stips.mobilnetehnologije.Vjezbe.Zadatak4.Zadatak4_MainActivity;
import hr.stips.mobilnetehnologije.Vjezbe.Zadatak5.Zadatak5_MainActivity;

public class MainActivity extends AppCompatActivity{

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

        intentoviPredavanja.add(new Intent(getApplicationContext(), Predavanje1_MainActivity.class));
        intentoviPredavanja.add(new Intent(getApplicationContext(), Predavanje2_MainActivity.class));
        intentoviPredavanja.add(new Intent(getApplicationContext(), Predavanje3_MainActivity.class));
        intentoviPredavanja.add(new Intent(getApplicationContext(), Predavanje4_MainActivity.class));
        intentoviPredavanja.add(new Intent(getApplicationContext(), Predavanje5_MainActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();

        vjezbeLW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: ");
                startActivity(intentoviVjezbi.get(i));
            }
        });

        predavanjaLW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: ");
                startActivity(intentoviPredavanja.get(i));
            }
        });

    }
}