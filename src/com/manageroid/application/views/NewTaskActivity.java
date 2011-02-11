package com.manageroid.application.views;

import java.util.Date;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

	private String name;
	private String SMSText;

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
						new SendSMS("+359897827164", SMSText), true, 4,
						new Date()));

		Toast.makeText(this, "Task saved", Toast.LENGTH_SHORT).show();
		this.finish();
	}

	private static final int PICK_CONTACT = 7;

	/**
	 * Invoked when send SMS radio button is selected
	 * 
	 * @param view
	 *            View of the {@link RadioButton}
	 * 
	 * @see RadioGroup
	 * @see Intent
	 */
	public void sendFuckingSMS(View view) {
		Intent intent = new Intent(Intent.ACTION_PICK,
				ContactsContract.Contacts.CONTENT_URI);
		startActivityForResult(intent, PICK_CONTACT);
		// TODO: open contacts chose to SMS
		// @author Kiril @date 11/02/11
	}

	Button smsApprovedButton;
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case (PICK_CONTACT):
			if (resultCode == Activity.RESULT_OK) {
				Uri contactData = data.getData();
				Cursor c = managedQuery(contactData, null, null, null, null);

				if (c.moveToFirst()
						&& c.getInt(c
								.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) == 1) {
					name = c.getString(c
							.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
					Toast.makeText(getApplicationContext(), name,
							Toast.LENGTH_SHORT).show();
				}

			}
		
			final Dialog dialog = new Dialog(this);

			dialog.setContentView(R.layout.sms_dialog);
			dialog.setTitle("SMS containment");
			dialog.setCanceledOnTouchOutside(true);
			dialog.show();
			
			smsApprovedButton = (Button)dialog.findViewById(R.id.sms_approved);
			smsApprovedButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.cancel();
					SMSText = (String) smsApprovedButton.getText();
				}
			});

			

			break;
		}
	}
}
