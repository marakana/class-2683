package com.cisco.cyamba;

import java.util.Date;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RefreshReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		context.startService(new Intent(RefreshService.REFRESH_ACTION));
		Log.d("RefreshReceiver", "onReceived at " + new Date());
	}

}
