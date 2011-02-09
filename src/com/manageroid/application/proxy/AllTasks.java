package com.manageroid.application.proxy;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;

import com.manageroid.application.ManageroidApp;

public class AllTasks {
	
	private final static String FILE_NAME = "manageroid_tasks.bin";
	
	public void archive(Context context)
	{
		// not tested
		try
		{
			FileOutputStream fos = context.openFileOutput(FILE_NAME, 0);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(allMyTasks);
			fos.close();
		}
		catch (IOException fnfe) {}
	}
	
	private static AllTasks instance;

	private List<ManageroidTask> allMyTasks;

	private AllTasks() {
		load(ManageroidApp.getContext());
		/*DatabaseSerializer dbs = new DatabaseSerializer(
				ManageroidApp.getContext());
		SQLiteDatabase db = dbs.getReadableDatabase();

		Cursor cursor = db.query(DatabaseSerializer.table1, null, null, null,
				null, null, null);
		
		// TODO: populate the tasks from the SQLite
		// @author:Kiril @date:08/02/11
		setAllMyTasks(new ArrayList<ManageroidTask>());*/
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

	public void load(Context context)
	{
		try
		{
			allMyTasks = null;
			allMyTasks = new ArrayList<ManageroidTask>();
			FileInputStream fos = context.openFileInput(FILE_NAME);
			byte[] readBytes = new byte[1024*1024];  // 1MB should be enough for everybody...
			fos.read(readBytes);
			fos.close();

			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(readBytes));
			try
			{
				allMyTasks = (ArrayList<ManageroidTask>) ois.readObject();
				ois.close();
			}
			catch (ClassNotFoundException cnfe) {}
		}
		catch (IOException ioe) {}
	}
}









