package com.manageroid.application.proxy;

import com.manageroid.application.ManageroidApp;

public class Database {
	private static Database instance = null;

	public DatabaseSerializer databaseSerializer;
	
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
