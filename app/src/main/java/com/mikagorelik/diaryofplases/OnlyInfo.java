package com.mikagorelik.diaryofplases;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class OnlyInfo extends AppCompatActivity {

    Context context;
    private boolean isService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_info);

        initElements();
    }

    private void initElements() {
        context=OnlyInfo.this;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        if (menu instanceof MenuBuilder)  {
            MenuBuilder mb=(MenuBuilder) menu;
            mb.setOptionalIconsVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.back) {
            finish();
        }
        if(id==R.id.exit) {
            finishAffinity();
        }
        if(id==R.id.credits) {
            Intent gonext=new Intent(context, Credits.class);
            startActivity(gonext);
        }
        if(id==R.id.guide) {
            Intent gonext=new Intent(context, Guide.class);
            startActivity(gonext);
        }
        if(id==R.id.contacts) {
            Intent gonext=new Intent(context, InnerContacts.class);
            startActivity(gonext);
        }
        if(id==R.id.emergency) {
            Intent gonext=new Intent(context, Emergency.class);
            startActivity(gonext);
        }
        if (id==R.id.speak_on)
            isService=true;
        if (id==R.id.speak_off)
            isService=false;
        return super.onContextItemSelected(item);
    }
}