package com.example.activelifetracker;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ImageView imageView = findViewById(R.id.iv_iconlogin);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.heart_beat_animation);
        imageView.startAnimation(animation);
    }
}