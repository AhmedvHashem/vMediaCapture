package com.vivantor.mediacapture.CaptureFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vivantor.mediacapture.R;

public class ImageCaptureFragment extends BaseFragment
{
	public static ImageCaptureFragment newInstance()
	{
		ImageCaptureFragment fragment = new ImageCaptureFragment();

		return fragment;
	}

	public ImageCaptureFragment()
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
		return inflater.inflate(R.layout.fragment_image_capture, container, false);
	}


}
