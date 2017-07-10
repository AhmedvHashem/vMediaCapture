package com.vivantor.mediacapture;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vivantor.mediacapture.CaptureFragments.AudioCaptureFragment;

public class MediaCaptureActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media_capture);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		if (findViewById(R.id.mediaFragments) != null)
		{
			// However, if we're being restored from a previous state,
			// then we don't need to do anything and should return or else
			// we could end up with overlapping fragments.
			if (savedInstanceState != null)
			{
				return;
			}

			// Create a new Fragment to be placed in the activity layout
			AudioCaptureFragment fragment = AudioCaptureFragment.newInstance();

			// In case this activity was started with special instructions from an
			// Intent, pass the Intent's extras to the fragment as arguments
//			fragment.setArguments(getIntent().getExtras());

			// Add the fragment to the 'fragment_container' FrameLayout
			getFragmentManager().beginTransaction().add(R.id.mediaFragments, fragment).commit();
		}
	}
}