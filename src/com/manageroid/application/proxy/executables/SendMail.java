package com.manageroid.application.proxy.executables;

import java.io.Serializable;

import com.manageroid.application.ManageroidApp;
import com.manageroid.application.services.DebugLog;

import android.content.Intent;
import android.widget.Toast;

public class SendMail implements Executable, Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 345245457546L;
	private String email = null;
	private String text = null;
	private String subject = null;
	
	@Override
	public void exec()
	{
		if (email != null && text != null)
		{
			Intent emailIntent = new Intent(Intent.ACTION_SEND);
			emailIntent.setType("text/plain");
			emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{email});
			if (subject != null)
			{
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
			}
			emailIntent.putExtra(Intent.EXTRA_TEXT   , text);

			try
			{
			    ManageroidApp.getContext().startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			}
			catch (android.content.ActivityNotFoundException ex)
			{
				DebugLog.write("email send failed: " + ex);
			}
		}
	}

	public SendMail(String newEmail, String newText, String newSubject)
	{
		email = newEmail;
		text = newText;
		subject = newSubject;
	}
}
