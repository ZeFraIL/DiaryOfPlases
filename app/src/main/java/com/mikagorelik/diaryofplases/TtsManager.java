package com.mikagorelik.diaryofplases;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class TtsManager implements TextToSpeech.OnInitListener {

    private static TtsManager instance;
    private TextToSpeech tts;
    private boolean isInitialized = false;

    private TtsManager(Context context) {
        this.tts = new TextToSpeech(context.getApplicationContext(), this);
    }

    public static synchronized TtsManager getInstance(Context context) {
        if (instance == null) {
            instance = new TtsManager(context);
        }
        return instance;
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setLanguage(Locale.getDefault());
            isInitialized = true;
        } else {
            Log.e("TTS", "Initialization failed");
        }
    }

    public void speak(String text) {
        if (isInitialized && text != null && !text.isEmpty()) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else if (!isInitialized) {
            Log.w("TTS", "TTS not initialized, cannot speak.");
        }
    }

    public void shutdown() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        instance = null;
    }
}
