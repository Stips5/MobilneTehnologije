package hr.stips.mobilnetehnologije.Predavanja.Predavanje7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hr.stips.mobilnetehnologije.R;

public class Predavanje7_MainActivity extends AppCompatActivity {

    /*
    * sa predavanja, broadcast na klik tipke
    * */

    private BroadcastReceiver br, local_br;
    private LocalBroadcastManager localBroadcaster;
    private TextView text;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p7_activity_main);

        this.text = (TextView) findViewById(R.id.textView);
        this.editText = (EditText) findViewById(R.id.editText);
        this.button = (Button)findViewById(R.id.button);

        this.localBroadcaster = LocalBroadcastManager.getInstance(this);

        local_br = new BroadcastReceiver() {
            @Override
            public void onReceive( Context context, Intent intent ) {
                String data = intent.getStringExtra("TEXT");
                Predavanje7_MainActivity.this.text.setText(data);
            }
        };

        localBroadcaster.registerReceiver(local_br, new IntentFilter("MOJ_BROADCAST"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent localIntent = new Intent("MOJ_BROADCAST");
                localIntent.putExtra("TEXT", Predavanje7_MainActivity.this.editText.getText().toString());
                Predavanje7_MainActivity.this.localBroadcaster.sendBroadcast(localIntent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case Intent.ACTION_POWER_CONNECTED:
                        Toast.makeText(context, "CHARGER CONNECTED!", Toast.LENGTH_LONG).show();
                        break;
                    case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                        Toast.makeText(context, "AIRPLANE MODE DISCONECTED!", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);

        this.registerReceiver(br, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(br);
    }
}
