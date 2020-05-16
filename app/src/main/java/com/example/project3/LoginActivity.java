package com.example.project3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button login;
    Button register;
    Cursor cursor;
    CheckBox pokazi;
    DatabaseHelper dbhelp;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbhelp = new DatabaseHelper(this);
        db = dbhelp.getReadableDatabase();
        username = findViewById(R.id.Username2);
        password = findViewById(R.id.Password2);
        final Cursor[] cursor = new Cursor[1];
        login = findViewById(R.id.button);
        register = (Button) findViewById(R.id.regis);
        pokazi = (CheckBox) findViewById(R.id.pokazi);

        pokazi.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });

        register.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, Register.class);
            startActivity(intent);
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cursor[0] = db.rawQuery("SELECT * FROM " + DatabaseHelper.USER_TABLE + " WHERE "
                                + DatabaseHelper.COLUMN_USERNAME + " =? AND " + DatabaseHelper.COLUMN_PASSWORD + " =?",
                        new String[]{username.getText().toString(), password.getText().toString()});

                if (username.getText().toString().equals("") ||
                        password.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Username and Password can't be empty", Toast.LENGTH_LONG).show();
                    return;
                }

                if (cursor[0] != null) {
                    if (cursor[0].getCount() > 0) {
                        cursor[0].moveToFirst();

                        Toast.makeText(LoginActivity.this, "Success!",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid username or password!",
                                Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
}
