package com.mikagorelik.diaryofplases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;




public class Start extends AppCompatActivity {


    CountDownTimer cdt;
    HelperDB helperDB;
    SQLiteDatabase db;
    Context context=Start.this;
    private TextView startTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startTV=findViewById(R.id.text_View);


        initElements();

        cdt=new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent next=new Intent(context, StartChoise.class);
                //Intent next=new Intent(context, AddToDB.class);
                startActivity(next);
            }
        };
        cdt.start();
    }

    private void initElements() {
        helperDB =new HelperDB(context);
        db= helperDB.getWritableDatabase();
        db.close();
    }

    public void start(View view) {


        cdt.cancel();
       Intent next=new Intent(context, StartChoise.class);
       startActivity(next);
    }
}