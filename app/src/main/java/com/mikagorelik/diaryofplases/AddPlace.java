package com.mikagorelik.diaryofplases;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddPlace extends BaseActivity {

    private static final int ADD_INNER_CONTACT_MENU_ID = 1;

    private EditText etName, etComment;
    private ImageView ivImage;
    private HelperDB helperDB;

    private String stGPS = "";
    private String stLink = "";
    private Uri imageUri;

    private final ActivityResultLauncher<Intent> searchLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    stLink = result.getData().getStringExtra(Search.EXTRA_RESULT_URL);
                    Toast.makeText(this, "Link captured", Toast.LENGTH_SHORT).show();
                }
            });

    private final ActivityResultLauncher<Intent> locationLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    stGPS = result.getData().getStringExtra(MyLocation.EXTRA_GPS_DATA);
                    Toast.makeText(this, "Location captured", Toast.LENGTH_SHORT).show();
                }
            });

    private final ActivityResultLauncher<Uri> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.TakePicture(),
            success -> {
                if (success) {
                    ivImage.setImageURI(imageUri);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        initViews();
        requestPermissions();

        ivImage.setOnClickListener(v -> {
            String placeName = etName.getText().toString();
            if (placeName.isEmpty()) {
                Toast.makeText(this, "Write name of place first!", Toast.LENGTH_LONG).show();
                return;
            }
            imageUri = createImageUri(placeName);
            if (imageUri != null) {
                cameraLauncher.launch(imageUri);
            }
        });

        findViewById(R.id.bLink).setOnClickListener(v -> {
            String placeName = etName.getText().toString();
            if (placeName.isEmpty()) {
                Toast.makeText(this, "Need name of place first!", Toast.LENGTH_LONG).show();
                return;
            }
            Intent go = new Intent(this, Search.class);
            go.putExtra(Search.EXTRA_SEARCH_QUERY, placeName);
            searchLauncher.launch(go);
        });

        findViewById(R.id.bGPS).setOnClickListener(v -> {
            Intent go = new Intent(this, MyLocation.class);
            locationLauncher.launch(go);
        });

        findViewById(R.id.bSavePlace).setOnClickListener(v -> confirmAndSavePlace());
    }

    private void initViews() {
        etComment = findViewById(R.id.etComment);
        etName = findViewById(R.id.etName);
        ivImage = findViewById(R.id.ivImage);
        helperDB = new HelperDB(this);
    }

    private void requestPermissions() {
        ActivityResultLauncher<String[]> permissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                permissions -> { /* Handle permissions granted/denied */ });
        permissionLauncher.launch(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE});
    }

    private Uri createImageUri(String placeName) {
        try {
            Calendar calendar = Calendar.getInstance();
            String imageFilename = placeName + "_" + calendar.get(Calendar.DAY_OF_MONTH) + "_" + (calendar.get(Calendar.MONTH) + 1) + "_" + calendar.get(Calendar.YEAR);
            File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File imageFile = File.createTempFile(imageFilename, ".jpg", storageDirectory);
            String authorities = getPackageName() + ".fileprovider";
            return FileProvider.getUriForFile(this, authorities, imageFile);
        } catch (IOException e) {
            Toast.makeText(this, "Failed to create image file", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private void confirmAndSavePlace() {
        String name = etName.getText().toString();
        String comment = etComment.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(comment) || ivImage.getDrawable() == null) {
            Toast.makeText(this, "Name, comment, and photo must not be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        speak("Save this place? " + name);
        new AlertDialog.Builder(this)
                .setTitle("Save this place?")
                .setMessage(name)
                .setPositiveButton("Ok", (dialog, which) -> savePlaceInBackground(name, comment))
                .setNegativeButton("No", null)
                .create().show();
    }

    private void savePlaceInBackground(String name, String comment) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            BitmapDrawable drawable = (BitmapDrawable) ivImage.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] bytesImage = byteArrayOutputStream.toByteArray();

            Place place = new Place(name, comment, bytesImage, stLink, stGPS);

            SQLiteDatabase db = helperDB.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(HelperDB.PLACE_NAME, place.getPlaceName());
            contentValues.put(HelperDB.PLACE_COMMENT, place.getPlaceComment());
            contentValues.put(HelperDB.PLACE_IMAGE, place.getPlaceImage());
            contentValues.put(HelperDB.PLACE_LINK, place.getPlaceLink());
            contentValues.put(HelperDB.PLACE_GPS, place.getPlaceGPS());
            long result = db.insert(HelperDB.TABLE_PLACE, null, contentValues);
            db.close();

            handler.post(() -> {
                if (result != -1) {
                    Toast.makeText(this, "Place saved successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Failed to save place.", Toast.LENGTH_SHORT).show();
                }
            });
        });
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
            startActivity(new Intent(this, AddInnerContact.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
