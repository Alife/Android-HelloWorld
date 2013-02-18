package com.pentasoft.helloworld;

import com.pentasoft.helloworld.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewClientActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view_client);

		WebView wv = (WebView) findViewById(R.id.webviewclient1);

		// WebSettings webSettings = wv.getSettings();
		// webSettings.setBuiltInZoomControls(true);

		wv.setWebViewClient(new Callback());
		wv.loadUrl("http://10.0.2.2:8001");

	}

	private class Callback extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			return (false);
		}
	}

}