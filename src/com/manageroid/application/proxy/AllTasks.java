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
import android.widget.ListView;

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
	
	/**
	 * Serialize all tasks on the local file system
	 */
	public void archive()
	{
		try
		{
			FileOutputStream fos = ManageroidApp.getContext().openFileOutput(FILE_NAME, 0);
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
		load();
		
		
		
		/*DatabaseSerializer dbs = new DatabaseSerializer(
				ManageroidApp.getContext());
		SQLiteDatabase db = dbs.getReadableDatabase();

		Cursor cursor = db.query(DatabaseSerializer.table1, null, null, null,
				null, null, null);
		
		// TODO: populate the tasks from the SQLite
		// @author:Kiril @date:08/02/11
		setAllMyTasks(new ArrayList<ManageroidTask>());*/
	}

	/**
	 * Initialize adapter from {@link AllTasks} to {@link ListView} of the tasks
	 * @param context
	 * 		Context where the adapter must be added
	 * @see TaskAdapter
	 */
	public void initAdapter(Context context)
	{
		teskListAdapter = new TaskAdapter(context, allMyTasks);
	}
	
	/**
	 * @return
	 * 		Singleton instance of the {@link AllTasks} class
	 */
	public static AllTasks getInstance()
	{
		if (instance == null)
			instance = new AllTasks();
		return instance;
	}

	/**
	 * Setter method
	 * @param value
	 * 		New <code>ArrayList</code> {@link ManageroidTask} instances
	 */
	public void setAllMyTasks(ArrayList<ManageroidTask> value) {
		allMyTasks = value;
	}

	/**
	 * Getter method
	 * @return
	 * 		Unmodifiable collections of all tasks
	 */
	public List<ManageroidTask> getAllMyTasks() {
		return Collections.unmodifiableList(allMyTasks);
	}

	/**
	 * Load all tasks from the local file system
	 */
	public void load()
	{
		DebugLog.write("in AllTasks.load() start");
		try
		{
			allMyTasks = null;
			FileInputStream fis = ManageroidApp.getContext().openFileInput(FILE_NAME);
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
	
	/**
	 *  Adds new task to the list of already existing tasks
	 * @param newTask
	 * 		New {@link ManageroidTask} to be added to all tasks
	 */
	public void add(ManageroidTask newTask)
	{
		allMyTasks.add(newTask);
		teskListAdapter.notifyDataSetChanged();
	}
	
	/**
	 * Remove a task from the task list
	 * @param i
	 * 		The position of the removed element
	 */
	public void remove(int i)
	{
		DebugLog.write("remove " + i);
		allMyTasks.remove(i);
		teskListAdapter.notifyDataSetChanged();
	}

	/**
	 * Change a task with new task
	 * @param i
	 * 		position of the old task
	 * @param newTask
	 * 		New task to be added instad of the old task
	 */
	public void set(int i,  ManageroidTask newTask)
	{
		allMyTasks.set(i, newTask);
		teskListAdapter.notifyDataSetChanged();
	}
	
	/**
	 * @return
	 * 		True, if there is no tasks in the tasks list
	 */
	public boolean isEmpty()
	{
		return allMyTasks == null;
	}
	
	/**
	 * @return
	 * 		The size of the tasks list
	 */
	public int size()
	{
		if (allMyTasks != null)
		{
			return allMyTasks.size();
		}
		return 0;
	}

	/**
	 * Getter method
	 * @return
	 * 		The {@link TaskAdapter} of the list of tasks
	 */
	public BaseAdapter getTeskListAdapter() {
		return teskListAdapter;
	}
}









