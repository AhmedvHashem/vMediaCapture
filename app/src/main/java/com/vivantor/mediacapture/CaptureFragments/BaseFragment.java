package com.vivantor.mediacapture.CaptureFragments;

import android.content.Context;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment
{
	private OnMediaCaptureListener mListener;

	public BaseFragment()
	{

	}

	@Override
	public void onAttach(Context context)
	{
		super.onAttach(context);

		if (context instanceof OnMediaCaptureListener)
		{
			mListener = (OnMediaCaptureListener) context;
		}
		else
		{
			throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach()
	{
		super.onDetach();

		mListener = null;
	}

	public interface OnMediaCaptureListener
	{
		void onStart();
		void onPause();
		void onStop();
	}
}
