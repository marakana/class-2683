package com.cisco.cyamba;

import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RefreshReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// context.startService(new Intent(RefreshService.REFRESH_ACTION));
		Log.d("RefreshReceiver", "onReceived at " + new Date());

		// Setup the operation
		Intent refreshIntent = new Intent(RefreshService.REFRESH_ACTION);
		PendingIntent operation = PendingIntent.getService(context, 0,
				refreshIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		// Setup alarm
		long refreshInterval = 180000; // 3 minutes
		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		alarmManager.setInexactRepeating(AlarmManager.RTC,
				System.currentTimeMillis(), refreshInterval, operation);
	}
}
