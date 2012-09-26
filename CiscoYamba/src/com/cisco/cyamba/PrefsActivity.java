package com.cisco.cyamba;

import android.app.Activity;
import android.os.Bundle;

public class PrefsActivity extends Activity {
	PrefsFragment prefsFragment = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (prefsFragment == null)
			prefsFragment = new PrefsFragment();

		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, prefsFragment).commit();
	}
}
