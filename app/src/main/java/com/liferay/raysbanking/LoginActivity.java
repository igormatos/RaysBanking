package com.liferay.raysbanking;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.liferay.mobile.screens.auth.login.LoginListener;
import com.liferay.mobile.screens.context.User;
import com.liferay.raysbanking.databinding.ActivityLoginBinding;

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
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	@Override
	public void onLoginFailure(Exception e) {
		Toast.makeText(this, "Falha no login", Toast.LENGTH_SHORT).show();
	}
}
