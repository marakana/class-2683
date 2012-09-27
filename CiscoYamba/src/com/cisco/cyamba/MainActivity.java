package com.cisco.cyamba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_status:
			startActivity(new Intent(this, StatusActivity.class)
					.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
			return true;
		case R.id.item_prefs:
			startActivity(new Intent(this, PrefsActivity.class));
			return true;
		default:
			return false;
		}
	}

}
