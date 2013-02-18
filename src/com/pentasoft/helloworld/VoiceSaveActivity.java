package com.pentasoft.helloworld;

import java.io.File;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.widget.Button;

/**
 * This is main class which shows how to capture/record audio
 * 
 * @author The Developer's Info
 * 
 */
public class VoiceSaveActivity extends Activity {
	private MediaRecorder mediaRecorder;
	private File file = null;
	static final String PREFIX = "record";
	static final String EXTENSION = ".3gpp";
	Button startRecording;
	Button stopRecording;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.voice_save);

		mediaRecorder = new MediaRecorder();

		startRecording = (Button) findViewById(R.id.startBtn);
		stopRecording = (Button) findViewById(R.id.stopBtn);
		startRecording.setVisibility(View.VISIBLE);
		stopRecording.setVisibility(View.INVISIBLE);
		startRecording.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try {
					startRecording();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		stopRecording.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stopRecording();
				saveToDB();
			}
		});
	}

	/**
	 * This method starts recording process
	 * 
	 * @throws Exception
	 */
	private void startRecording() throws Exception {
		startRecording.setVisibility(View.INVISIBLE);
		stopRecording.setVisibility(View.VISIBLE);
		mediaRecorder = new MediaRecorder();
		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		if (file == null) {
			File rootDir = Environment.getExternalStorageDirectory();
			file = File.createTempFile(PREFIX, EXTENSION, rootDir);
		}
		mediaRecorder.setOutputFile(file.getAbsolutePath());
		mediaRecorder.prepare();
		mediaRecorder.start();
	}

	/**
	 * This method stops recording
	 */
	private void stopRecording() {
		mediaRecorder.stop();
		mediaRecorder.release();
		startRecording.setVisibility(View.VISIBLE);
		stopRecording.setVisibility(View.INVISIBLE);
	}

	/**
	 * This method sets all metadata for audio file
	 */
	private void saveToDB() {
		ContentValues values = new ContentValues(3);
		long current = System.currentTimeMillis();
		values.put(MediaColumns.TITLE, "My Audio record");
		values.put(MediaColumns.DATE_ADDED, (int) (current / 1000));
		values.put(MediaColumns.MIME_TYPE, "audio/mp3");
		values.put(MediaColumns.DATA, file.getAbsolutePath());
		ContentResolver contentResolver = getContentResolver();
		Uri base = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		Uri newUri = contentResolver.insert(base, values);
		sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, newUri));
	}
}