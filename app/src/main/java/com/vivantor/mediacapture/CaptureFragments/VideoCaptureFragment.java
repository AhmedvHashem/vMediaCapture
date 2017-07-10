package com.vivantor.mediacapture.CaptureFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vivantor.mediacapture.R;

public class VideoCaptureFragment extends BaseFragment
{
	public static VideoCaptureFragment newInstance()
	{
		VideoCaptureFragment fragment = new VideoCaptureFragment();

		return fragment;
	}

	public VideoCaptureFragment()
	{
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_video_capture, container, false);
	}


}
