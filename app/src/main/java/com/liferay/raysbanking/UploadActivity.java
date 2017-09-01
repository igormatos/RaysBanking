package com.liferay.raysbanking;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liferay.raysbanking.databinding.ActivityUploadBinding;

public class UploadActivity extends AppCompatActivity {

	ActivityUploadBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.activity_upload);


	}
}
