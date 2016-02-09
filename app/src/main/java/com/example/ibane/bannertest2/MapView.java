package com.example.ibane.bannertest2;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by jesllagr on 10/31/15.
 */
public class MapView extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        webView = (WebView) findViewById(R.id.map_view);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setInitialScale(50 * (int)webView.getScaleX());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://catalog.utm.edu/mime/media/view/12/746/CatalogMap2015-2016-01.jpg");

    }

}
