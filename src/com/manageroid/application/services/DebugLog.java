package com.manageroid.application.services;

import java.util.logging.Logger;

import android.util.Log;

/**
 * Utility class for logging message to the LogCat
 * @author Administrator
 *
 */
public class DebugLog
{
	/**
	 * Writes in the Log
	 * @param debugOutput
	 * 		Object to be written
	 * 
	 * @see Logger
	 */
	static public void write(Object debugOutput)
	{
		// LogCat filter "info"
		// we can remove this class later
        Log.i ("info", debugOutput.toString());
	}
}
