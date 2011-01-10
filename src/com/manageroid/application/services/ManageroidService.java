/**
 * 
 */
package com.manageroid.application.services;

import com.manageroid.application.R;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * This is the main worker class of the application.
 * @author Administrator
 *
 */
public class ManageroidService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		Toast.makeText(this, R.string.service_created, Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		
		Toast.makeText(this, R.string.service_started, Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		Toast.makeText(this, R.string.service_destroyed, Toast.LENGTH_LONG).show();
	}

}
