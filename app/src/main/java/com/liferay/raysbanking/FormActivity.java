package com.liferay.raysbanking;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.raysbanking.databinding.ActivityFormBinding;

import java.util.Map;

public class FormActivity extends AppCompatActivity {

	ActivityFormBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.activity_form);

		binding.webview.setWebViewClient(new WebViewClient());

		String formUrl = getIntent().getStringExtra("formUrl");

		if (formUrl != null) {
			WebSettings webSettings = binding.webview.getSettings();
			webSettings.setJavaScriptEnabled(true);
			binding.webview.loadUrl(formUrl);
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}

}
