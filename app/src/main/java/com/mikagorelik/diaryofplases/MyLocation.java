package com.mikagorelik.diaryofplases;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MyLocation extends BaseActivity {

    public static final String EXTRA_GPS_DATA = "com.mikagorelik.diaryofplases.EXTRA_GPS_DATA";

    private FusedLocationProviderClient fusedLocationClient;

    private final ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your app.
                    getCurrentLocation();
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    Toast.makeText(this, "Location permission denied. Cannot get location.", Toast.LENGTH_LONG).show();
                    finish();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        checkPermissionAndGetLocation();
    }

    private void checkPermissionAndGetLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            // You can directly ask for the permission.
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            String stGPS = location.getLatitude() + "*" + location.getLongitude();
                            Intent goback = new Intent();
                            goback.putExtra(EXTRA_GPS_DATA, stGPS);
                            setResult(RESULT_OK, goback);
                            finish();
                        } else {
                            Toast.makeText(MyLocation.this, "Could not retrieve location. Please ensure location is enabled.", Toast.LENGTH_LONG).show();
                            finish(); // Finish if location is null
                        }
                    }
                });
    }
}
