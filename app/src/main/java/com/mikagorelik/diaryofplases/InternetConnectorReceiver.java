package com.mikagorelik.diaryofplases;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * BroadcastReceiver для мониторинга состояния подключения к интернету.
 */
@SuppressWarnings("deprecation")
public class InternetConnectorReceiver extends BroadcastReceiver {
    private static final String STATUS_WIFI = "WiFi enabled";
    private static final String STATUS_MOBILE = "Mobile enabled";
    private static final String STATUS_OTHER = "Other connection type";
    private static final String STATUS_NO_INTERNET = "No internet is available";

    private ConnectivityChangeListener listener;

    public interface ConnectivityChangeListener {
        void onConnectivityChanged(String status);
    }

    public void setConnectivityChangeListener(ConnectivityChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String status;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return;
        }

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null) {
            switch (networkInfo.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    status = STATUS_WIFI;
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    status = STATUS_MOBILE;
                    break;
                default:
                    status = STATUS_OTHER;
                    break;
            }
        } else {
            status = STATUS_NO_INTERNET;
        }

        if (listener != null) {
            listener.onConnectivityChanged(status);
        }
    }
}

/*
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;


public class InternetConnectorReceiver extends BroadcastReceiver {
    public interface ConnectivityChangeListener {
        void onConnectivityChanged(String status);
    }

    private ConnectivityChangeListener listener;

    public void setConnectivityChangeListener(ConnectivityChangeListener listener) {
        this.listener = listener;
    }

    public InternetConnectorReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String status="";
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return;
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        if (networkInfo != null) {
            switch (networkInfo.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    status = "WiFi enabled";
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    status = "Mobile enabled";
                    break;
                default:
                    status = "Other connection type";
                    break;
            }
        } else {
            status = "No internet is available";
        }
        if (listener != null) {
            listener.onConnectivityChanged(status);
        }
        */
/*if (networkInfo!=null)  {
            if (networkInfo.getType()==ConnectivityManager.TYPE_WIFI) {
                status="WiFi enabled";
            }
            if (networkInfo.getType()==ConnectivityManager.TYPE_MOBILE)  {
                status="Mobile enabled";
            }
        }
        else {
            status = "No internet is available";
        }*//*

        Toast.makeText(context, "Status="+status, Toast.LENGTH_LONG).show();
    }
}*/
