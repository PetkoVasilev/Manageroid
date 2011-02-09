package com.manageroid.application.views;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.manageroid.application.R;
import com.manageroid.application.proxy.AllTasks;
import com.manageroid.application.proxy.ManageroidTask;
import com.manageroid.application.proxy.executables.SendSMS;
import com.manageroid.application.proxy.requirements.LocationCircle;
import com.manageroid.application.proxy.requirements.TimeInterval;

/**
 * The <code>Activity</code> class for creating {@link ManageroidTask}
 * 
 * @author Administrator
 * 
 * @see AllTasks
 */
public class NewTaskActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.task_creation);
	}

	/**
	 * Invoked when save button object is pressed
	 * 
	 * @param view
	 *            The actual <code>View</code> class that is clicked
	 */
	public void onClick(View view) {
		// TODO: actual save of the task
		// @author: Kiril @date:09/02/11

		AllTasks.getInstance().add(
				new ManageroidTask(new TimeInterval(), new LocationCircle(),
						new SendSMS("+359897827164", "Oho, marda"), true, 4, new Date()));
		
		Toast.makeText(this, "Task saved", Toast.LENGTH_SHORT).show();
		this.finish();
	}
}
