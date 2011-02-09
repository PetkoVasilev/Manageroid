package com.manageroid.application.proxy.executables;

import java.io.Serializable;

public class SendSMS implements Executable, Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 879123456934L;
	private String contact;
	private String number;
	private String text;

	@Override
	public void exec() {
		// TODO Auto-generated method stub
		
	}

	public SendSMS(String newContact, String newNumber, String newText)
	{
		contact = newContact;
		number = newNumber;
		text = newText;
	}
}
