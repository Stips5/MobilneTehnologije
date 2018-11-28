package hr.stips.mobilnetehnologije.Predavanja.Predavanje3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import hr.stips.mobilnetehnologije.R;

public class Predavanje3_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p3_activity_main);

        final ImageView zvijezda = (ImageView) findViewById(R.id.imageView);
        Button stisni = (Button)findViewById(R.id.button);
        Button smisaoSinhrono = (Button)findViewById(R.id.button2);
        Button smisaoAsinhrono = (Button)findViewById(R.id.button3);

        stisni.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ((Button) v).setText("PUSTI");
                    zvijezda.setImageResource(android.R.drawable.star_big_on);
                } else if (event.getAction() == MotionEvent.ACTION_UP){
                    ((Button)v).setText("PRITISNI");
                    zvijezda.setImageResource(android.R.drawable.star_big_off);
                }



                return false;
            }

    });
        smisaoSinhrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView ispisi = (TextView)findViewById(R.id.textView);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ispisi.setText("42");
            }
        });

        smisaoAsinhrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView ispisi = (TextView)findViewById(R.id.textView);
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute(ispisi);
            }
        });


}
}
