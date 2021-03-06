package com.cisco.cyamba;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class StatusProvider extends ContentProvider {

	private static final int STATUS_DIR = 1;
	private static final int STATUS_ITEM = 2;

	private DbHelper dbHelper;

	private static final UriMatcher uriMatcher;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(StatusContract.AUTHORITY, StatusContract.PATH,
				STATUS_DIR);
		uriMatcher.addURI(StatusContract.AUTHORITY, StatusContract.PATH + "/#",
				STATUS_ITEM);
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case STATUS_DIR:
			return StatusContract.CONTENT_TYPE;
		case STATUS_ITEM:
			return StatusContract.CONTENT_ITEM_TYPE;
		default:
			return null;
		}
	}

	@Override
	public boolean onCreate() {
		dbHelper = new DbHelper(getContext());
		return (dbHelper == null) ? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(DbHelper.TABLE);

		switch (uriMatcher.match(uri)) {
		case STATUS_DIR:
			break;
		case STATUS_ITEM:
			qb.appendWhere(StatusContract.Columns._ID + "="
					+ uri.getLastPathSegment());
			break;
		default:
			throw new IllegalArgumentException("Unsupported Uri: " + uri);
		}

		Cursor cursor = qb.query(db, projection, selection, selectionArgs,
				null, null, sortOrder);

		// Register for changes to this cursor
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Uri result = null;

		// Assert correct uri
		if (uriMatcher.match(uri) != STATUS_DIR) {
			throw new IllegalArgumentException("Unsupported Uri: " + uri);
		}

		// Insert data to DB
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long id = db.insertWithOnConflict(DbHelper.TABLE, null, values,
				SQLiteDatabase.CONFLICT_IGNORE);

		if (id > 0) {
			result = ContentUris.withAppendedId(uri, id);
			getContext().getContentResolver().notifyChange(result, null);
		}

		return result;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int count = 0;
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		switch (uriMatcher.match(uri)) {
		case STATUS_DIR:
			db.update(DbHelper.TABLE, values, selection, selectionArgs);
			break;
		case STATUS_ITEM:
			String id = uri.getLastPathSegment();
			String whereClause = StatusContract.Columns._ID
					+ "="
					+ id
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection
							+ ')' : "");
			db.update(DbHelper.TABLE, values, whereClause, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unsupported Uri: " + uri);
		}

		if (count > 0) {
			getContext().getContentResolver().notifyChange(uri, null);
		}

		return count;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int count = 0;
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		switch (uriMatcher.match(uri)) {
		case STATUS_DIR:
			count = db.delete(DbHelper.TABLE, selection, selectionArgs);
			break;
		case STATUS_ITEM:
			String id = uri.getLastPathSegment();
			String whereClause = StatusContract.Columns._ID
					+ "="
					+ id
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection
							+ ')' : "");
			count = db.delete(DbHelper.TABLE, whereClause, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unsupported Uri: " + uri);
		}

		if (count > 0) {
			getContext().getContentResolver().notifyChange(uri, null);
		}

		return count;
	}
}
