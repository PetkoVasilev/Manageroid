package com.manageroid.application.services;

import android.util.Log;

public class DebugLog
{
	static public void write(Object debugOutput)
	{
		// LogCat filter "info"
		// we can remove this class later
        Log.i ("info", debugOutput.toString());
	}
}
