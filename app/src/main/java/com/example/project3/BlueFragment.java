package com.example.project3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class BlueFragment extends Fragment {

    private TextView mMsgReciever;
    private TextView mGender;

    public void msgReceived(String message, String gender){
        mMsgReciever.setText("Your name is: " +message + " and you are of a/an" +  gender);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_blue, container, false);
        mMsgReciever=  v.findViewById(R.id.Receiver);
        return v;
    }
}


