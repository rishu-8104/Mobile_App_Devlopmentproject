package com.example.hellotamk2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //method "sayHello"
    public void sayHello( View view ){
        TextView helloTextView = findViewById(R.id.helloTextView);
        helloTextView.setText("Hello TAMK " + counter++);

    }
}