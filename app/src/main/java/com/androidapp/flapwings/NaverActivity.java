package com.androidapp.flapwings;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NaverActivity extends AppCompatActivity {
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver);
        ButterKnife.bind(this);

        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings(); // 웹세팅
        webSettings.setJavaScriptEnabled(true); // 스크립트 허용
        webSettings.setUseWideViewPort(true); // 와이드뷰 허용
        webSettings.setSupportZoom(true); // 줌 허용
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 캐시 사용하지 않음
        webView.loadUrl("https://search.naver.com/search.naver?sm=top_sug.pre&fbm=0&acr=1&acq=%EB%A7%9E%EC%B6%A4%EB%B2%95&qdt=0&ie=utf8&query=%EB%A7%9E%EC%B6%A4%EB%B2%95%EA%B2%80%EC%82%AC%EA%B8%B0"); // url 연결
    }
}
