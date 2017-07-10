package com.vivantor.mediacapture.PreviewFragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vivantor.mediacapture.CaptureFragments.AudioCaptureFragment;
import com.vivantor.mediacapture.R;
import com.vivantor.mediacapture.UI.VAudioPlayerO;

public class PreviewAudioCaptureFragment extends Fragment
{
	Button saveBTN, discardBTN;
	VAudioPlayerO audioPlayer;

	String mediaFilePath;

	public static PreviewAudioCaptureFragment newInstance(String mediaFilePath)
	{
		PreviewAudioCaptureFragment fragment = new PreviewAudioCaptureFragment();
		fragment.mediaFilePath = mediaFilePath;
		return fragment;
	}

	public PreviewAudioCaptureFragment()
	{
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_preview_audio_capture, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		saveBTN = (Button) view.findViewById(R.id.saveBTN);
		discardBTN = (Button) view.findViewById(R.id.discardBTN);
		audioPlayer = (VAudioPlayerO) view.findViewById(R.id.audioPlayer);

		saveBTN.setOnClickListener(OnSaveClicked);
		discardBTN.setOnClickListener(OnDiscardClicked);

		audioPlayer.SetFilePath(mediaFilePath);
	}

	View.OnClickListener OnSaveClicked = new View.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			Intent data = new Intent();
			data.putExtra("MediaFilePath", mediaFilePath);
			getActivity().setResult(Activity.RESULT_OK, data);
			getActivity().finish();
		}
	};

	View.OnClickListener OnDiscardClicked = new View.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			getActivity().getFragmentManager().beginTransaction().replace(R.id.mediaFragments, AudioCaptureFragment.newInstance()).commit();
		}
	};
}
