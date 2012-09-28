package com.cisco.fib;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText in;
	TextView out;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		in = (EditText) findViewById(R.id.in);
		out = (TextView) findViewById(R.id.out);
	}

	public void onClick(View v) {
		long n = Long.parseLong(in.getText().toString());

		long start = System.currentTimeMillis();
		long resultJ = FibLib.fibJ(n);
		long timeJ = System.currentTimeMillis() - start;
		out.append(String.format("\nfibJ(%d)=%d (%d ms)", n, resultJ, timeJ));

		start = System.currentTimeMillis();
		long resultN = FibLib.fibN(n);
		long timeN = System.currentTimeMillis() - start;
		out.append(String.format("\nfibN(%d)=%d (%d ms)", n, resultN, timeN));
	}
}
