package com.pentasoft.helloworld;

import com.pentasoft.helloworld.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_);
        
        WebView wv = (WebView) findViewById(R.id.webview1);
                
        WebSettings webSettings = wv.getSettings();
		// webSettings.setBuiltInZoomControls(true);
        
		wv.loadUrl("http://10.0.2.2:8001");

	}
}
