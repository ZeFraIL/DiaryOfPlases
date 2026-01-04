package com.mikagorelik.diaryofplases;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InnerContacts extends BaseActivity implements ContactAdapter.OnInnerContactClickListener {

    private RecyclerView recyclerViewContacts;
    private ProgressBar progressBar;
    private HelperDB helperDB;
    private myContact currentContact;

    private static final int ADD_INNER_CONTACT_MENU_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_contacts);

        initViews();
        requestContactPermissions();

        findViewById(R.id.bInner).setOnClickListener(v -> displayInnerContacts());
        findViewById(R.id.bTotal).setOnClickListener(v -> displayDeviceContacts());

        displayInnerContacts();
    }

    private void initViews() {
        recyclerViewContacts = findViewById(R.id.recyclerViewContacts);
        progressBar = findViewById(R.id.progressBarContacts);
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        helperDB = new HelperDB(this);
    }

    private void requestContactPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.READ_CONTACTS}, 200);
    }

    private void displayInnerContacts() {
        setLoading(true);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            List<myContact> innerContacts = new ArrayList<>();
            SQLiteDatabase db = helperDB.getReadableDatabase();
            Cursor cursor = db.query(HelperDB.TABLE_MY_CONTACT, null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(HelperDB.MY_CONTACT_ID));
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(HelperDB.MY_CONTACT_NAME));
                    @SuppressLint("Range") String comment = cursor.getString(cursor.getColumnIndex(HelperDB.MY_CONTACT_COMMENT));
                    @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(HelperDB.MY_CONTACT_PHONE));
                    @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(HelperDB.MY_CONTACT_EMAIL));
                    innerContacts.add(new myContact(id, name, comment, phone, email));
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

            handler.post(() -> {
                setLoading(false);
                if(innerContacts.isEmpty()){
                    Toast.makeText(this, "No inner contacts found", Toast.LENGTH_SHORT).show();
                }
                ContactAdapter adapter = new ContactAdapter(innerContacts, this);
                recyclerViewContacts.setAdapter(adapter);
            });
        });
    }

    @SuppressLint("Range")
    private void displayDeviceContacts() {
        setLoading(true);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(()-> {
            List<String> deviceContacts = new ArrayList<>();
            Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    deviceContacts.add(name + ": " + number);
                } while (cursor.moveToNext());
                cursor.close();
            }

            handler.post(()-> {
                setLoading(false);
                if(deviceContacts.isEmpty()) {
                    Toast.makeText(this, "No device contacts found", Toast.LENGTH_SHORT).show();
                } else {
                     ContactAdapter adapter = new ContactAdapter(deviceContacts, null);
                     recyclerViewContacts.setAdapter(adapter);
                }
            });
        });
    }

    @Override
    public void onItemClick(myContact contact) {
        this.currentContact = contact;
        showContactDialog();
    }

    private void showContactDialog() {
        speak(currentContact.toString());
        final View dialogView = getLayoutInflater().inflate(R.layout.mycontact_dialog, null);
        final AlertDialog dialog = new AlertDialog.Builder(this).setCancelable(false).setView(dialogView).create();

        ((TextView) dialogView.findViewById(R.id.tvTitle)).setText("From 'My inner contacts'");
        ((TextView) dialogView.findViewById(R.id.tvMessage)).setText(currentContact.toString());

        dialogView.findViewById(R.id.bSendSMS).setOnClickListener(v -> sendSms(dialog));
        dialogView.findViewById(R.id.bSendEmail).setOnClickListener(v -> sendEmail(dialog));
        dialogView.findViewById(R.id.bCall).setOnClickListener(v -> makeCall(dialog));
        dialogView.findViewById(R.id.bDelete).setOnClickListener(v -> confirmDelete(dialog));
        dialogView.findViewById(R.id.bUpdate).setOnClickListener(v -> updateContact(dialog));
        dialogView.findViewById(R.id.bCancel).setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void updateContact(DialogInterface parentDialog) {
        Intent intent = new Intent(this, AddInnerContact.class);
        intent.putExtra(AddInnerContact.EXTRA_CONTACT, currentContact);
        startActivity(intent);
        parentDialog.dismiss();
    }

    private void confirmDelete(DialogInterface parentDialog) {
        speak("Are you sure you want to delete?");
        new AlertDialog.Builder(this)
                .setTitle("Are you sure you want to delete?")
                .setMessage(currentContact.toString())
                .setPositiveButton("Delete", (dialog, which) -> deleteContactInBackground(parentDialog))
                .setNegativeButton("No", null)
                .show();
    }

    private void deleteContactInBackground(DialogInterface parentDialog) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            SQLiteDatabase db = helperDB.getWritableDatabase();
            int result = db.delete(HelperDB.TABLE_MY_CONTACT, HelperDB.MY_CONTACT_ID + "=?", new String[]{currentContact.getContactID()});
            db.close();

            handler.post(() -> {
                if(result > 0){
                    Toast.makeText(this, "Contact deleted", Toast.LENGTH_SHORT).show();
                    displayInnerContacts(); // Refresh the list
                    parentDialog.dismiss();
                } else {
                    Toast.makeText(this, "Failed to delete contact", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @SuppressLint("MissingPermission")
    private void makeCall(DialogInterface parentDialog) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + currentContact.getContactPhone())));
        }
        parentDialog.dismiss();
    }

    private void sendEmail(DialogInterface parentDialog) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + currentContact.getContactEmail()));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Help Request");
        startActivity(intent);
        parentDialog.dismiss();
    }

    private void sendSms(DialogInterface parentDialog) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + currentContact.getContactPhone()));
        intent.putExtra("sms_body", "Message from DiaryOfPlases");
        startActivity(intent);
        parentDialog.dismiss();
    }

    private void setLoading(boolean isLoading) {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        recyclerViewContacts.setVisibility(isLoading ? View.GONE : View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, ADD_INNER_CONTACT_MENU_ID, 0, "Add inner contact");
        MenuItem menuItem = menu.findItem(R.id.contacts);
        if (menuItem != null) menuItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == ADD_INNER_CONTACT_MENU_ID) {
            Intent intent = new Intent(this, AddInnerContact.class);
            intent.putExtra(AddInnerContact.EXTRA_CONTACT, new myContact("", "", "", "", ""));
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
