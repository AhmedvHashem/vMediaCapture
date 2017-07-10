package com.vivantor.mediacapture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button captureAudioBTN = (Button) findViewById(R.id.captureAudioBTN);
		captureAudioBTN.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		startActivityForResult(new Intent(MainActivity.this, MediaCaptureActivity.class), 1001);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK)
		{
			if (requestCode == 1001)
			{
				String filePath = data.getStringExtra("MediaFilePath");

				Toast.makeText(this, filePath, Toast.LENGTH_LONG).show();
			}
		}
	}
}
