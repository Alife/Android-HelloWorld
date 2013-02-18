package com.pentasoft.helloworld;

import java.util.Date;

import net.tsz.afinal.FinalDb;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.pentasoft.config.Configs.DaoConfig;
import com.pentasoft.db.model.Article;
import com.zijunlin.Zxing.Demo.CaptureActivity;

public class MainActivity extends Activity {

	OnClickListener lis1 = null;
	OnClickListener lis2 = null;
	OnClickListener lis3 = null;
	OnClickListener lis4 = null;
	OnClickListener lis5 = null;
	OnClickListener lis6 = null;
	OnClickListener lis7 = null;
	Button bt1 = null;
	Button bt2 = null;
	Button bt3 = null;
	Button bt4 = null;
	Button bt5 = null;
	Button bt6 = null;
	Button bt7 = null;
	TextView textView1 = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getBt();
		setListener();

		// List<Article> articleList = new ArticleServices().getRemoteArticle();
		// // setTitle(articleList.size() + "");
		// FinalDb db = FinalDb.create(this, DaoConfig.getDbName());
		// for (Article article : articleList) {
		// db.save(article);
		// }
		try {

			FinalDb db = FinalDb.create(this, DaoConfig.getDbName(), true);
			Article article = new Article();
			article.setColumnId(1);
			article.setContent("qqqqq");
			article.setDateCreated(new Date());
			// article.setId(1);
			article.setTitle("title");
			db.save(article);
			Log.i("FinalDb", article.getArticleId() + "");
		} catch (Exception e) {
			// TODO: handle exception
			Log.i("FinalDb", e.getMessage());
		}

		Intent intent = getIntent();
		String code = intent.getStringExtra("code");// 从Intent中获取数据
		textView1.setText(code);
	}

	/**
	 * 获取按钮
	 */
	private void getBt() {
		bt1 = (Button) findViewById(R.id.button1);
		bt2 = (Button) findViewById(R.id.button2);
		bt3 = (Button) findViewById(R.id.button3);
		bt4 = (Button) findViewById(R.id.button4);
		bt5 = (Button) findViewById(R.id.button5);
		bt6 = (Button) findViewById(R.id.button6);
		bt7 = (Button) findViewById(R.id.buttonCapture);
		textView1 = (TextView) findViewById(R.id.textView1);
	}

	/**
	 * 、 * 02-02 16:35:04.771: E/AndroidRuntime(728): at
	 * com.pentasoft.helloworld.MainActivity.getBt(MainActivity.java:109) 添加按钮处理事件
	 */
	private void setListener() {
		lis1 = new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 按钮1被点击了,设置按钮1不可见
			// bt1.setVisibility(View.INVISIBLE);
			// bt2.setVisibility(View.VISIBLE);
			// setTitle("按钮1不见了!");
				Intent intent = new Intent(MainActivity.this, VoiceRecognition.class);
				startActivity(intent);
			}
		};
		lis2 = new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 按钮2被点击了,设置按钮2不可见
			// bt2.setVisibility(View.INVISIBLE);
			// bt1.setVisibility(View.VISIBLE);
			// setTitle("按钮2不见了!");
				Intent intent = new Intent(MainActivity.this, VoiceSaveActivity.class);
				startActivity(intent);
			}
		};
		lis3 = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
				startActivity(intent);
			}
		};
		lis4 = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
				startActivity(intent);
			}
		};
		lis5 = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, WebViewClientActivity.class);
				startActivity(intent);
			}
		};
		lis6 = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, PhoneInfoActivity.class);
				startActivity(intent);
			}
		};
		lis7 = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
				startActivity(intent);
			}
		};
		bt1.setOnClickListener(lis1);
		bt2.setOnClickListener(lis2);
		bt3.setOnClickListener(lis3);
		bt4.setOnClickListener(lis4);
		bt5.setOnClickListener(lis5);
		bt6.setOnClickListener(lis6);
		bt7.setOnClickListener(lis7);
	}

	// 重载onConfigurationChanged方法，此方法会在屏幕方向改变时被调用如下：
    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 加入横屏要处理的代码
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // 加入竖屏要处理的代码
        }
    }
}