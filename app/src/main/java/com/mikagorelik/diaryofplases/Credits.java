package com.mikagorelik.diaryofplases;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class Credits extends BaseActivity {

    private static final String MY_PHONE_NUMBER = "YOUR_PHONE_NUMBER"; // TODO: Replace with your phone number
    private static final String MY_EMAIL_ADDRESS = "YOUR_EMAIL_ADDRESS"; // TODO: Replace with your email

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        Button bSmsMe = findViewById(R.id.bSMSme);
        Button bMailMe = findViewById(R.id.bMailMe);

        bSmsMe.setOnClickListener(v -> {
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + MY_PHONE_NUMBER));
            smsIntent.putExtra("sms_body", "I have problems with the DiaryOfPlases application, I need help.");
            if (smsIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(smsIntent);
            } else {
                Toast.makeText(this, "No SMS app found.", Toast.LENGTH_SHORT).show();
            }
        });

        bMailMe.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{MY_EMAIL_ADDRESS});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Help Request for DiaryOfPlases");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "I have a problem with the application.");
            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(emailIntent);
            } else {
                Toast.makeText(this, "No Email app found.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem menuItem = menu.findItem(R.id.credits);
        if (menuItem != null) {
            menuItem.setVisible(false);
        }
        return true;
    }
}
