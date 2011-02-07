/**
 * 
 */
package com.manageroid.application.proxy.requirements;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.manageroid.application.proxy.ManageroidTask;

/**
 * Class containing time interval requirement for {@link ManageroidTask}
 * @author Administrator
 *
 *@see Accomplishable
 */
public class TimeInterval implements Accomplishable {
	private static final String startStr = "start";
	private static final String endStr = "start";

	public Date start;
	public Date end;

	@Override
	public boolean requirementsAreMet() {
		//TODO: add implementation
		//@author:Kiril @date:07/06/11
		return false;
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
