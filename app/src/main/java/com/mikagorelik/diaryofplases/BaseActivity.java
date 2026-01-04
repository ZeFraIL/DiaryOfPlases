package com.mikagorelik.diaryofplases;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

public abstract class BaseActivity extends AppCompatActivity {

    protected boolean isService;
    private TtsManager ttsManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ttsManager = TtsManager.getInstance(this);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        if (menu instanceof MenuBuilder) {
            ((MenuBuilder) menu).setOptionalIconsVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.back) {
            finish();
            return true;
        } else if (id == R.id.exit) {
            finishAffinity();
            return true;
        } else if (id == R.id.credits) {
            startActivity(new Intent(this, Credits.class));
            return true;
        } else if (id == R.id.guide) {
            startActivity(new Intent(this, Guide.class));
            return true;
        } else if (id == R.id.contacts) {
            startActivity(new Intent(this, InnerContacts.class));
            return true;
        } else if (id == R.id.emergency) {
            startActivity(new Intent(this, Emergency.class));
            return true;
        } else if (id == R.id.speak_on) {
            isService = true;
            speak("Voice assistance enabled");
            return true;
        } else if (id == R.id.speak_off) {
            isService = false;
            // No need to speak when turning off
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Speaks the given text if voice assistance is enabled.
     * @param message The text to be spoken.
     */
    protected void speak(String message) {
        if (isService) {
            ttsManager.speak(message);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Shutdown TTS manager only if it's the last activity, or handle it in Application class.
        // For simplicity, we can shut it down here. A more robust solution might involve Application.ActivityLifecycleCallbacks.
        if (isFinishing()) {
           // ttsManager.shutdown(); // This might be too aggressive if you move between activities quickly.
           // A better place is in the main/launcher activity when the app truly closes.
        }
    }
}
