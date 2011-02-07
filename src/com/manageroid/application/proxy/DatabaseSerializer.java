package com.manageroid.application.proxy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseSerializer extends SQLiteOpenHelper {

	private static final String DATABASE = "manageroid.db";
	private static final int DB_VERSION = 1;

	/**
	 * Constructor - associates the helper with the given data base, if one does
	 * not exists, it creates it.
	 * 
	 * @param context
	 *            the Context within which to work
	 */
	public DatabaseSerializer(Context context) {
		super(context, DATABASE, null, DB_VERSION);
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
	}

	public static final String table1 = "tasks";

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE "
				+ table1
				+ "(task_id INTEGER PRIMARY KEY, requirement_id INTEGER, executable_id INTEGER );");
		db.execSQL("CREATE TABLE reqs(req_id INTEGER PRIMARY KEY, req_json STRING);");
		db.execSQL("CREATE TABLE execs(exec_id INTEGER PRIMARY KEY, exec_json STRING);");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/*
		 * Log.v(TAG, "Upgrading DB from " + oldVersion + " to " + newVersion);
		 * 
		 * if (newVersion <= oldVersion) { return; }
		 * 
		 * switch (oldVersion) { case 1: db.execSQL("ALTER TABLE " +
		 * Tables.VIEW_STATES + " ADD COLUMN " + ViewStateColumns.USER_ID +
		 * " INTEGER NOT NULL DEFAULT (0)"); //$FALL-THROUGH$ case 2:
		 * db.execSQL("ALTER TABLE " + Tables.LIBRARY_ISSUES + " ADD COLUMN " +
		 * LibraryIssuesColumns.PACKAGE_SIZE + " INTEGER NOT NULL DEFAULT (0)");
		 * //$FALL-THROUGH$ case 3: db.execSQL("ALTER TABLE " +
		 * Tables.LIBRARY_ISSUES + " ADD COLUMN " +
		 * LibraryIssuesColumns.PAGE_COUNT + " INTEGER NOT NULL DEFAULT (0)"); }
		 */
		// db.execSQL("DROP TABLE IF EXISTS " + Tables.LIBRARY_ISSUES);
		// db.execSQL("DROP TABLE IF EXISTS " +
		// Tables.LIBRARY_PUBLICATIONS);
		// onCreate(db);
	}
}