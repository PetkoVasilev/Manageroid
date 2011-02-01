package com.manageroid.application.views;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.manageroid.application.R;

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
	 * The adapter connecting the {@link ArrayList} of {@link ManagedTask}s
	 * objects with the {@link ListView} of this <code>Activity</code>
	 */
	private ArrayAdapter<String> teskListAdapter;

	/**
	 * The list of tasks TODO: this is not supposed to be a String, it must be
	 * {@link ManagedTask}
	 */
	private ArrayList<String> list = new ArrayList<String>() {{
		add("XZ13s");
		add("AB21/X");
		add("YYLEX");
	 }};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		tasks = (ListView) findViewById(R.id.tasks);
		setModify((Button) findViewById(R.id.modifybutton));
		setDelete((Button) findViewById(R.id.deletebutton));

		teskListAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);

		tasks.setAdapter(teskListAdapter);

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
		Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// save, save
		Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
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
