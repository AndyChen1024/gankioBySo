package com.sorrowdrug.gankiobyso.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sorrowdrug.gankiobyso.R;

public class ArticleWebView extends AppCompatActivity {

    private WebView mWvWeb;
    private String mUrl;
    private WebSettings mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_web_view);

        mWvWeb = (WebView) findViewById(R.id.wv_web);

        initData();


    }

    private void initData() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        mWvWeb.loadUrl(mUrl);

        mSettings = mWvWeb.getSettings();
        mSettings.setTextSize(WebSettings.TextSize.NORMAL);
        mSettings.setSupportZoom(true);
        mSettings.setJavaScriptEnabled(true);
        //自动适应屏幕
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mSettings.setLoadWithOverviewMode(true);
        mWvWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWvWeb.loadUrl(url);
                return true;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWvWeb != null)
            mWvWeb.destroy();

    }
}
