package com.manageroid.application.proxy.executables;

import java.io.Serializable;
import java.util.ArrayList;

import com.manageroid.application.services.DebugLog;

import android.telephony.SmsManager;

public class SendSMS implements Executable, Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 879123456934L;
	private String address = null;
	private String text = null;

	@Override
	public void exec()
	{
		if (text != null && address != null)
		{
			try
			{
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendMultipartTextMessage(address, null, smsManager.divideMessage(text), null, null);
				DebugLog.write("send sms success: " + address + " ## " + text);
			}
			catch (Exception smse)
			{
				DebugLog.write("send sms failed: " + smse);
			}
		}
	}

	public SendSMS(String newAddress, String newText)
	{
		address = newAddress;
		text = newText;
	}
}
