package com.mikagorelik.diaryofplases;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;

public class Emergency extends BaseActivity {

    private final ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            isGranted -> {
                if (!isGranted) {
                    Toast.makeText(this, "Call permission denied. Cannot make emergency calls.", Toast.LENGTH_LONG).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        requestCallPermission();

        findViewById(R.id.bCallPolice).setOnClickListener(v -> callNow("100"));
        findViewById(R.id.bCallAmbulance).setOnClickListener(v -> callNow("101"));
        findViewById(R.id.bCallFire).setOnClickListener(v -> callNow("102"));
    }

    private void requestCallPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE);
        }
    }

    @SuppressLint("MissingPermission")
    private void callNow(String phoneNumber) {
        speak("Calling now " + phoneNumber);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        } else {
            Toast.makeText(this, "Call permission is not granted.", Toast.LENGTH_SHORT).show();
            // Optionally, launch the dialer instead so the user can initiate the call manually
            // Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            // startActivity(dialIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem menuItem = menu.findItem(R.id.emergency);
        if (menuItem != null) {
            menuItem.setVisible(false);
        }
        return true;
    }
}
