package com.example.taisuke.ankotest;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Kuchikomi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuchikomi);
        WebView webview = findViewById(R.id.web);
        webview.loadUrl("https://twitter.com/search?f=tweets&vertical=default&q=%23NITFes&src=typd");
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setVerticalScrollBarEnabled(true);
    }
}
