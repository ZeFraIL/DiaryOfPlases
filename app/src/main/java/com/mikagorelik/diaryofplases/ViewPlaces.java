package com.mikagorelik.diaryofplases;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewPlaces extends BaseActivity implements PlaceAdapter.OnItemClickListener {

    private RecyclerView recyclerViewPlaces;
    private ProgressBar progressBar;
    private PlaceAdapter adapter;
    private List<Place> places;
    private HelperDB helperDB;

    private static final int ADD_INNER_CONTACT_MENU_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_places);
        initElements();
        loadPlacesInBackground();
    }

    private void initElements() {
        recyclerViewPlaces = findViewById(R.id.recyclerViewPlaces);
        progressBar = findViewById(R.id.progressBar);
        helperDB = new HelperDB(this);
        places = new ArrayList<>();
        adapter = new PlaceAdapter(places, this);
        recyclerViewPlaces.setAdapter(adapter);
        recyclerViewPlaces.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadPlacesInBackground() {
        // Show progress bar and hide recycler view before starting background work
        progressBar.setVisibility(View.VISIBLE);
        recyclerViewPlaces.setVisibility(View.GONE);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            List<Place> loadedPlaces = new ArrayList<>();
            SQLiteDatabase db = helperDB.getReadableDatabase();
            Cursor cursor = db.query(HelperDB.TABLE_PLACE, null, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(HelperDB.PLACE_NAME));
                    @SuppressLint("Range") String comment = cursor.getString(cursor.getColumnIndex(HelperDB.PLACE_COMMENT));
                    @SuppressLint("Range") byte[] image = cursor.getBlob(cursor.getColumnIndex(HelperDB.PLACE_IMAGE));
                    @SuppressLint("Range") String link = cursor.getString(cursor.getColumnIndex(HelperDB.PLACE_LINK));
                    @SuppressLint("Range") String gps = cursor.getString(cursor.getColumnIndex(HelperDB.PLACE_GPS));
                    loadedPlaces.add(new Place(name, comment, image, link, gps));
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

            // Post result to main thread
            handler.post(() -> {
                // Hide progress bar and show recycler view after background work is done
                progressBar.setVisibility(View.GONE);
                if (loadedPlaces.isEmpty()) {
                    Toast.makeText(ViewPlaces.this, "List of places is empty", Toast.LENGTH_SHORT).show();
                } else {
                    places.clear();
                    places.addAll(loadedPlaces);
                    adapter.notifyDataSetChanged();
                    recyclerViewPlaces.setVisibility(View.VISIBLE);
                }
            });
        });
    }

    @Override
    public void onItemClick(Place place) {
        speak(place.getPlaceName() + ". " + place.getPlaceComment());
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle(place.getPlaceName());
        adb.setMessage(place.getPlaceComment());

        if (place.getPlaceImage() != null) {
            ImageView imageView = new ImageView(this);
            Glide.with(this).load(place.getPlaceImage()).into(imageView);
            adb.setView(imageView);
        }

        adb.setPositiveButton("Work with information", (dialog, which) -> {
            // TODO: Implement place editing/sharing logic
        });
        adb.setNegativeButton("Enough", null);
        adb.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, ADD_INNER_CONTACT_MENU_ID, 0, "Add inner contact");
        MenuItem menuItem = menu.findItem(R.id.contacts);
        if (menuItem != null) {
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == ADD_INNER_CONTACT_MENU_ID) {
            Intent gonext = new Intent(this, AddInnerContact.class);
            startActivity(gonext);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
