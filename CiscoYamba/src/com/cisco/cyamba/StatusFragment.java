package com.cisco.cyamba;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.marakana.android.yamba.clientlib.YambaClient;

public class StatusFragment extends Fragment implements OnClickListener,
		TextWatcher {
	private static final int MAX_LENGTH = 140;
	private Button buttonUpdate;
	private EditText editStatus;
	private TextView textCount;
	private static StatusUpdateTask statusUpdateTask = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_status, container, false);

		editStatus = (EditText) view.findViewById(R.id.edit_status);
		buttonUpdate = (Button) view.findViewById(R.id.button_update);
		textCount = (TextView) view.findViewById(R.id.text_count);

		textCount.setText(Integer.valueOf(MAX_LENGTH).toString());
		buttonUpdate.setOnClickListener(this);
		editStatus.addTextChangedListener(this);

		return view;
	}

	@Override
	public void onStop() {
		super.onStop();
		if (statusUpdateTask != null) {
			statusUpdateTask.cancel(true);
			statusUpdateTask = null;
		}
	}

	/** OnClickListener callback */
	public void onClick(View v) {
		String status = editStatus.getText().toString();

		if (status != null && !status.isEmpty()) {
			statusUpdateTask = new StatusUpdateTask();
			statusUpdateTask.execute(status);
		}

		Log.d("Yamba", "onClicked with status: " + status);
	}

	class StatusUpdateTask extends AsyncTask<String, Void, String> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(getActivity(), "Posting...",
					"please wait...");
		}

		@Override
		protected String doInBackground(String... params) {
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(getActivity());
			String username = prefs.getString("username", "");
			String password = prefs.getString("password", "");
			YambaClient yambaClient = new YambaClient(username, password);
			try {
				yambaClient.postStatus(params[0]);
				return "Successfully updated";
			} catch (Exception e) {
				return "Failed to update";
			}
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.cancel();
			Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
		}
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

		if (size > 25) {
			textCount.setTextColor(Color.DKGRAY);
		} else {
			textCount.setTextColor(Color.RED);
		}
	}

}
