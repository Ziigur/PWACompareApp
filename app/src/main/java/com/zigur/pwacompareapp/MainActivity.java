package com.zigur.pwacompareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.zigur.pwacompareapp.MESSAGE";

    private int REQUEST_GEOLOCATION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == REQUEST_GEOLOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                moveToGeolocationPage();
            }
        }
    }

    public void testGeolocation(View view) {

        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            moveToGeolocationPage();
        } else {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, REQUEST_GEOLOCATION);
        }



    }

    private void moveToGeolocationPage() {
        Intent intent = new Intent(this, GeolocationActivity.class);
        startActivity(intent);
    }
}
