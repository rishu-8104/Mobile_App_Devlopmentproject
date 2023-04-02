package com.example.guilayout;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            boolean isYellow = false;
            @Override
            public void onClick(View view) {
                if (isYellow) {
                    button1.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
                } else {
                    button1.setBackgroundColor(Color.YELLOW);
                }
                isYellow = !isYellow;
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            boolean isYellow = false;
            @Override
            public void onClick(View view) {
                if (isYellow) {
                    button2.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
                } else {
                    button2.setBackgroundColor(Color.YELLOW);
                }
                isYellow = !isYellow;
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            boolean isYellow = false;
            @Override
            public void onClick(View view) {
                if (isYellow) {
                    button3.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
                } else {
                    button3.setBackgroundColor(Color.YELLOW);
                }
                isYellow = !isYellow;
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            boolean isYellow = false;
            @Override
            public void onClick(View view) {
                if (isYellow) {
                    button4.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
                } else {
                    button4.setBackgroundColor(Color.YELLOW);
                }
                isYellow = !isYellow;
            }
        });
    }



}
