package com.manageroid.application.proxy.executables;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;

import com.manageroid.application.ManageroidApp;
import com.manageroid.application.R;

public class SetAlarm implements Executable
{
	private static final int ALARM_ID = 1;
	
	@Override
    public void exec()
    {
		Context context = ManageroidApp.getContext();
		NotificationManager manger = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    	Notification notification = new Notification(R.drawable.logo, "Manageroid alarm", System.currentTimeMillis());

    	PendingIntent contentIntent = PendingIntent.getActivity(context, 0, null, 0);
    	String contextTitle = "Manageroid Task Alarm";
    	String contextText = "";
    	notification.setLatestEventInfo(context, contextTitle, contextText, contentIntent);

    	notification.flags =  Notification.DEFAULT_LIGHTS |  Notification.DEFAULT_SOUND
    							| Notification.DEFAULT_VIBRATE | Notification.FLAG_AUTO_CANCEL;

    	manger.notify(ALARM_ID, notification);
    }
}



