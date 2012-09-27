package com.cisco.cyamba;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class StatusProvider extends ContentProvider {

	private static final int STATUS_DIR = 1;
	private static final int STATUS_ITEM = 2;
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
		switch(uriMatcher.match(uri)) {
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
}
