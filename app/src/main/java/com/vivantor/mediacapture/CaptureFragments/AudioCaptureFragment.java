package com.vivantor.mediacapture.CaptureFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vivantor.mediacapture.UI.VAudioCapture;
import com.vivantor.mediacapture.PreviewFragments.PreviewAudioCaptureFragment;
import com.vivantor.mediacapture.R;

public class AudioCaptureFragment extends Fragment
{
	VAudioCapture audioCapture;

	public static AudioCaptureFragment newInstance()
	{
		AudioCaptureFragment fragment = new AudioCaptureFragment();

		return fragment;
	}

	public AudioCaptureFragment()
	{
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_audio_capture, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		audioCapture = (VAudioCapture) view.findViewById(R.id.audioCapture);
		audioCapture.setOnVAudioCaptureListener(OnVAudioCapture);
	}

	VAudioCapture.OnVAudioCaptureListener OnVAudioCapture = new VAudioCapture.OnVAudioCaptureListener()
	{
		@Override
		public void OnStart()
		{

		}

		@Override
		public void OnStop(String filePath)
		{
			getActivity().getFragmentManager().beginTransaction().replace(R.id.mediaFragments, PreviewAudioCaptureFragment.newInstance(filePath)).commit();
		}
	};
}