package com.manageroid.application.proxy;

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
import com.manageroid.application.services.DebugLog;

public class AllTasks {
	
	private final static String FILE_NAME = "manageroid_tasks.bin";

	private static ArrayList<ManageroidTask> allMyTasks = new ArrayList<ManageroidTask>();
	
	private static AllTasks instance;

	public static void archive(Context context)
	{
		try
		{
			FileOutputStream fos = context.openFileOutput(FILE_NAME, 0);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(allMyTasks);
			oos.flush();
			fos.close();
		}
		catch (IOException fnfe) {
			DebugLog.write("in archive " + fnfe.toString());
		}
	}

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

	public static AllTasks getInstance()
	{
		if (instance == null)
			instance = new AllTasks();
		return instance;
	}

	public void setAllMyTasks(ArrayList<ManageroidTask> value) {
		allMyTasks = value;
	}

	public static List<ManageroidTask> getAllMyTasks() {
		return Collections.unmodifiableList(allMyTasks);
	}

	public static void load(Context context)
	{
		DebugLog.write("in AllTasks.load() start");
		try
		{
			allMyTasks = null;
			FileInputStream fis = context.openFileInput(FILE_NAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			try
			{
				allMyTasks = (ArrayList<ManageroidTask>) ois.readObject();
			}
			catch (ClassNotFoundException cnfe) {
				DebugLog.write("allMyTasks null pointer on read");
			}
			ois.close();
			fis.close();
		}
		catch (IOException ioe) {
			DebugLog.write("in AllTasks.load() " + ioe.toString());
		}

		if (allMyTasks == null)
			allMyTasks = new ArrayList<ManageroidTask>();
	}
	
	public static void add(ManageroidTask newTask)
	{
		allMyTasks.add(newTask);
	}
	
	public static void remove(int i)
	{
		DebugLog.write("remove " + i);
		allMyTasks.remove(i);
	}

	public static void set(int i,  ManageroidTask newTask)
	{
		allMyTasks.set(i, newTask);
	}
	
	public static boolean isEmpty()
	{
		return allMyTasks == null;
	}
	
	public static int size()
	{
		if (allMyTasks != null)
		{
			return allMyTasks.size();
		}
		else
		{
			return 0;
		}
	}
}









