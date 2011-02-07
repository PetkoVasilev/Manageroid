package com.manageroid.application.proxy;

import com.manageroid.application.proxy.executables.Executable;
import com.manageroid.application.proxy.executables.Revertable;
import com.manageroid.application.proxy.requirements.LocationCircle;
import com.manageroid.application.proxy.requirements.TimeInterval;

import android.text.format.Time;

public class ManageroidTask {
	public TimeInterval timeRule = null;
	public LocationCircle locationRule = null;

	public Executable whatToDo;
	public Revertable whatToRevert = null;

	public boolean isActive = false;
	public int repeat = 0;
	
	public Time expirationDate = new Time();

	public boolean isRevertable()
	{
		return whatToRevert != null;
	}

	public boolean hasLocationComponent()
	{
		return locationRule != null;
	}

	public boolean hasTimeComponent()
	{
		return timeRule != null;
	}

	public boolean requirementsAreMet()
	{
		return (hasLocationComponent() && locationRule.requirementsAreMet() || !hasLocationComponent())
				&& (hasTimeComponent() && timeRule.requirementsAreMet() || !hasTimeComponent());
	}
	
	public ManageroidTask()
	{
		// default far in the future
		expirationDate.set (31, 11, 2032);
		// TODO
	}
}
