package hr.stips.mobilnetehnologije.Vjezbe.Zadatak8;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import hr.stips.mobilnetehnologije.R;

public class Zadatak8_NewActivity extends AppCompatActivity {

    private static final String TAG = "NewActivity";
    ImageView imageView;
    String url;
    Bitmap bmp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zadatak_8_activity_new);

        url = getIntent().getStringExtra("Position");

        imageView = findViewById(R.id.imageView);
        AsyncTask myAsy = new MyAsync();
        myAsy.execute();

    }

    class MyAsync extends AsyncTask {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            imageView = findViewById(R.id.imageView);
        }
        @Override
        protected Object doInBackground(Object[] objects) {
            Log.d(TAG, "doInBackground: Started");
            try {
                bmp = Glide.with(getApplicationContext())
                        .asBitmap()
                        .load(url)
                        .submit()
                        .get();

            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.d(TAG, "doInBackground: Done");
            return null;

        }

        @Override
        protected void onProgressUpdate(Object[] values) {

        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            imageView.setImageBitmap(bmp);
        }
    }


}
