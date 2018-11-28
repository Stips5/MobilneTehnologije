package hr.stips.mobilnetehnologije.Predavanja.Predavanje1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import hr.stips.mobilnetehnologije.R;

public class Predavanje1_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p1_activity_main);
        ispisi_text();
    }

    private void ispisi_text(){
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText skupiTekst = (EditText)findViewById(R.id.editText2);
                TextView ispisiTekst = (TextView)findViewById(R.id.textView2);
                ispisiTekst.setText(skupiTekst.getText().toString());
            }
        });

    }
}
