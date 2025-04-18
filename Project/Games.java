package com.example.waterwise;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Games extends AppCompatActivity {

    private VideoView videoView;
    private Button playButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        // Initialize VideoView and Button
        videoView = findViewById(R.id.videoView);
        playButton = findViewById(R.id.playButton);

        // Set the video URI from the raw folder
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.myvideo);
        videoView.setVideoURI(videoUri);

        // Play video when button is clicked
        playButton.setOnClickListener(v -> videoView.start());
    }
}
