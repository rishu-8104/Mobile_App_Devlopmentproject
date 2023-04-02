package com.example.weatherapp2023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;
    private String description = "Click to refresh";
    private double temperature = 0;
    private double windSpeed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
    }

    protected void onStart() {
        super.onStart(); // Activity is about to become visible.
        Log.d("MY_APP", "Main_Activity: onStartCalled");
    }

    protected void onResume() {
        super.onResume(); // Activity has become visible
    }
    protected void onPause() {
        super.onPause(); // Another activity is taking focus
    }
    protected void onStop() {
        super.onStop(); // Activity is no longer visible
    }
    protected void onDestroy() {
        super.onDestroy(); // The activity is about to be destroyed.
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("WEATHER_DESCRIPTION", description);
        outState.putDouble("TEMPERATURE", temperature);
        outState.putDouble("WIND", windSpeed);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        description = savedInstanceState.getString("WEATHER_DESCRIPTION");
        if (description == null) {
            description = "Click to refresh";
        }
        temperature = savedInstanceState.getDouble("TEMPERATURE", 20);
        windSpeed = savedInstanceState.getDouble("WIND", 0);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(description);
        TextView temperatureTextView = findViewById(R.id.temperatureTextView);
        temperatureTextView.setText(" " + temperature + "C");
        TextView windTextView = findViewById(R.id.windTextView);
        windTextView.setText(" " + windSpeed + "m/s");
    }

    public void showForecastActivity(View view) {
        Intent openForecast = new Intent(this, ForecastActivity.class);
        openForecast.putExtra("CITY_NAME", "Tampere");
        openForecast.putExtra("HOW_MANY_DAYS", 5);
        startActivity(openForecast);
    }

    public void refreshData(View view) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=tampere&units=metric&appid=6c433438776b5be4ac86001dc88de74d";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Log.d("WEATHER_APP", response);
                    parseJsonandUpdateUI(response);
                }, error -> {
            Log.d("WEATHER_APP", error.toString());
        });
        queue.add(stringRequest);
    }

    private void parseJsonandUpdateUI(String response) {
        try {
            JSONObject weatherResponse = new JSONObject(response);
            description = weatherResponse.getJSONArray("weather").getJSONObject(0).getString("description");
            temperature = weatherResponse.getJSONObject("main").getDouble("temp");
            windSpeed = weatherResponse.getJSONObject("wind").getDouble("speed");
            TextView descriptionTextView = findViewById(R.id.descriptionTextView);
            descriptionTextView.setText(description);
            TextView temperatureTextView = findViewById(R.id.temperatureTextView);
            temperatureTextView.setText(" " + temperature + "C");
            TextView windTextView = findViewById(R.id.windTextView);
            windTextView.setText(" " + windSpeed + "m/s");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void openWebPage(View view) {
        String urlString = "https://www.tuni.fi";
        Uri uri = Uri.parse(urlString);
        Intent openWebPage = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(openWebPage);
        } catch (ActivityNotFoundException e) {

        }
    }

    public void setAlarm(View view) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, "Timeout!")
                .putExtra(AlarmClock.EXTRA_LENGTH, 10)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
        }
    }

    public void openMaps(View view) {
        Uri location = Uri.parse("geo:27.1750,78.0422?q=Taj+Mahal");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        try {
            startActivity(mapIntent);
        } catch (ActivityNotFoundException e) {
        }
    }
}