package com.example.finalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements LocationListener {


    private double latitude, longitude;
    boolean switchStatus = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Switch switchGPS = (Switch) findViewById(R.id.switchGPS);
        switchGPS.setChecked(switchStatus);

        //check switch
        if (switchGPS.isChecked()) {
            LocationManager locationMaganer = (LocationManager) getSystemService(LOCATION_SERVICE);

            // Checks user  permission
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "No permission!", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        0
                );

                return;
            }


            //location updates
            try {
                locationMaganer.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            } catch (Exception e) {
                Log.d("Location problem", e.toString());
            }

        }

    }

    //activity to show Currency exchange rates
    public void openRatesActivity (View view) {
        //creating an intent
        Intent intent = new Intent(this, RatesActivity.class);
        startActivity(intent);
    }

public void showMapActivity(View view) {

    Switch switchGPS = findViewById(R.id.switchGPS);

    // checks gps
    if (switchGPS.isChecked()) {
        // create a Uri to open the Google Maps app with a search for nearby currency exchange agencies
        String uri = "geo:" + latitude + "," + longitude + "?q=currency+exchange+agencies+near+me";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    } else {
        Toast.makeText(this, "Please enable GPS to show nearby currency exchange agencies", Toast.LENGTH_SHORT).show();
    }
}

    //updating gps location
    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

    }


    public void startGPS (View view) {


        Switch switchGPS = (Switch) findViewById(R.id.switchGPS);
        switchStatus = true;

        if (switchGPS.isChecked()) {
            LocationManager locationMaganer = (LocationManager) getSystemService(LOCATION_SERVICE);


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //we don't have permission so ask it here:
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        0
                );

                    return;
            }
            switchStatus = true;


            try {
                locationMaganer.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            } catch (Exception e) {
                Log.d("Location problem", e.toString());
            }

        }
        else switchStatus = false;

    }

    @Override
    public void onResume(){
        super.onResume();
        //setting the GPS switch state
        Switch switchGPS = (Switch) findViewById(R.id.switchGPS);
        switchGPS.setChecked(switchStatus);
    }
}