package com.example.project3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class GreenFragment extends Fragment {

    private OnGreenFragmemntListener mCallback;
    private EditText name, username, password, confirm;
    private Button signinBtn;
    private Button back;
    private Cursor cursor;
    private SQLiteDatabase db;
    private CheckBox pokazi;
    private DatabaseHelper dbHelper;
    private String Gender = "";
    private String perfix="";
    private Button confirmbutton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_green, container, false);

        dbHelper = new DatabaseHelper(getContext());

        name = (EditText) v.findViewById(R.id.Name2);
        password = (EditText) v.findViewById(R.id.Password2);
        confirm = (EditText) v.findViewById(R.id.Confirm2);
        username = (EditText) v.findViewById(R.id.Username2);
        signinBtn = (Button) v.findViewById(R.id.signinBtn);
        back = (Button) v.findViewById(R.id.back);
        pokazi = (CheckBox) v.findViewById(R.id.pokazi);

        pokazi.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                confirm.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                confirm.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });

        RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch(checkedId) {
                case R.id.ma:
                    Gender="Male";
                    break;
                case R.id.fe:
                    Gender="Female";
                    break;
                case R.id.ot:
                    Gender="Other";
                    break;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("") ||
                        username.getText().toString().equals("") ||
                        password.getText().toString().equals("") || confirm.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Enter the required information", Toast.LENGTH_LONG).show();
                    return;
                }

                // check if both password matches
                if (!password.getText().toString().equals(confirm.getText().toString())) {
                    Toast.makeText(getContext(), "Password does not match", Toast.LENGTH_LONG).show();
                } else {
                    dbHelper.addUser(name.getText().toString(),
                            username.getText().toString(), password.getText().toString(),
                            confirm.getText().toString());

                    Toast.makeText(getContext(), "Successful", Toast.LENGTH_LONG).show();

                    Intent inte = new Intent(getContext(), LoginActivity.class);
                    startActivity(inte);
                }
            }

            public void onBackPressed() {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        Button confirm_button = v.findViewById(R.id.confirm_button);
        confirm_button.setOnClickListener(v1 -> {
            String message = name.getText().toString();
            mCallback.messageFromGreenFragment(message, Gender);
        });
        return v;
    }

    public interface OnGreenFragmemntListener {
        void messageFromGreenFragment(String message, String Gender);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnGreenFragmemntListener){
            mCallback=(OnGreenFragmemntListener) context;
        } else{
            throw new RuntimeException(context.toString()+" must implement OnGreenFragmentListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mCallback=null;
    }

}