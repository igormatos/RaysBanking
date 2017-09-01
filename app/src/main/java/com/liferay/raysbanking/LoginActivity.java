package com.liferay.raysbanking;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.liferay.mobile.android.auth.basic.BasicAuthentication;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;
import com.liferay.mobile.push.Push;
import com.liferay.mobile.screens.auth.login.LoginListener;
import com.liferay.mobile.screens.context.User;
import com.liferay.raysbanking.databinding.ActivityLoginBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements LoginListener {

	ActivityLoginBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

		binding.loginScreenlet.setListener(this);
		binding.signUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), SignUpActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onLoginSuccess(User user) {
		Toast.makeText(this, "Login feito com sucesso", Toast.LENGTH_SHORT).show();

		_registerForPush();

		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
//		finish();
	}

	private void _registerForPush() {
//		EditText emailField = binding.loginScreenlet.findViewById(R.id.liferay_login);
//		EditText passwordField = binding.loginScreenlet.findViewById(R.id.liferay_password);
//		String email = emailField.getText().toString();
//		String password = passwordField.getText().toString();
		String email = "test@liferay.com";
		String password = "test";
		BasicAuthentication authentication = new BasicAuthentication(email, password);

		Session session = new SessionImpl(getString(R.string.liferay_server), authentication);

		Push push = Push.with(session);
		push.withPortalVersion(70);

		try {
			push.onSuccess(new Push.OnSuccess() {
					@Override
					public void onSuccess(JSONObject jsonObject) {
						try {
							String token = jsonObject.getString("token");
							Log.e("RAYBANK", "onSuccess" + token);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				})
				.onFailure(new Push.OnFailure() {
					@Override
					public void onFailure(Exception e) {
						Log.e("RAYBANK", "onFailure" + e.getMessage());
					}
				})
				.register(getApplicationContext(), getString(R.string.firebase_sender_id));
		} catch (Exception e) {
			Log.e("RAYBANK", "catch" + e.getMessage());
		}
	}

	@Override
	public void onLoginFailure(Exception e) {
		Toast.makeText(this, "Falha no login", Toast.LENGTH_SHORT).show();
		Log.d("RAYBANK", "Falha no Login" + String.valueOf(e));
	}
}