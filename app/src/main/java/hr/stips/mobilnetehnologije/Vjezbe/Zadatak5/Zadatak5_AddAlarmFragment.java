package hr.stips.mobilnetehnologije.Vjezbe.Zadatak5;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.util.Calendar;

import hr.stips.mobilnetehnologije.R;

public class Zadatak5_AddAlarmFragment extends Fragment {

    private static final String TAG = "Zadatak5_TestFragment";
    ImageButton dodaj;
    EditText labela;
    EditText vrime;
    Calendar mcurrentTime;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mcurrentTime = Calendar.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.zadatak_5_fragment_layout, container, false);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {//ovaj event se dogadja "dovoljno kasno" pa se moze i ovdje staviti ispis u Main
        super.onResume();
        //TextView textMainActivity = (TextView)getActivity().findViewById(R.id.activityTextView);
        //textMainActivity.setText("Headakjshd!!");
    }

    @Override
    public void onStart() {
        super.onStart();

        dodaj = getView().findViewById(R.id.button_dodaj);
        labela =getView().findViewById(R.id.editText_labela);
        vrime = getView().findViewById(R.id.editText_vrime);

        vrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        vrime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Dodano " + labela.getText().toString() + vrime.getText().toString() );
                saveAlarm(getAlarmNumber(), labela.getText().toString(), vrime.getText().toString() );
                getActivity().recreate();
                setAlarm();
            }
        });
    }

    private void setAlarm() {

        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_HOUR, mcurrentTime.get(Calendar.HOUR_OF_DAY));
        intent.putExtra(AlarmClock.EXTRA_MINUTES, Calendar.MINUTE);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, labela.getText().toString());
        startActivity(intent);
//        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        Intent i= new Intent("MY_INTENT");
//        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.SECOND, 5);
//        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
//        Toast.makeText(this, "Halarm set in" + calendar.getTime(), Toast.LENGTH_SHORT).show();/
    }

    int getAlarmNumber() {

        SharedPreferences prefs = getContext().getSharedPreferences("halarm", Context.MODE_PRIVATE);
        int alarmNum = prefs.getInt("AlarmNum", 0);
        Log.d(TAG, "Num of alarm restored " + alarmNum);
        return alarmNum;
    }

    void saveAlarm(int alarmNum, String label, String time) {

        Log.d(TAG, "Alarm saved: " + label + " " + time);
        String consts[] = {"Label", "Vrime"};
        SharedPreferences prefs = getContext().getSharedPreferences("halarm", Context.MODE_PRIVATE);
        prefs.edit().putString(consts[0] + alarmNum, label).apply();
        prefs.edit().putString(consts[1] + alarmNum, time).apply();
        prefs.edit().putInt("AlarmNum", ++alarmNum).apply();
    }
}
