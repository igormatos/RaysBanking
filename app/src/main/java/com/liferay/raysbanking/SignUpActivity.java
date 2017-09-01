package com.liferay.raysbanking;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.liferay.mobile.screens.auth.signup.SignUpListener;
import com.liferay.mobile.screens.context.User;
import com.liferay.raysbanking.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity implements SignUpListener {

	ActivitySignUpBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
		binding.signUpScreenlet.setListener(this);
	}

	@Override
	public void onSignUpFailure(Exception e) {
		Toast.makeText(this, "falha no signup", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onSignUpSuccess(User user) {
		Toast.makeText(this, "Signup feito com sucesso", Toast.LENGTH_SHORT).show();
	}
}
