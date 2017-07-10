package com.vivantor.mediacapture.UI;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vivantor.mediacapture.R;
import com.vivantor.mediacapture.VUtilities;

/**
 * Created by AhmedNTS on 2016-02-29.
 */
@SuppressWarnings("all")
public class VAudioCapture extends LinearLayout implements View.OnClickListener
{
	static final String TAG = VAudioCapture.class.getSimpleName();

	ImageButton captureButton;
	TextView captureTime;

	private boolean isCapturing = false;

	private MediaRecorder audioRecorder;
	private String outputFilePath = null;

	private Handler handler = new Handler();

	long startTime = 0L;
	long timeInMilliseconds = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;

	OnVAudioCaptureListener listener;

	public VAudioCapture(Context context)
	{
		super(context);
		init();
	}

	public VAudioCapture(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	public VAudioCapture(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init();
	}

	public VAudioCapture(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	void init()
	{
		inflate(getContext(), R.layout.view_capture_audio, this);

		captureButton = (ImageButton) findViewById(R.id.captureButton);
		captureTime = (TextView) findViewById(R.id.captureTime);

		captureTime.setOnClickListener(this);
		captureButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		if (!isCapturing)
			Start();
		else
			Stop();
	}

	public void PreparAudioCapture()
	{
		outputFilePath = VUtilities.getOutputMediaFile(3).getPath();
		Log.d(TAG, "outputFilePath= " + outputFilePath);

		try
		{
			releaseMediaRecorder();

			audioRecorder = new MediaRecorder();
			audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			audioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
			audioRecorder.setOutputFile(outputFilePath);
			audioRecorder.prepare();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			releaseMediaRecorder();
		}
	}

	public void Start()
	{
		PreparAudioCapture();

		if (audioRecorder == null) return;
		if (isCapturing) return;

		try
		{
			audioRecorder.start();
			isCapturing = true;

			startTime = SystemClock.uptimeMillis();
			handler.postDelayed(updateTimerThread, 0);

			if (listener != null)
				listener.OnStart();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			releaseMediaRecorder();
		}
	}

	public void Stop()
	{
		if (audioRecorder == null) return;
		if (!isCapturing) return;

		audioRecorder.stop();
		isCapturing = false;

		//timeSwapBuff += timeInMilliseconds;
		captureTime.setText("Record");
		handler.removeCallbacks(updateTimerThread);

		if (listener != null)
			listener.OnStop(outputFilePath);
	}

	private Runnable updateTimerThread = new Runnable()
	{
		public void run()
		{
			Log.d(TAG, "updateTimerThread");

			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

			updatedTime = timeSwapBuff + timeInMilliseconds;

			int secs = (int) (updatedTime / 1000);
			int mins = secs / 60;
			secs = secs % 60;

			captureTime.setText(String.format("%02d", mins) + ":" + String.format("%02d", secs));

			handler.postDelayed(this, 0);
		}
	};

	@Override
	protected void onDetachedFromWindow()
	{
		super.onDetachedFromWindow();

		handler.removeCallbacks(updateTimerThread);

		releaseMediaRecorder();
	}

	private void releaseMediaRecorder()
	{
		if (audioRecorder != null)
		{
			audioRecorder.reset();
			audioRecorder.release();
			audioRecorder = null;
		}
	}

	public void setOnVAudioCaptureListener(OnVAudioCaptureListener listener)
	{
		this.listener = listener;
	}

	public static interface OnVAudioCaptureListener
	{
		void OnStart();

		void OnStop(String filePath);
	}
}
