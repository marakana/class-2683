package com.cisco.cyamba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	StatusFragment statusFragment;
	PrefsFragment prefsFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		statusFragment = new StatusFragment();
		prefsFragment = new PrefsFragment();
		
		getActionBar().setHomeButtonEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			getFragmentManager().beginTransaction().remove(prefsFragment)
					.remove(statusFragment).commit();
			return true;
		case R.id.item_status:
			getFragmentManager().beginTransaction()
					.replace(android.R.id.content, statusFragment).commit();

			return true;
		case R.id.item_prefs:
			getFragmentManager().beginTransaction()
					.replace(android.R.id.content, prefsFragment).commit();
			return true;
		default:
			return false;
		}
	}

}
