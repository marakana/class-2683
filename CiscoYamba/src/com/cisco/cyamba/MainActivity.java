package com.cisco.cyamba;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	public static final String REFRESH_ACTION = "com.cisco.yamba.REFRESH";
	private StatusFragment statusFragment;
	private PrefsFragment prefsFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		statusFragment = new StatusFragment();
		prefsFragment = new PrefsFragment();

		getActionBar().setHomeButtonEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/** Handles all action bar actions. */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			getFragmentManager().beginTransaction().remove(prefsFragment)
					.remove(statusFragment).commit();
			return true;
		case R.id.item_status:
			swapFragment(statusFragment);
			return true;
		case R.id.item_prefs:
			swapFragment(prefsFragment);
			return true;
		case R.id.item_refresh:
			startService(new Intent(REFRESH_ACTION));
			return true;
		case R.id.item_purge:
			getContentResolver().delete(StatusContract.CONTENT_URI, null, null);
			return true;
		default:
			return false;
		}
	}

	private void swapFragment(Fragment fragment) {
		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();

		// If fragment is already registered with FragmentManager, show it
		if (fragment.isAdded()) {
			transaction.show(fragment);
			Log.d("MainActivity", "showing: " + fragment.getTag());
		}
		// Else add it to the view
		else {
			transaction.replace(R.id.main_content, fragment, fragment
					.getClass().getSimpleName());
			Log.d("MainActivity", "replacing: "
					+ fragment.getClass().getSimpleName());
		}

		// Add this transaction to the back stack
		transaction.addToBackStack(null);

		transaction.commit();
	}
}
