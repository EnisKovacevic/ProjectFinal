package com.example.project3;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.project3.R.layout.songlyricsview;

public class LyricsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public static final String FRAGMENT_TAG = "LYRICS_FRAGMENT";
    static String a = " ";
    static String s = " ";
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);

        a = DetailActivity.t;
        s = DetailActivity.f;

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.lyrics.ovh/v1/").addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        String addition = a + '/' + s + "/";
        Call<Lyrics> call = jsonPlaceHolderApi.getLyrics(a, s);

        call.enqueue(new Callback<Lyrics>() {
            @Override
            public void onResponse(Call<Lyrics> call, Response<Lyrics> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                Lyrics list = response.body();
                assert list != null;
                String content = " ";
                content += list.getLyrics() + "\n";
                textViewResult.append(content);
            }

            @Override
            public void onFailure(Call<Lyrics> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
