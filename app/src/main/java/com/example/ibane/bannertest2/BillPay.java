package com.example.ibane.bannertest2;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by jesllagr on 10/31/15.
 */
public class BillPay extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_pay);

//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        webView = (WebView) findViewById(R.id.bill_pay);
        webView.getSettings().setBuiltInZoomControls(true);
//        webView.setInitialScale(50 * (int)webView.getScaleX());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://secure.touchnet.com/C21608_tsa/web/");

    }

}


//https://secure.touchnet.com/C21608_tsa/web/index.jsp