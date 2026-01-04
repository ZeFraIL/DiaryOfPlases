package com.mikagorelik.diaryofplases;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddInnerContact extends BaseActivity {

    public static final String EXTRA_CONTACT = "com.mikagorelik.diaryofplases.EXTRA_CONTACT";

    private HelperDB helperDB;
    private TextView tvAddInner;
    private EditText etAddName, etAddComment, etAddPhone, etAddEmail;
    private Button bAdd;
    private myContact contactToUpdate;

    private static final int ADD_INNER_CONTACT_MENU_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inner_contact);

        initViews();

        Intent intent = getIntent();
        contactToUpdate = (myContact) intent.getSerializableExtra(EXTRA_CONTACT);

        if (contactToUpdate != null && !TextUtils.isEmpty(contactToUpdate.getContactID())) {
            populateFieldsForUpdate();
        }

        bAdd.setOnClickListener(v -> saveContactInBackground());
    }

    private void populateFieldsForUpdate() {
        tvAddInner.setText("Update inner contact");
        etAddName.setText(contactToUpdate.getContactName());
        etAddComment.setText(contactToUpdate.getContactComment());
        etAddPhone.setText(contactToUpdate.getContactPhone());
        etAddEmail.setText(contactToUpdate.getContactEmail());
        bAdd.setText("Update inner contact");
    }

    private void saveContactInBackground() {
        String name = etAddName.getText().toString();
        String comment = etAddComment.getText().toString();
        String phone = etAddPhone.getText().toString();
        String email = etAddEmail.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(comment) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(email)) {
            Toast.makeText(this, "All fields must be filled!", Toast.LENGTH_SHORT).show();
            return;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            SQLiteDatabase db = helperDB.getWritableDatabase();

            if (contactToUpdate != null && !TextUtils.isEmpty(contactToUpdate.getContactID())) {
                db.delete(HelperDB.TABLE_MY_CONTACT, HelperDB.MY_CONTACT_ID + "=?", new String[]{contactToUpdate.getContactID()});
            }

            String newId = "" + (long) (Long.MAX_VALUE * Math.random());
            ContentValues contentValues = new ContentValues();
            contentValues.put(HelperDB.MY_CONTACT_ID, newId);
            contentValues.put(HelperDB.MY_CONTACT_NAME, name);
            contentValues.put(HelperDB.MY_CONTACT_COMMENT, comment);
            contentValues.put(HelperDB.MY_CONTACT_PHONE, phone);
            contentValues.put(HelperDB.MY_CONTACT_EMAIL, email);
            long result = db.insert(HelperDB.TABLE_MY_CONTACT, null, contentValues);
            db.close();

            handler.post(() -> {
                if (result != -1) {
                    Toast.makeText(this, "Contact saved successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Failed to save contact.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void initViews() {
        tvAddInner = findViewById(R.id.tvAddInner);
        etAddName = findViewById(R.id.etAddName);
        etAddComment = findViewById(R.id.etAddComment);
        etAddPhone = findViewById(R.id.etAddPhone);
        etAddEmail = findViewById(R.id.etAddEmail);
        bAdd = findViewById(R.id.bAdd);
        helperDB = new HelperDB(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, ADD_INNER_CONTACT_MENU_ID, 0, "Add another contact");
        MenuItem menuItem = menu.findItem(R.id.contacts);
        if (menuItem != null) {
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == ADD_INNER_CONTACT_MENU_ID) {
            etAddName.setText("");
            etAddComment.setText("");
            etAddPhone.setText("");
            etAddEmail.setText("");
            tvAddInner.setText("Add inner contact");
            bAdd.setText("Add inner contact");
            contactToUpdate = null;
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
