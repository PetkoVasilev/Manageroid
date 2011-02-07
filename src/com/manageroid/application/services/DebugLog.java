package com.manageroid.application.services;

import android.util.Log;

public class DebugLog
{
	static public void write(String debugOutput)
	{
		// LogCat filter "info"
        Log.i ("info", debugOutput);
	}
}
