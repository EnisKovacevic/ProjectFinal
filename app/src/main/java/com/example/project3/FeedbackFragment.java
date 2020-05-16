package com.example.project3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FeedbackFragment extends Fragment {

    public static final String FRAGMENT_TAG = "DEMO_FRAGMENT";
    private EditText text;
    private Button button;

    public FeedbackFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState){
        final View v = inflater.inflate(R.layout.fragment_demo ,container,false);

        Button button = v.findViewById(R.id.bsend);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Feedback sent successfully!", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

}