package com.example.waterwise;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        ImageButton i1=findViewById(R.id.surveyButton);
        ImageButton i2=findViewById(R.id.reportButton);
        ImageButton i4=findViewById(R.id.gamesButton);
        ImageButton i5=findViewById(R.id.forumButton);
        ImageButton i3=findViewById(R.id.triviaButton);
        ImageButton i9=findViewById(R.id.kylButton);
        ImageButton i7=findViewById(R.id.marketplaceButton);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomePage.this, survey.class);
                startActivity(i);
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomePage.this, report.class);
                startActivity(i);
            }
        });

        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomePage.this, tipsandtrivia.class);
                startActivity(i);
            }
        });

        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomePage.this, Games.class);
                startActivity(i);

            }
        });
        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomePage.this, forum.class);
                startActivity(i);

            }
        });
        i7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomePage.this, Market.class);
                startActivity(i);
            }
        });

        i9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomePage.this, waterfragment.class);
                startActivity(i);

            }
        });
    }
}