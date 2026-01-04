package com.mikagorelik.diaryofplases;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class LogOrReg extends BaseActivity {

    private TextView tvLR;
    private EditText etPassword;
    private Button bLR;

    private Security security;
    private boolean isRegisterMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_or_reg);

        try {
            security = new Security();
        } catch (Exception e) {
            Toast.makeText(this, "Keystore initialization failed. Cannot proceed.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        initViews();
        setupMode();

        bLR.setOnClickListener(v -> {
            String password = etPassword.getText().toString();
            if (password.isEmpty()) {
                showToastAndSpeak("Error: password is empty!");
                return;
            }

            if (isRegisterMode) {
                handleRegistration(password);
            } else {
                handleLogin(password);
            }
        });
    }

    private void handleRegistration(String password) {
        speak("Your password will be: " + password);
        new AlertDialog.Builder(this)
                .setTitle("Confirm Your Password")
                .setMessage("Your password will be: " + password)
                .setPositiveButton("It's Ok", (dialog, which) -> {
                    try {
                        security.encryptAndSavePassword(password, this);
                        showToastAndSpeak("Registration successful!");
                        goToMainChoice();
                    } catch (Exception e) {
                        showToastAndSpeak("Failed to save password. Please try again.");
                    }
                })
                .setNegativeButton("Go Back", (dialog, which) -> etPassword.setText(""))
                .create().show();
    }

    private void handleLogin(String password) {
        try {
            String decryptedPassword = security.getDecryptedPassword(this);
            if (password.equals(decryptedPassword)) {
                goToMainChoice();
            } else {
                showToastAndSpeak("Error: password incorrect!");
            }
        } catch (Exception e) {
            showToastAndSpeak("Failed to decrypt password. The security key might have been invalidated.");
        }
    }

    private void setupMode() {
        if (security.isPasswordSet(this)) {
            isRegisterMode = false;
            tvLR.setText("Write your password for\nLogin");
            bLR.setText("Login");
        } else {
            isRegisterMode = true;
            tvLR.setText("Write your password for\nRegister");
            bLR.setText("Register");
        }
    }

    private void initViews() {
        bLR = findViewById(R.id.bLR);
        tvLR = findViewById(R.id.tvTitle);
        etPassword = findViewById(R.id.etPassword);
    }

    private void goToMainChoice() {
        Intent intent = new Intent(this, MainChoise.class);
        startActivity(intent);
        finish();
    }

    private void showToastAndSpeak(String message) {
        speak(message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
