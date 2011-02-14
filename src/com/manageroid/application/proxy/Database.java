package com.manageroid.application.proxy;

import android.database.sqlite.SQLiteDatabase;

import com.manageroid.application.ManageroidApp;

/**
 * Singleton class responsible for database handling
 * @author Administrator
 *
 * @see SQLiteDatabase
 */
public class Database {
	private static Database instance = null;

	private DatabaseSerializer databaseSerializer;
	
	/**
	 * @return
	 * 		{@link DatabaseSerializer} object responsible for handling database
	 */
	public DatabaseSerializer getDatabaseSerializer() {
		return databaseSerializer;
	}

	/**
	 * @return
	 * 		The Singleton instance of this object
	 */
	public static Database getInstance() {
		if (instance == null)
			instance = new Database();

		return instance;
	}
	
	private Database()
	{
		databaseSerializer = new DatabaseSerializer(ManageroidApp.getContext());
	}
	
}
