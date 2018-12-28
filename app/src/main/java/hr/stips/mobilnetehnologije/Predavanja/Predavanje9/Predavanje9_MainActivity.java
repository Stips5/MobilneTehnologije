package hr.stips.mobilnetehnologije.Predavanja.Predavanje9;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageButton;

import hr.stips.mobilnetehnologije.R;

public class Predavanje9_MainActivity extends AppCompatActivity {

    /*
    * animacije neke sa predavanja
    * */

    private Boolean enlarge; // bool property

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p9_activity_main);//trenutno inflate-ani
        this.enlarge = true;

        ImageButton ib = (ImageButton)findViewById(R.id.imageButton);//bitno je da se view ovi zvu isto zbog proraucuna delte za animaciju
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Predavanje9_MainActivity.this.enlarge) {
                    Predavanje9_MainActivity.this.enlarge = false;
                    animateTo(R.layout.p9_activity_main_2);
                } else {
                    Predavanje9_MainActivity.this.enlarge = true;
                    animateTo(R.layout.activity_main);//povrat je id (int)
                }
            }
        });
    }

    private void animateTo(int id) {

        ConstraintLayout constraintLayout = (ConstraintLayout)findViewById(R.id.constraintLayout);//parent unutar kojeg se animira (onaj sto ga je activity inflate-ao)
        ConstraintSet cs = new ConstraintSet();//ide se sa trenutnog stanja na stanje koje je bilo na pocetku
        cs.clone(this, id);//kloniraju se svi konstraint-ovi sa seta ciji id je posaln
        ChangeBounds transition = null;//objekt potreban za animaciju

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            transition = new ChangeBounds();
            transition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));//tip animacije 1.0 kakav ce biti "wobble efekt" (tenzija opruge)
            //transition.setInterpolator(new AccelerateDecelerateInterpolator());
            //transition.setInterpolator(new AnticipateInterpolator());
            transition.setDuration(1200);//koliko dugo ce animacija trajati u ms
            TransitionManager.beginDelayedTransition(constraintLayout, transition);//koju tranziciju radimo i u kojem view setu
            cs.applyTo(constraintLayout);
        }


        //tranzicija opisuje tip interpolacije i trajanje animacije - znaci opisuje tranziciju iz stanja a u stanje b
    }
}
