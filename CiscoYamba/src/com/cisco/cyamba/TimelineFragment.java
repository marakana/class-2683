package com.cisco.cyamba;

import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

public class TimelineFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {
	private static final String[] FROM = { StatusContract.Columns.USER,
			StatusContract.Columns.MESSAGE, StatusContract.Columns.CREATED_AT };
	private static final int[] TO = { R.id.text_user, R.id.text_message,
			R.id.text_created_at };
	private SimpleCursorAdapter adapter;

	private static final ViewBinder VIEW_BINDER = new ViewBinder() {

		public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
			if (view.getId() == R.id.text_created_at) {
				long timestamp = cursor.getLong(columnIndex);
				CharSequence relTime = DateUtils
						.getRelativeTimeSpanString(timestamp);
				((TextView) view).setText(relTime);
				return true;
			} else
				return false;
		}

	};

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		adapter = new SimpleCursorAdapter(getActivity(), R.layout.row, null,
				FROM, TO, 0);
		adapter.setViewBinder(VIEW_BINDER);
		setListAdapter(adapter);

		getLoaderManager().initLoader(0, null, this);
	}

	// --- LoaderCallbacks ---

	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new CursorLoader(getActivity(), StatusContract.CONTENT_URI,
				null, null, null, StatusContract.DEFAULT_SORT_ORDER);
	}

	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		adapter.swapCursor(cursor);
	}

	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor(null);
	}

}
