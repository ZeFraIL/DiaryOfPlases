package com.mikagorelik.diaryofplases;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Security {

    private static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    private static final String ALIAS = "DiaryOfPlases_PasswordAlias";
    private static final String TRANSFORMATION = "AES/CBC/PKCS7Padding";

    private static final String PREFS_NAME = "SecurePasswordPrefs";
    private static final String PASSWORD_KEY = "EncryptedPassword";
    private static final String IV_KEY = "InitializationVector";

    private KeyStore keyStore;

    public Security() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);
    }

    public void encryptAndSavePassword(String password, Context context) throws Exception {
        SecretKey secretKey = getOrCreateSecretKey();

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] iv = cipher.getIV();
        byte[] encryptedPassword = cipher.doFinal(password.getBytes("UTF-8"));

        saveToPrefs(encryptedPassword, iv, context);
    }

    public String getDecryptedPassword(Context context) throws Exception {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String encryptedPasswordB64 = prefs.getString(PASSWORD_KEY, null);
        String ivB64 = prefs.getString(IV_KEY, null);

        if (encryptedPasswordB64 == null || ivB64 == null) {
            return null; // No password saved
        }

        byte[] encryptedPassword = Base64.decode(encryptedPasswordB64, Base64.DEFAULT);
        byte[] iv = Base64.decode(ivB64, Base64.DEFAULT);

        SecretKey secretKey = (SecretKey) keyStore.getKey(ALIAS, null);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

        byte[] decryptedPasswordBytes = cipher.doFinal(encryptedPassword);
        return new String(decryptedPasswordBytes, "UTF-8");
    }

    public boolean isPasswordSet(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.contains(PASSWORD_KEY);
    }

    private SecretKey getOrCreateSecretKey() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, UnrecoverableKeyException {
        if (!keyStore.containsAlias(ALIAS)) {
            final KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEYSTORE);

            final KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(ALIAS,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7);

            keyGenerator.init(builder.build());
            return keyGenerator.generateKey();
        } else {
            return (SecretKey) keyStore.getKey(ALIAS, null);
        }
    }

    private void saveToPrefs(byte[] encryptedPassword, byte[] iv, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        String encryptedPasswordB64 = Base64.encodeToString(encryptedPassword, Base64.DEFAULT);
        String ivB64 = Base64.encodeToString(iv, Base64.DEFAULT);

        editor.putString(PASSWORD_KEY, encryptedPasswordB64);
        editor.putString(IV_KEY, ivB64);
        editor.apply();
    }
}
