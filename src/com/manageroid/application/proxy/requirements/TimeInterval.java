/**
 * 
 */
package com.manageroid.application.proxy.requirements;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.manageroid.application.proxy.ManageroidTask;
import com.manageroid.application.proxy.executables.Readable;
import com.manageroid.application.services.ManageroidService;

/**
 * Class containing time interval requirement for {@link ManageroidTask}
 * @author Administrator
 *
 * @see Accomplishable
 */
public class TimeInterval implements Accomplishable, Readable, Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = 9237641923746L; //random keyboard pounding
	private static final String startStr = "start";
	private static final String endStr = "start";

	public Date start;
	public Date end;

	@Override
	public boolean requirementsAreMet()
	{
		if (ManageroidService.currentTime == null) return false;

		String currentTimeString = ManageroidService.currentTime.toString().substring(9, 15);
		String startTimeString = start.toString().substring(9, 15);
		String endTimeString = end.toString().substring(9, 15);

		if (start.before(end))
		{
			return currentTimeString.compareTo(startTimeString) >= 0 && currentTimeString.compareTo(endTimeString) <= 0;
		}
		return currentTimeString.compareTo(startTimeString) >= 0 || currentTimeString.compareTo(endTimeString) <= 0;
	}

	@Override
	public JSONObject getJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put(startStr, start);
			json.put(endStr, end);
		} catch (JSONException e) {
			//TODO:Logger something
			//@author:Kiril @date:06/02/11
			e.printStackTrace();
		}

		return json;
	}

	@Override
	public void reconstructObject(JSONObject json) {
		try {
			start = (Date) json.get(startStr);
			end = (Date) json.get(endStr);
		} catch (JSONException e) {
			//TODO:Logger something
			//@author:Kiril @date:06/02/11
		}
	}

}
