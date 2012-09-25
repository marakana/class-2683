package com.cisco.cyamba;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class StatusActivity extends Activity {
	Button buttonUpdate;
	EditText editStatus;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        
        buttonUpdate = (Button) findViewById(R.id.button_update);
        editStatus = (EditText) findViewById(R.id.edit_status);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_status, menu);
        return true;
    }
}
