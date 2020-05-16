package com.example.project3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class Register extends AppCompatActivity implements GreenFragment.OnGreenFragmemntListener {

    BlueFragment mBlueFragment;
    GreenFragment mGreenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mBlueFragment = (BlueFragment)
                fragmentManager.findFragmentById(R.id.blue_fragment_container);
        if (mBlueFragment == null) {
            mBlueFragment = new BlueFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.blue_fragment_container, mBlueFragment).commit();
        }

        mGreenFragment = (GreenFragment)
                fragmentManager.findFragmentById(R.id.green_fragment_container);
        if (mGreenFragment == null) {
            mGreenFragment = new GreenFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.green_fragment_container, mGreenFragment).commit();
        }
    }

    @Override
    public void messageFromGreenFragment(String message, String gender) {
        mBlueFragment.msgReceived(message, gender);
    }
}