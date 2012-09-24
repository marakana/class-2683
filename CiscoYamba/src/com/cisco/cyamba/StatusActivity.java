package com.cisco.cyamba;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

// Test
public class StatusActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_status, menu);
        return true;
    }
}
