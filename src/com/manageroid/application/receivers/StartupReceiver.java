package com.manageroid.application.receivers;

import com.manageroid.application.ManageroidApp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Receiver invoked at BOOT_COMPLETE event
 * 
 * @author Administrator
 * 
 */
public class StartupReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub

		// I suppose that there is some bugs here

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(ManageroidApp.getContext());
		boolean bStartService = prefs.getBoolean("startService", false);

		if (bStartService) {
			Intent serviceIntent = new Intent();
			serviceIntent
					.setAction("com.manageroid.application.services.ManageroidService");
			context.startService(serviceIntent);
		}
	}

}
