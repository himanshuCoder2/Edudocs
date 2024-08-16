package com.example.layoutcheck;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100); // Set the maximum progress value

        //  progress update it will run progress bar next then open new activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                updateProgress();
            }
        }, 1000); // Delay for demonstration purposes
    }

    private void updateProgress() {
        if (progressValue < 100) {
            progressValue += 10; // Increment progress value
            progressBar.setProgress(progressValue);
            // Call this method again after a delay (e.g., every second)
            new Handler().postDelayed(this::updateProgress, 1000);
        } else {
            // Progress complete, navigate to the  MainActivity class
            startActivity(new Intent(SplashScreen.this, MainActivity.class));
            finish();
        }
    }
}
