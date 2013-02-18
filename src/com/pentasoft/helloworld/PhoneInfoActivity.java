package com.pentasoft.helloworld;

import com.pentasoft.helloworld.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * class name£ºAndroidUtilActivity<BR>
 * class description£ºshow get sim card info activity<BR>
 * PS£º×¢ÒâÈ¨ÏÞ <BR>
 * Date:2012-3-12<BR>
 * 
 * @version 1.00
 * @author CODYY)peijiangping
 */
public class PhoneInfoActivity extends Activity {
	private TextView number;
	private TextView privoid;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_info);
		number = (TextView) this.findViewById(R.id.textView1);
		privoid = (TextView) this.findViewById(R.id.textView2);

		SIMCardInfo siminfo = new SIMCardInfo(PhoneInfoActivity.this);
		System.out.println(siminfo.getProvidersName());
		System.out.println(siminfo.getNativePhoneNumber());
		number.setText(siminfo.getNativePhoneNumber());
		privoid.setText(siminfo.getProvidersName());

	}
}