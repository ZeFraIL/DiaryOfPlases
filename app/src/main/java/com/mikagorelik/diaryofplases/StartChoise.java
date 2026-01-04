package com.mikagorelik.diaryofplases;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartChoise extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_choise);
    }

    public void startUser(View view) {
        speak("You go now to registry or login");
        Intent next = new Intent(this, LogOrReg.class);
        startActivity(next);
    }

    public void onlyInfo(View view) {
        speak("You go now to information part only");
        Intent next = new Intent(this, OnlyInfo.class);
        startActivity(next);
    }
}
