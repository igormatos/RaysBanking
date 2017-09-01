package com.liferay.raysbanking;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.raysbanking.databinding.ActivityAccountBinding;

public class AccountActivity extends AppCompatActivity {

	ActivityAccountBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.activity_account);

		String name = SessionContext.getCurrentUser().getFirstName();
		String approvedMessage = String.format(getString(R.string.account_approved_message), name);
		binding.message.setText(approvedMessage);

		String fullName = SessionContext.getCurrentUser().getFullName();
		binding.name.setText(fullName);

		Double creditLimit = getIntent().getDoubleExtra("creditLimit", 10.000);
		binding.creditLimit.setText(String.valueOf(creditLimit));

		int cardType = getIntent().getIntExtra("cardType", 0);
		if (cardType == 0) {
			binding.cardName.setText("Cliente Master Platium Diamond Digital Experience");
		} else {
			binding.cardName.setText("Cliente Plastikum Silver");
		}


	}
}
