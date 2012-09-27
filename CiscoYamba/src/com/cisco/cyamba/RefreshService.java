package com.cisco.cyamba;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import com.marakana.android.yamba.clientlib.YambaClient;

public class RefreshService extends Service {
	private static final String TAG = "RefreshService";
	private YambaClient yambaClient;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		// Initialize yambaClient
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		String username = prefs.getString("username", "");
		String password = prefs.getString("password", "");
		yambaClient = new YambaClient(username, password);

		Log.d(TAG, "onCreated");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStarted");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroyed");
	}
}
