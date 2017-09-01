package com.liferay.raysbanking;

import com.liferay.mobile.push.PushNotificationsReceiver;

/**
 * @author Igor Matos
 */

public class PushReceiver extends PushNotificationsReceiver {
	@Override
	public String getServiceClassName() {
		return PushService.class.getName();
	}
}
