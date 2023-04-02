package com.example.weatherapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ForecastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        Intent intent = getIntent();
        String cityName = intent.getStringExtra("CITY_NAME");
        String someThingNotThere = intent.getStringExtra("NOT_THERE");
        int howManyDays = intent.getIntExtra("HOW_MANY_DAYS",6);
        TextView headerTextview = findViewById(R.id.headerTextview);
        if (cityName != null){
            headerTextview.setText(cityName);
        }
        else{
            headerTextview.setText(R.string.LOCATION_NOT_KNOWN);
        }
    }

    public void openMainActivity(View view) {
        Intent openMain = new Intent(this, MainActivity.class);
        startActivity(openMain);
    }
}