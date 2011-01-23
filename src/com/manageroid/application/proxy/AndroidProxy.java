package com.manageroid.application.proxy;

import org.puremvc.java.multicore.patterns.proxy.Proxy;

import android.content.Context;

public class AndroidProxy extends Proxy {
	private Context context;

	public AndroidProxy(String name, Context con) {
		super(name);
		
		this.setContext(con);
	}

	public void setContext(Context con) {
		this.context = con;
	}

	public Context getContext() {
		return context;
	}
	

}
