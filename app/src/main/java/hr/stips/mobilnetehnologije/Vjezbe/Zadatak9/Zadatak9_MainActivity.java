package hr.stips.mobilnetehnologije.Vjezbe.Zadatak9;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hr.stips.mobilnetehnologije.R;

public class Zadatak9_MainActivity extends AppCompatActivity {

//    animacije

    ImageButton imageButton;
    ConstraintLayout clNaslov;
    RelativeLayout rlDescription;
    TextView tapForInfo;
    TextView descriptionTextView;
    RelativeLayout relativeLayout;
    boolean descriptionIn = false;
    int duration = 900;
    private static final String TAG = "Zadatak9_MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zadatak_9_activity);

        imageButton = findViewById(R.id.imageView);
        tapForInfo = findViewById(R.id.tapToInfoTextView);
        descriptionTextView = findViewById(R.id.opisTextView);
        clNaslov = findViewById(R.id.naslovConstrainLayout);
        relativeLayout = findViewById(R.id.relativeLayout);
        rlDescription = findViewById(R.id.descriptionRelativeLayout);

        clNaslov.setVisibility(View.GONE);
        descriptionTextView.setVisibility(View.GONE);
    }


    void animateSlideIn()
    {
        //slide title and description to view

        TranslateAnimation slideDescription = new TranslateAnimation(
                0,
                0,
                0,
                descriptionTextView.getHeight());
        slideDescription.setDuration(duration);
        slideDescription.setFillEnabled(true);
        slideDescription.setFillAfter(true);
        slideDescription.setInterpolator(new OvershootInterpolator());

        TranslateAnimation slideTitle = new TranslateAnimation(
                0,
                -clNaslov.getWidth(),
                0,
                0
        );
        slideTitle.setDuration(duration);
        slideTitle.setFillEnabled(true);
        slideTitle.setFillAfter(true);
        slideTitle.setInterpolator(new OvershootInterpolator());

        ScaleAnimation imageAnimation = new ScaleAnimation(
                1.1f,
                1,
                1.1f,
                1
        );
        imageAnimation.setDuration(250);
        imageAnimation.setFillEnabled(true);
        imageAnimation.setFillAfter(true);

        rlDescription.startAnimation(slideDescription);
        clNaslov.startAnimation(slideTitle);
        imageButton.startAnimation(imageAnimation);

        tapForInfo.setVisibility(View.VISIBLE);
        clNaslov.setVisibility(View.VISIBLE);
        descriptionTextView.setVisibility(View.VISIBLE);

        Log.d(TAG, "animateSlideIn: Animation");
    }

    void animateSlideOut()
    {
        //slide title and description off view

        TranslateAnimation slideDescription = new TranslateAnimation(
                0,
                0,
                descriptionTextView.getHeight(),
                0);
        slideDescription.setDuration(duration);
        slideDescription.setFillEnabled(true);
        slideDescription.setFillAfter(true);
        slideDescription.setInterpolator(new OvershootInterpolator());

        TranslateAnimation slideTitle = new TranslateAnimation(
                -clNaslov.getWidth(),
                0,
                0,
                0
        );
        slideTitle.setDuration(duration);
        slideTitle.setFillEnabled(true);
        slideTitle.setFillAfter(true);
        slideTitle.setInterpolator(new OvershootInterpolator());

        ScaleAnimation imageAnimation = new ScaleAnimation(
                1,
                1.1f,
                1,
                1.1f
        );
        imageAnimation.setDuration(250);
        imageAnimation.setFillEnabled(true);
        imageAnimation.setFillAfter(true);

        rlDescription.startAnimation(slideDescription);
        clNaslov.startAnimation(slideTitle);
        imageButton.startAnimation(imageAnimation);

        tapForInfo.setVisibility(View.GONE);
        clNaslov.setVisibility(View.VISIBLE);
        descriptionTextView.setVisibility(View.VISIBLE);

        Log.d(TAG, "animateSlideOut: Animation");
    }


    @Override
    protected void onStart() {
        super.onStart();

//        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,0.0f, 0);
//        animation.setDuration(3000);
//        animation.setFillAfter(true);
//
//        animation.start();  // start animation

        imageButton.setOnClickListener(v -> {
            if (descriptionIn)
            {
                animateSlideOut();
                descriptionIn = false;
            }
            else
            {
                animateSlideIn();
                descriptionIn = true;
            }
        });


    }
}
