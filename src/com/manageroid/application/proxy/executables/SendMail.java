package com.manageroid.application.proxy.executables;

import java.io.Serializable;

public class SendMail implements Executable, Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 345245457546L;
	private String Email;
	private String Text;
	
	@Override
	public void exec() {
		// TODO Auto-generated method stub
	}

	public SendMail(String newEmail, String newText)
	{
		Email = newEmail;
		Text = newText;
	}
}
