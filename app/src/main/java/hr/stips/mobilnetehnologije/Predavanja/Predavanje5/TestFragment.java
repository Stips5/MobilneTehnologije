package hr.stips.mobilnetehnologije.Predavanja.Predavanje5;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import hr.stips.mobilnetehnologije.R;

public class TestFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.p5_fragment_layout, container, false);
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
        //TextView textMainActivity = (TextView)getActivity().findViewById(R.id.activityTextView);
        //textMainActivity.setText("Headakjshd!!");

        //mijenjanje teksta na main Activity-u
        EditText editText = (EditText)getView().findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                TextView textMainActivity = (TextView)getActivity().findViewById(R.id.activityTextView);
                textMainActivity.setText(s.toString());
            }
        });
    }
}
