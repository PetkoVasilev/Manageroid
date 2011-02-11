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
import android.widget.BaseAdapter;

import com.manageroid.application.ManageroidApp;
import com.manageroid.application.services.DebugLog;
import com.manageroid.application.views.adapters.TaskAdapter;

/**
 * Actually this class is a wrapper of ArrayList<ManageroidTask>
 * @author Administrator
 *
 */
public class AllTasks {
	
	private final static String FILE_NAME = "manageroid_tasks.bin";

	private ArrayList<ManageroidTask> allMyTasks = new ArrayList<ManageroidTask>();
	
	private static AllTasks instance;
	
	private BaseAdapter teskListAdapter;
	
	public void archive(Context context)
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

	public void initAdapter(Context context)
	{
		//XXX:possible bug, this context looks suspicious
		//@author: Kiril @date:09/02/11
		teskListAdapter = new TaskAdapter(context, allMyTasks);
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

	public List<ManageroidTask> getAllMyTasks() {
		return Collections.unmodifiableList(allMyTasks);
	}

	public void load(Context context)
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
	
	public void add(ManageroidTask newTask)
	{
		allMyTasks.add(newTask);
		teskListAdapter.notifyDataSetChanged();
	}
	
	public void remove(int i)
	{
		DebugLog.write("remove " + i);
		allMyTasks.remove(i);
		teskListAdapter.notifyDataSetChanged();
	}

	public void set(int i,  ManageroidTask newTask)
	{
		allMyTasks.set(i, newTask);
		teskListAdapter.notifyDataSetChanged();
	}
	
	public boolean isEmpty()
	{
		return allMyTasks == null;
	}
	
	public int size()
	{
		if (allMyTasks != null)
		{
			return allMyTasks.size();
		}
		return 0;
	}

	public void setTeskListAdapter(BaseAdapter teskListAdapter) {
		this.teskListAdapter = teskListAdapter;
	}

	public BaseAdapter getTeskListAdapter() {
		return teskListAdapter;
	}
}









