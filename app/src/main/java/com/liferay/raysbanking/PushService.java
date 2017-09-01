package com.liferay.raysbanking;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.liferay.mobile.push.PushNotificationsService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Igor Matos
 */

public class PushService extends PushNotificationsService {

	@Override
	public void onPushNotification(JSONObject pushNotification) {
		super.onPushNotification(pushNotification);

		Log.e("RAYBANK", "onPush " + pushNotification.toString());

		try {
			Intent intent = new Intent(this, MainActivity.class);

			String formUrl = pushNotification.has(_formUrl) ? pushNotification.getString(_formUrl) : null;
			if (formUrl != null) {
				intent = new Intent(this, FormActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra(_formUrl, formUrl);
			} else if (pushNotification.has(_creditLimit)) {
				intent = new Intent(this, AccountActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				int cardType = pushNotification.has(_cardType) ? pushNotification.getInt(_cardType) : -1;
				intent.putExtra(_cardType, cardType);
				intent.putExtra(_creditLimit, pushNotification.getLong(_creditLimit));
			} else {
				intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			}

			String message = pushNotification.has(_message) ? pushNotification.getString(_message) : "";
			intent.putExtra(_message, message);

			PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

			Notification notification = new NotificationCompat.Builder(this)
				.setContentText(message)
				.setContentTitle("Ray's Banking")
				.setSmallIcon(R.drawable.ic_credit_card_black)
				.setContentIntent(pendingIntent)
				.build();

			NotificationManagerCompat manager = NotificationManagerCompat.from(
				this);

			manager.notify(message.hashCode(), notification);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private static String _creditLimit = "creditLimit";
	private static String _cardType = "cardType";
	private static String _message = "message";
	private static String _formUrl = "formUrl";
}