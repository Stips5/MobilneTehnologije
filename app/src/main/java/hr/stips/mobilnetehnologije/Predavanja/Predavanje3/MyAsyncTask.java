package hr.stips.mobilnetehnologije.Predavanja.Predavanje3;

import android.os.AsyncTask;
import android.widget.TextView;

public class MyAsyncTask extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return objects[0];
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        ((TextView)o).setText("43");


    }
}
