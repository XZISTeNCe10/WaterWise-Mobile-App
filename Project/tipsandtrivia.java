package com.example.waterwise;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.GenerationConfig;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class tipsandtrivia extends AppCompatActivity {
    private Button sendButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tipsandtrivia);

        sendButton=findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model();
            }
        });
    }

    public void model() {
        GenerationConfig.Builder configBuilder = new GenerationConfig.Builder();
        configBuilder.responseMimeType = "application/json";
        GenerationConfig generationConfig = configBuilder.build();

        GenerativeModel gm = new GenerativeModel(
                "gemini-1.5-flash", "AIzaSyAHiGWpoAni-m-uLXqSxRxRZCoXAcm8WII");

        GenerativeModelFutures model = GenerativeModelFutures.from(gm);
        EditText e1 = findViewById(R.id.chatInput);
        Content content = new Content.Builder()
                .addText(e1.getText().toString())
                .build();

        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
        Executor executor = Executors.newSingleThreadExecutor();

        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                runOnUiThread(() -> {
                    String resultText = result.getText();
                    TextView responseTextView = findViewById(R.id.responseText);
                    responseTextView.setText(resultText);
                    Log.d("MainActivity", "Response: " + resultText);
                });
            }

            @Override
            public void onFailure(Throwable t) {
                runOnUiThread(() -> {
                    Toast.makeText(tipsandtrivia.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("MainActivity", "Error: " + t.getMessage());
                });
            }
        }, executor);
    }
}