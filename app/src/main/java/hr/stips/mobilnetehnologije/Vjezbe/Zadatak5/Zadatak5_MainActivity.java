package hr.stips.mobilnetehnologije.Vjezbe.Zadatak5;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Calendar;
import java.util.Vector;

import hr.stips.mobilnetehnologije.R;

public class Zadatak5_MainActivity extends FragmentActivity {

    private static final String TAG = "Zadatak5_MainActivity";

    private class MyAlarm{

        String label;
        String time;
        Calendar calendar;

        private MyAlarm(String label, String vrime) {
            this.label = label;
            this.time = vrime;
        }

        private MyAlarm() {
            label = "Alarm";
            time = "00:00";
        }

        public String getTime() {
            return time;
        }

        public String getLabel() {
            return label;
        }

        public Calendar getTimeAsCalender() {
            return Calendar.getInstance();
        }

        @Override
        public String toString() {
            return getLabel() + ", " + getTime();
        }
    }

    ListView alarmiListView;
    Vector<String> listaAlarma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zadatak_5_activity);

        listaAlarma = new Vector<>();
        alarmiListView = findViewById(R.id.alarmiListView);

        refreshList();
    }

    void refreshList()
    {
        Log.d(TAG, "List refreshed");
        listaAlarma = getSavedAlarms();
        alarmiListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaAlarma));
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshList();

//        setAlarm();
    }



    Vector<String> getSavedAlarms() {
        SharedPreferences prefs = this.getSharedPreferences("halarm", Context.MODE_PRIVATE);
        Vector<String> alarmi = new Vector<>();

        int n_of_alarms = prefs.getInt("AlarmNum", 0);

        Log.d(TAG, "getSavedAlarms: num of alarms saved " + n_of_alarms);

        for (int i = 0; i < n_of_alarms; i++) {
            alarmi.add(prefs.getString("Label"+i, " ") + "," + prefs.getString("Vrime"+i, " "));
        }

        return alarmi;
    }
}
