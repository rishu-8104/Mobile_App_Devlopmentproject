package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText heightInput;
    private EditText weightInput;
    private Button calculateButton;
    private TextView resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heightInput = findViewById(R.id.height_input);
        weightInput = findViewById(R.id.weight_input);
        calculateButton = findViewById(R.id.calculate_button);
        resultText = findViewById(R.id.result_text);
        resultText.setText("Please enter height and weight.");
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String heightStr = heightInput.getText().toString();
                String weightStr = weightInput.getText().toString();
                if (!TextUtils.isEmpty(heightStr) && !TextUtils.isEmpty(weightStr)) {
                    double height = Double.parseDouble(heightStr) / 100;
                    double weight = Double.parseDouble(weightStr);
                    int bmi = (int) (weight / (height * height));
                    resultText.setText("BMI: " + bmi);
                } else {
                    resultText.setText("Please enter values.");
                }
            }
        })
    ;}
}