package com.manageroid.application.proxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.manageroid.application.ManageroidApp;

public class AllTasks {

	private static AllTasks instance;

	private List<ManageroidTask> allMyTasks;

	private AllTasks() {
		DatabaseSerializer dbs = new DatabaseSerializer(
				ManageroidApp.getContext());
		SQLiteDatabase db = dbs.getReadableDatabase();

		Cursor cursor = db.query(DatabaseSerializer.table1, null, null, null,
				null, null, null);
		
		// TODO: populate the tasks from the SQLite
		// @author:Kiril @date:08/02/11
		setAllMyTasks(new ArrayList<ManageroidTask>());
	}

	public static AllTasks getInstance() {
		if (instance == null)
			instance = new AllTasks();
		return instance;
	}

	public void setAllMyTasks(ArrayList<ManageroidTask> value) {
		this.allMyTasks = value;
	}

	public List<ManageroidTask> getAllMyTasks() {
		return Collections.unmodifiableList(allMyTasks);
	}

}
