package com.mikagorelik.diaryofplases;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Guide extends BaseActivity implements View.OnClickListener {

    private LinearLayout LL;
    private List<String> files;
    private List<Button> btns;
    private List<TextView> tvs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initViews();
        buildListOfFiles();
        buildAccordion();
    }

    private void buildAccordion() {
        btns = new ArrayList<>();
        tvs = new ArrayList<>();
        if (!files.isEmpty()) {
            for (int i = 0; i < files.size(); i++) {
                Button button = new Button(this);
                button.setText("Guide's part #" + (i + 1));
                button.setId(i);
                button.setOnClickListener(this);
                btns.add(button);
                LL.addView(btns.get(i));

                TextView textView = new TextView(this);
                textView.setId(100 + i);
                textView.setBackgroundColor(Color.WHITE);
                textView.setPadding(10, 10, 10, 10);
                textView.setVisibility(View.GONE);
                tvs.add(textView);
                LL.addView(tvs.get(i));
            }
        }
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < btns.size(); i++) {
            if (v.getId() == btns.get(i).getId()) {
                toggleSection(i);
                break; // Exit loop once the clicked button is found
            }
        }
    }

    private void toggleSection(int index) {
        TextView textView = tvs.get(index);
        if (textView.getVisibility() == View.VISIBLE) {
            textView.setVisibility(View.GONE);
        } else {
            // Only load content when expanding
            String content = loadContentFromFile(files.get(index));
            textView.setText(content);
            textView.setVisibility(View.VISIBLE);
            speak(content); // Speak the content when it becomes visible
        }
    }

    private String loadContentFromFile(String fileName) {
        try {
            int fileID = getResources().getIdentifier(fileName, "raw", getPackageName());
            InputStream is = getResources().openRawResource(fileID);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder all = new StringBuilder();
            String st;
            while ((st = br.readLine()) != null) {
                all.append(st).append("\n");
            }
            br.close();
            return all.toString();
        } catch (Exception e) {
            Toast.makeText(this, "Error reading guide file.", Toast.LENGTH_SHORT).show();
            return "";
        }
    }

    private void buildListOfFiles() {
        files = new ArrayList<>();
        try {
            InputStream is = getResources().openRawResource(R.raw.guide_all);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String st;
            while ((st = br.readLine()) != null) {
                files.add(st);
            }
            br.close();
        } catch (IOException e) {
            Toast.makeText(this, "Could not load guide index.", Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews() {
        LL = findViewById(R.id.LL);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem menuItem = menu.findItem(R.id.guide);
        if (menuItem != null) {
            menuItem.setVisible(false);
        }
        return true;
    }
}
