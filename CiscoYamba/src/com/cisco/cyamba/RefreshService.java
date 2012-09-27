package com.cisco.cyamba;

import java.util.List;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClient.Status;
import com.marakana.android.yamba.clientlib.YambaClientException;

public class RefreshService extends IntentService {
	private static final String TAG = "RefreshService";
	private YambaClient yambaClient = null;

	public RefreshService() {
		super(TAG);
	}

	@Override
	public void onCreate() {
		super.onCreate();

		// Initialize yambaClient
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		String username = prefs.getString("username", "");
		String password = prefs.getString("password", "");
		Log.d(TAG, String.format("YambaClient with: %s/%s", username, password));
		yambaClient = new YambaClient(username, password);

		Log.d(TAG, "onCreated");
	}

	@Override
	public void onHandleIntent(Intent intent) {
		Log.d(TAG, "onHandleIntent");

		ContentValues values = new ContentValues();
		try {
			List<Status> timeline = yambaClient.getTimeline(20);
			for (Status status : timeline) {

				// Insert into status provider
				values.put(StatusContract.Columns._ID, status.getId());
				values.put(StatusContract.Columns.CREATED_AT, status
						.getCreatedAt().getTime());
				values.put(StatusContract.Columns.USER, status.getUser());
				values.put(StatusContract.Columns.MESSAGE, status.getMessage());
				getContentResolver().insert(StatusContract.CONTENT_URI, values);

				Log.d(TAG,
						String.format("%s: %s", status.getUser(),
								status.getMessage()));
			}
		} catch (YambaClientException e) {
			Log.e(TAG, "Failed to fetch timeline", e);
			e.printStackTrace();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroyed");
	}
}
