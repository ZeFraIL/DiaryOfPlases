package com.mikagorelik.diaryofplases;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class Search extends BaseActivity {

    public static final String EXTRA_SEARCH_QUERY = "com.mikagorelik.diaryofplases.EXTRA_SEARCH_QUERY";
    public static final String EXTRA_RESULT_URL = "com.mikagorelik.diaryofplases.EXTRA_RESULT_URL";

    Context context = Search.this;
    EditText et;
    String stUrl = "";
    WebView wvs;
    private static final String GOOGLE_SEARCH_URL = "https://www.google.com/search?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initElements();
    }

    private void initElements() {
        Intent take = getIntent();
        stUrl = take.getStringExtra(EXTRA_SEARCH_QUERY);
        et = findViewById(R.id.et);
        et.setText(stUrl);
        wvs = findViewById(R.id.wvs);
        if (stUrl != null && !stUrl.isEmpty()) {
            WebSettings webSettings = wvs.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wvs.setWebViewClient(new MyWebViewClient());
            wvs.loadUrl(GOOGLE_SEARCH_URL + stUrl);
        }
    }

    public void goSearch(View view) {
        stUrl = et.getText().toString();
        if (stUrl.isEmpty())
            return;

        speak("Search now for " + stUrl);

        WebSettings webSettings = wvs.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wvs.setWebViewClient(new MyWebViewClient());
        wvs.loadUrl(GOOGLE_SEARCH_URL + stUrl);
    }

    public void backWithLink(View view) {
        Intent goback = new Intent();
        goback.putExtra(EXTRA_RESULT_URL, wvs.getUrl());
        setResult(RESULT_OK, goback); // Use RESULT_OK standard code
        finish();
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }

    // onCreateOptionsMenu and onOptionsItemSelected are now handled by BaseActivity.
}
