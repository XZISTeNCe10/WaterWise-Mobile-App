package com.example.waterwise;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;

public class forum extends AppCompatActivity {

    private LinearLayout forumContainer;
    private EditText etInput;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        forumContainer = findViewById(R.id.forumContainer);
        etInput = findViewById(R.id.etInput);
        Button btnPost = findViewById(R.id.btnPost);

        // Post button click listener
        btnPost.setOnClickListener(v -> {
            String userInput = etInput.getText().toString().trim();
            if (!userInput.isEmpty()) {
                addPost(userInput);
                etInput.setText(""); // Clear input field
            } else {
                Toast.makeText(forum.this, "Please write something!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addPost(String postContent) {
        // Create a new post container
        LinearLayout postLayout = new LinearLayout(this);
        postLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        postLayout.setOrientation(LinearLayout.VERTICAL);
        postLayout.setPadding(8, 8, 8, 8);
        postLayout.setBackgroundResource(R.drawable.post_background);

        // Add the user's post as a TextView
        TextView tvPost = new TextView(this);
        tvPost.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        tvPost.setText(postContent);
        tvPost.setTextSize(16);
        tvPost.setTextColor(getResources().getColor(android.R.color.black));

        // Create a horizontal layout for Like and Reply buttons
        LinearLayout buttonLayout = new LinearLayout(this);
        buttonLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonLayout.setPadding(0, 8, 0, 0);

        // Add a Like button
        Button btnLike = new Button(this);
        btnLike.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        btnLike.setText("Like");
        btnLike.setOnClickListener(v -> {
            Toast.makeText(forum.this, "Liked!", Toast.LENGTH_SHORT).show();
        });

        // Add a Reply button
        Button btnReply = new Button(this);
        btnReply.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        btnReply.setText("Reply");
        btnReply.setOnClickListener(v -> {
            Toast.makeText(forum.this, "Replied!", Toast.LENGTH_SHORT).show();
        });

        // Add buttons to the button layout
        buttonLayout.addView(btnLike);
        buttonLayout.addView(btnReply);

        // Add components to the post layout
        postLayout.addView(tvPost);
        postLayout.addView(buttonLayout);

        // Add the post layout to the forum container
        forumContainer.addView(postLayout);
    }
}