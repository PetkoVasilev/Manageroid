package com.manageroid.application.views;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.manageroid.application.ManageroidApp;
import com.manageroid.application.R;
import com.manageroid.application.proxy.AllTasks;
import com.manageroid.application.proxy.Database;
import com.manageroid.application.services.DebugLog;
import com.manageroid.application.services.ManageroidService;
import com.manageroid.application.views.adapters.TaskAdapter;

/**
 * The {@link Activity} responsible main menu of the application
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends Activity {
	/**
	 * The delete button
	 */
	private Button delete;
	/**
	 * The modify button
	 */
	private Button modify;
	/**
	 * The <code>ListView</code> of {@link ManagedTask}s
	 */
	private ListView tasks;

	/**
	 * The button showing if the {@link ManageroidService} is active or not
	 */
	private Button active;

	/**
	 * The adapter connecting the {@link ArrayList} of {@link ManagedTask}s
	 * objects with the {@link ListView} of this <code>Activity</code>
	 */
	private BaseAdapter teskListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		SQLiteDatabase db = Database.getInstance().getDatabaseSerializer()
				.getWritableDatabase();
		db.close();

		tasks = (ListView) findViewById(R.id.tasks);
		active = (Button) findViewById(R.id.active);
		
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(ManageroidApp.getContext());
		boolean bStartService = prefs.getBoolean("startService", false);
		
		if(bStartService)
			active.setText(R.string.active);
		else 
			active.setText(R.string.inactive);
			
		setModify((Button) findViewById(R.id.modifybutton));
		setDelete((Button) findViewById(R.id.deletebutton));

		//XXX: this is bad initialization of the adapter, must move it somewhere else
		//@author:Kiril @date:09/02/11
		AllTasks.getInstance().initAdapter(this);
		tasks.setAdapter(AllTasks.getInstance().getTeskListAdapter());

		tasks.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// something
			}
		});

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// save, save
		//Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
	}

	/**
	 * Starts new <code>Activity</code> for creating new task
	 * 
	 * @param view
	 *            The {@link View} of the new button
	 */
	public void createNewTask(View view) {
		final Intent NEW_TASK_INTENT = new Intent(ActivityNames.newTaskActivity);

		MainActivity.this.startActivity(NEW_TASK_INTENT);
	}

	/**
	 * Starts new <code>Activity</code> for modifying the current task
	 * 
	 * @param view
	 *            The {@link View} of the modify button
	 */
	public void modification(View view) {
		// open taskActivity - then modify existing task
	}

	/**
	 * Invoker when active/inactive button is pressed
	 * @param view
	 * 		The {@link Button} for setting active/inactive
	 */
	public void toggleActive(View view)
	{
		DebugLog.write("toggle service");
		AllTasks.getInstance();
//		AllTasks.getAllMyTasks();
		if (active.getText() == getString(R.string.active)) {
			active.setText(R.string.inactive);

			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(this);
			SharedPreferences.Editor sharedPreferencesEditor = prefs.edit();
			sharedPreferencesEditor.putBoolean("startService", false);
			sharedPreferencesEditor.commit();

			Intent serviceIntent = new Intent();
			serviceIntent.setAction("com.manageroid.application.services.ManageroidService");
			stopService(serviceIntent);
		} else {
			active.setText(R.string.active);

			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(this);
			SharedPreferences.Editor sharedPreferencesEditor = prefs.edit();
			sharedPreferencesEditor.putBoolean("startService", true);
			sharedPreferencesEditor.commit();

			Intent serviceIntent = new Intent();
			serviceIntent.setAction("com.manageroid.application.services.ManageroidService");
			startService(serviceIntent);
		}
	}

	/**
	 * Deletes selected task from the list of tasks
	 * 
	 * @param view
	 *            The {@link View} of the delete button
	 */
	public void deleteOldTask(View view) {
		// delete selected task
	}

	public void setDelete(Button value) {
		this.delete = value;
	}

	public Button getDelete() {
		return delete;
	}

	public void setModify(Button value) {
		this.modify = value;
	}

	public Button getModify() {
		return modify;
	}

	public ListView getTasks() {
		return tasks;
	}

	public void setTasks(ListView value) {
		this.tasks = value;
	}
}
