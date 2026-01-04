package com.mikagorelik.diaryofplases;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AlertDialog;

public class MainChoise extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_choise);

        findViewById(R.id.bAddPlace).setOnClickListener(v -> buildDialog("Add place?", AddPlace.class));
        findViewById(R.id.bViewPlaces).setOnClickListener(v -> buildDialog("View places?", ViewPlaces.class));
        findViewById(R.id.bInfo).setOnClickListener(v -> buildDialog("View information?", OnlyInfo.class));
    }

    public void buildDialog(String title, Class<?> toGo) {
        speak(title);
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setPositiveButton("Yes", (dialog, which) -> {
                    Intent go = new Intent(this, toGo);
                    startActivity(go);
                })
                .setNegativeButton("No", null)
                .create().show();
    }
}
