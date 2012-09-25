package com.cisco.cyamba;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StatusActivity extends Activity implements 
		//OnClickListener,
		TextWatcher {
	private static final int MAX_LENGTH = 140;
//	private Button buttonUpdate;
	private EditText editStatus;
	private TextView textCount;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);

		editStatus = (EditText) findViewById(R.id.edit_status);
//		buttonUpdate = (Button) findViewById(R.id.button_update);
		textCount = (TextView) findViewById(R.id.text_count);

		textCount.setText(Integer.valueOf(MAX_LENGTH).toString());
//		buttonUpdate.setOnClickListener(this);
		editStatus.addTextChangedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_status, menu);
		return true;
	}

	/** OnClickListener callback */
	public void onUpdateButtonClick(View v) {
		String status = editStatus.getText().toString();
		
		// TODO Do some work here...
		
		Toast.makeText(this, "Successfully updated", Toast.LENGTH_LONG).show();
		Log.d("Yamba", "onClicked with status: " + status);
	}

	/** TextWatcher callbacks */
	public void afterTextChanged(Editable s) {
	}

	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	public void onTextChanged(CharSequence s, int start, int before, int count) {
		int size = MAX_LENGTH - editStatus.getText().length();
		textCount.setText(Integer.valueOf(size).toString());
		
		if(size>25) {
			textCount.setTextColor( Color.DKGRAY );
		} else {
			textCount.setTextColor( Color.RED );
		}
	}
}
