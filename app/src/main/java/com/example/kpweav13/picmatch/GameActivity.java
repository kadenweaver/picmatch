package com.example.kpweav13.picmatch;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TextView counter = (TextView)findViewById(R.id.clicks);
        Button menu = (Button)findViewById(R.id.menu);

        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent s = new Intent(GameActivity.this,MainActivity.class);
                startActivity(s);
            }
        });

    }


    public void addClick(int i){
        TextView counter = (TextView)findViewById(R.id.clicks);
        counter.setText("Clicks: " + ++i);
    }

    public void setClick(int i){
        TextView counter = (TextView)findViewById(R.id.clicks);
        counter.setText("Clicks: " + i);
    }

    public void startThinking() {
        View thinkView = findViewById(R.id.thinking);
        thinkView.setVisibility(View.VISIBLE);
    }

    public void stopThinking() {
        View thinkView = findViewById(R.id.thinking);
        thinkView.setVisibility(View.GONE);

    }

    public void asknewgame(){
        final View ngview = findViewById(R.id.ngame);
        Button yes = (Button)findViewById(R.id.yes);
        Button no = (Button)findViewById(R.id.no);
        ngview.setVisibility(View.VISIBLE);
        yes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                ngview.setVisibility(View.GONE);
                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ngview.setVisibility(View.GONE);
                Intent s = new Intent(GameActivity.this,MainActivity.class);
                startActivity(s);
            }
        });


    }
}