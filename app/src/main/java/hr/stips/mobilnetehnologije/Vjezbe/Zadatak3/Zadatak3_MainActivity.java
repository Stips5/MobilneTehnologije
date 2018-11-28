package hr.stips.mobilnetehnologije.Vjezbe.Zadatak3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import hr.stips.mobilnetehnologije.R;

public class Zadatak3_MainActivity extends AppCompatActivity {

    Button startuj;
    ProgressBar pb;
    TextView tw;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zadatak_3_activity);

        Log.d(TAG, "onCreate: ");
        startuj = findViewById(R.id.startBut);
    }

    @Override
    protected void onStart() {
        super.onStart();

        startuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressBar pb = findViewById(R.id.progresBar);
                pb.setProgress(0);
                Toast.makeText(Zadatak3_MainActivity.this, "POkrenuto", Toast.LENGTH_SHORT).show();
                MyAsync say = new MyAsync();
                say.execute(pb);
            }
        });
    }

    private static final String TAG = "Zadatak3";

    class MyAsync extends AsyncTask {

        int prog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            prog = 0;
            pb = findViewById(R.id.progresBar);
            tw = findViewById(R.id.progresStateTW);
            startuj = findViewById(R.id.startBut);
        }
        @Override
        protected Object doInBackground(Object[] objects) {
            pb.setVisibility(View.VISIBLE);
            Log.d(TAG, "doInBackground: started");
            try {
                for (int i = 0; i <= pb.getMax(); i++, prog++)
                {
                    Log.d(TAG, "doInBackground: " + prog);
                    publishProgress(prog);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onProgressUpdate(Object[] values) {

            startuj.setEnabled(false);
            pb.setProgress(prog);
            tw.setText(String.valueOf(prog) + "%");
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            tw.setText("Svrseno je");
            startuj.setEnabled(true);
            pb.setVisibility(View.INVISIBLE);
        }
    }

    //    Napraviti AsyncTask koji će obavljati proizvoljnu operaciju (upotrijebite maštu :)).
//    U fuknciji onPreExecute() koristite ProgressBar view (s
//    indeterminate opcijom). U funkciji onPostExecute(Result) ukloniti ili
//    sakriti progress bar, a rezultat operacije ispisati.
//    Dodatak (za one koji žele više ). Rješiti zadatak sa ProgressBar-om koji
//    nije indeterminate.


}


