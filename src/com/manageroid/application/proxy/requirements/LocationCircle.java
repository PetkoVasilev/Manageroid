package com.manageroid.application.proxy.requirements;

import org.json.JSONException;
import org.json.JSONObject;

import com.manageroid.application.proxy.ManageroidTask;

/**
 * Class that contains location requirements for a {@link ManageroidTask}
 * 
 * @author Administrator
 * @see Accomplishable
 */
public class LocationCircle implements Accomplishable {

	private static final String longtigude = "x";
	private static final String latitude = "y";
	private static final String radiusStr = "radius";

	private long x;
	private long y;
	private long radius;

	@Override
	public boolean requirementsAreMet() {
		// TODO: add implementation
		// @author:Kiril @date:07/06/11
		return false;
	}

	@Override
	public JSONObject getJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put(longtigude, x);
			json.put(latitude, y);
			json.put(radiusStr, radius);
		} catch (JSONException e) {
			// TODO:Logger something
			// @author:Kiril @date:06/02/11
		}
		return json;
	}

	@Override
	public void reconstructObject(JSONObject json) {
		try {
			x = json.getLong(longtigude);
			y = json.getLong(latitude);
			radius = json.getLong(radiusStr);
		} catch (JSONException e) {
			// TODO:Logger something
			// @author:Kiril @date:06/02/11
		}
	}

}
