package com.vivantor.mediacapture.UI;

/**
 * Created by AhmedNTS on 2016-02-16.
 */

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.vivantor.mediacapture.R;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("all")
public class VAudioPlayerO extends LinearLayout implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener
{
	private MediaPlayer mediaPlayer;

	private ToggleButton mediaToggle;
	private SeekBar mediaSeekBar;
	private TextView mediaRemainTime;

	private long totalDuration = 0;
	private long currentDuration = 0;

	private Handler handler = new Handler();

	public VAudioPlayerO(Context context)
	{
		super(context);
		init();
	}

	public VAudioPlayerO(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	public VAudioPlayerO(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init();
	}

	public VAudioPlayerO(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	public void init()
	{
		inflate(getContext(), R.layout.view_player_audio, this);

		mediaToggle = (ToggleButton) findViewById(R.id.mediaToggle);
		mediaSeekBar = (SeekBar) findViewById(R.id.mediaSeekBar);
		mediaSeekBar.setMax(100);
		mediaSeekBar.setProgress(0);
		mediaRemainTime = (TextView) findViewById(R.id.mediaRemainTime);

		mediaRemainTime.setText(String.format("%02d:%02d",
				TimeUnit.MILLISECONDS.toMinutes((long) currentDuration),
				TimeUnit.MILLISECONDS.toSeconds((long) currentDuration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) currentDuration))));

		mediaToggle.setOnCheckedChangeListener(this);
		mediaSeekBar.setOnSeekBarChangeListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{
		if (isChecked)
		{
			Start();
		}
		else
		{
			Pause();
		}
	}

	public void SetFilePath(String filePath)
	{
		if (filePath == null || filePath.isEmpty()) return;

		try
		{
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setDataSource(filePath);
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

			mediaPlayer.prepare();

			mediaPlayer.setOnCompletionListener(this);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void Start()
	{
		if (mediaPlayer == null) return;

		mediaPlayer.start();

		handler.postDelayed(UpdateSongTime, 100);
	}

	public void Pause()
	{
		if (mediaPlayer == null) return;

		mediaPlayer.pause();

		handler.removeCallbacks(UpdateSongTime);
	}

//	public void Stop()
//	{
//		if (mediaPlayer == null) return;
//
//		mediaPlayer.stop();
//
//		handler.removeCallbacks(UpdateSongTime);
//	}

	public boolean isPlaying()
	{
		if (mediaPlayer != null)
			return mediaPlayer.isPlaying();
		else return false;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
	{

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar)
	{
		// remove message Handler from updating progress bar
		handler.removeCallbacks(UpdateSongTime);
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar)
	{
		if (mediaPlayer == null) return;

		handler.removeCallbacks(UpdateSongTime);

		int totalDuration = mediaPlayer.getDuration();
		int currentPosition = Utilities.progressToTimer(seekBar.getProgress(), totalDuration);

		// forward or backward to certain seconds
		mediaPlayer.seekTo(currentPosition);

		// update timer progress again
		handler.postDelayed(UpdateSongTime, 100);
	}

	@Override
	public void onCompletion(MediaPlayer mp)
	{
		handler.removeCallbacks(UpdateSongTime);

		if (mediaToggle != null)
			mediaToggle.setChecked(false);
		if (mediaSeekBar != null)
			mediaSeekBar.setProgress(0);
		if (mediaRemainTime != null)
			mediaRemainTime.setText("00:00");
	}

	private Runnable UpdateSongTime = new Runnable()
	{
		public void run()
		{
			try
			{
//				if (mediaPlayer.isPlaying())
				{
					totalDuration = mediaPlayer.getDuration();
					currentDuration = mediaPlayer.getCurrentPosition();

					if (mediaRemainTime != null)
						mediaRemainTime.setText(String.format("%02d:%02d",
								TimeUnit.MILLISECONDS.toMinutes((long) currentDuration),
								TimeUnit.MILLISECONDS.toSeconds((long) currentDuration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) currentDuration))
						));

					// return percentage
					int progress = Utilities.getProgressPercentage(currentDuration, totalDuration);
					if (mediaSeekBar != null)
						mediaSeekBar.setProgress(progress);

					handler.postDelayed(this, 100);
				}
			}
			catch (IllegalArgumentException e)
			{
				e.printStackTrace();
			}
			catch (IllegalStateException e)
			{
				e.printStackTrace();
			}
		}
	};

	@Override
	protected void onDetachedFromWindow()
	{
		super.onDetachedFromWindow();

		Dispose();
	}

	public void Dispose()
	{
		handler.removeCallbacks(UpdateSongTime);

		if (mediaPlayer != null)
		{
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}

	public static class Utilities
	{
		/**
		 * Function to convert milliseconds time to
		 * Timer Format
		 * Hours:Minutes:Seconds
		 */
		public static String milliSecondsToTimer(long milliseconds)
		{
			String finalTimerString = "";
			String secondsString = "";

			// Convert total duration into time
			int hours = (int) (milliseconds / (1000 * 60 * 60));
			int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
			int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
			// Add hours if there
			if (hours > 0)
			{
				finalTimerString = hours + ":";
			}

			// Prepending 0 to seconds if it is one digit
			if (seconds < 10)
			{
				secondsString = "0" + seconds;
			}
			else
			{
				secondsString = "" + seconds;
			}

			finalTimerString = finalTimerString + minutes + ":" + secondsString;

			// return timer string
			return finalTimerString;
		}

		/**
		 * Function to get Progress percentage
		 *
		 * @param currentDuration
		 * @param totalDuration
		 */
		public static int getProgressPercentage(long currentDuration, long totalDuration)
		{
			Double percentage = (double) 0;

			long currentSeconds = (int) (currentDuration / 1000);
			long totalSeconds = (int) (totalDuration / 1000);

			// calculating percentage
			percentage = (((double) currentSeconds) / totalSeconds) * 100;

			// return percentage
			return percentage.intValue();
		}

		/**
		 * Function to change progress to timer
		 *
		 * @param progress      -
		 * @param totalDuration returns current duration in milliseconds
		 */
		public static int progressToTimer(int progress, int totalDuration)
		{
			int currentDuration = 0;
			totalDuration = (int) (totalDuration / 1000);
			currentDuration = (int) ((((double) progress) / 100) * totalDuration);

			// return current duration in milliseconds
			return currentDuration * 1000;
		}
	}
}
