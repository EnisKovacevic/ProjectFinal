package com.example.project3;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class DetailActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tvtitle, tvsong;
    private ImageView img;
    static String t = " ";
    static String f = " ";
    private Button bConfirm;
    private OnDetailActivityListener mCallback;
    private Button bSubmit;
    private Button bDemos;
    private DrawerLayout drawerLayout;

    public DetailActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvtitle = (TextView) findViewById(R.id.tit);
        tvsong = (TextView) findViewById(R.id.son);
        img = (ImageView) findViewById(R.id.thumb);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.drawer_nav);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String SongTitle = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Thumbnail");

        tvtitle.setText(Title);
        tvsong.setText(SongTitle);
        img.setImageResource(image);

        Button but = findViewById(R.id.bSubmit);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, LyricsActivity.class);
                startActivity(intent);
            }
        });

        Button bb= findViewById(R.id.bconfirm);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t = tvtitle.getText().toString();
                f = tvsong.getText().toString();
            }
        });

        Button bdemo = findViewById(R.id.bdemos);
        bdemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = new Intent(DetailActivity.this, Demo.class);
                startActivity(d);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Feedback:
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new FeedbackFragment()).commit();
            break;
            case R.id.about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public interface OnDetailActivityListener{
        void messageFromOnDetailActivityListener(String artist, String songtitle);
    }
}