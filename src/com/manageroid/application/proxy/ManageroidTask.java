package com.manageroid.application.proxy;

import java.io.Serializable;
import java.util.Date;

import com.manageroid.application.proxy.executables.Executable;
import com.manageroid.application.proxy.executables.Revertable;
import com.manageroid.application.proxy.requirements.Accomplishable;
import com.manageroid.application.proxy.requirements.LocationCircle;
import com.manageroid.application.proxy.requirements.TimeInterval;

/**
 * Base class for a given task - contains requirements and executables
 * @author Administrator
 *
 *@see Accomplishable
 *@see Executable
 */
public class ManageroidTask implements Serializable {

    private static final long serialVersionUID = 879082348790L;
	public TimeInterval timeRule = null;
	public LocationCircle locationRule = null;

	public Executable action = null;

	public boolean isActive = false;
	public int repeat = 0;
	
	public Date expirationDate = null;
	
	public String description;

	public void exec()
	{
		action.exec();
	}

	public void revert()
	{
		if (this.isRevertable())
		{
			((Revertable)action).undo();
		}
	}

	public boolean isRevertable()
	{
		return action instanceof Revertable;
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
	
	/**
	 * Creates empty task
	 */
	public ManageroidTask()
	{
		// a task without "action" is not valid, use only for testing
		expirationDate = new Date(2032, 12, 31);
	}
	
	/**
	 * Creates new task
	 * @param newTimeRule
	 * @param newLocationRule
	 * @param newAction
	 * @param newIsActive
	 * @param newRepeat
	 * @param newExpirationDate
	 */
	public ManageroidTask(TimeInterval newTimeRule, LocationCircle newLocationRule, Executable newAction, boolean newIsActive, int newRepeat, Date newExpirationDate)
	{
		if (newTimeRule != null)
		{
			timeRule = newTimeRule;
		}

		if (newLocationRule != null)
		{
			locationRule = newLocationRule;
		}

		if (newAction != null)
		{
			action = newAction;
		}

		isActive = newIsActive;
		repeat = newRepeat;

		if (newExpirationDate != null)
		{
			expirationDate = new Date(newExpirationDate.getTime());
		}
		else
		{
			// default far in the future
			expirationDate = new Date(2032, 12, 31);
		}
		
		description = "created new task, a";
	}
}
