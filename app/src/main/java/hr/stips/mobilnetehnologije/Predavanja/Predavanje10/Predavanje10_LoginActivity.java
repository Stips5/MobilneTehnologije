package hr.stips.mobilnetehnologije.Predavanja.Predavanje10;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import hr.stips.mobilnetehnologije.R;

public class Predavanje10_LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p10_activity_login);

        mAuth = FirebaseAuth.getInstance();

        Button login = (Button)findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ((EditText)findViewById(R.id.email)).getText().toString();
                String password = ((EditText)findViewById(R.id.password)).getText().toString();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Predavanje10_LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null) {
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Predavanje10_LoginActivity.this, "Authentication success but no user.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Predavanje10_LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
