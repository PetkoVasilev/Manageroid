package com.manageroid.application.proxy.executables;

import java.io.Serializable;

import com.manageroid.application.ManageroidApp;

import android.content.Context;
import android.media.AudioManager;

public class SetRingerMode implements Executable, Revertable, Serializable
{
	/**
     * 
     */
    private static final long serialVersionUID = 45625695694700L;
    private int toRingerMode = 0;
    private int fromRingerMode = 0;
    
	@Override
    public void undo()
    {
		Context context = ManageroidApp.getContext();
		AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		int currentRungerMode = audioManager.getRingerMode();
		if (currentRungerMode != fromRingerMode)
		{
			audioManager.setRingerMode(fromRingerMode);
		}
    }

	@Override
    public void exec()
    {
		Context context = ManageroidApp.getContext();
		AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		int currentRungerMode = audioManager.getRingerMode();
		if (currentRungerMode != toRingerMode)
		{
			audioManager.setRingerMode(toRingerMode);
		}
    }
	
	public SetRingerMode(int newToMode, int newFormMode)
	{
		toRingerMode = newToMode;
		fromRingerMode = newFormMode;
	}
}
