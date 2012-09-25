package com.cisco.cyamba;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class StatusActivity extends Activity implements OnClickListener {
	Button buttonUpdate;
	EditText editStatus;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        
        editStatus = (EditText) findViewById(R.id.edit_status);
        buttonUpdate = (Button) findViewById(R.id.button_update);
        
        buttonUpdate.setOnClickListener( this );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_status, menu);
        return true;
    }

	public void onClick(View v) {
		String status = editStatus.getText().toString();
		Log.d("Yamba", "onClicked with status: "+status);
	}
}
