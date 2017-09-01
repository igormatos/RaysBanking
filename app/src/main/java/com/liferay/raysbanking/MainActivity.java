package com.liferay.raysbanking;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.raysbanking.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

	ActivityMainBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

		if (!SessionContext.isLoggedIn()) {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			finish();
		}

		String name = SessionContext.getCurrentUser().getFirstName();
		String helloText = String.format(getString(R.string.hello_text), name);
		binding.helloText.setText(helloText);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}

}