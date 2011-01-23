package com.manageroid.application.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Receiver invoked at BOOT_COMPLETE event
 * @author Administrator
 *
 */
public class StartupReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub
		
		Intent serviceIntent = new Intent();
		serviceIntent.setAction("com.manageroid.application.services.ManageroidService");
		context.startService(serviceIntent);
	}

}