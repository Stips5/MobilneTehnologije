package hr.stips.mobilnetehnologije.Predavanja.Predavanje10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import hr.stips.mobilnetehnologije.R;

public class Predavanje10_MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p10_activity_main);
        mAuth = FirebaseAuth.getInstance();

        Button logout = (Button)findViewById(R.id.buttonLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent i = new Intent(Predavanje10_MainActivity.this, Predavanje10_LoginActivity.class);
                startActivity(i);
            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        TextView tv = (TextView) findViewById(R.id.status);
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            tv.setText("Hello " + currentUser.getEmail() + "!");
            //getPredavanje();

        } else {
            Intent i = new Intent(this, Predavanje10_LoginActivity.class);
            startActivity(i);
        }
    }

}
