package com.manageroid.application.proxy.executables;

import java.io.Serializable;

import com.manageroid.application.ManageroidApp;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

public class SetAirplaneMode implements Executable, Revertable, Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 6990254870836L;

	@Override
	public void undo()
	{
		Context context = ManageroidApp.getContext();
		boolean isEnabled = Settings.System.getInt(context.getContentResolver(),
													Settings.System.AIRPLANE_MODE_ON, 0) == 1;
		if (isEnabled)
		{
			Settings.System.putInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0);
			Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
			intent.putExtra("state", 0);
			context.sendBroadcast(intent);
		}
	}

	@Override
	public void exec()
	{
		Context context = ManageroidApp.getContext();
		boolean isEnabled = Settings.System.getInt(context.getContentResolver(),
													Settings.System.AIRPLANE_MODE_ON, 0) == 1;
		if (!isEnabled)
		{
			Settings.System.putInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 1);
			Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
			intent.putExtra("state", 1);
			context.sendBroadcast(intent);
		}
	}
}
