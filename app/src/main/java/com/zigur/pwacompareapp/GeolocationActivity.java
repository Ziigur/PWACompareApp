package com.zigur.pwacompareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class GeolocationActivity extends AppCompatActivity {

    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocation);

        textView = findViewById(R.id.textGeolocationStatus);

        final long startTime = Calendar.getInstance().getTimeInMillis();

        final LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        final LocationListener locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                long millisTook = Calendar.getInstance().getTimeInMillis() - startTime;
                String msg = "Fetching Geolocation took: " + millisTook + " ms";
                msg += "\nCoordinates: " + location.getLongitude() + " " + location.getLatitude();
                textView.setText(msg);

                locationManager.removeUpdates(this);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
        }
    }
}
