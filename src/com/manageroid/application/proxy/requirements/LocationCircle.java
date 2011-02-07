package com.manageroid.application.proxy.requirements;

import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;

import com.manageroid.application.proxy.ManageroidTask;
import com.manageroid.application.proxy.executables.Readable;
import com.manageroid.application.services.ManageroidService;

/**
 * Class that contains location requirements for a {@link ManageroidTask}
 * 
 * @author Administrator
 * @see Accomplishable
 */
public class LocationCircle implements Accomplishable, Readable {

	private static final String longtigude = "x";
	private static final String latitude = "y";
	private static final String radiusStr = "radius";

	private double x;
	private double y;
	private double radius;

	@Override
	public boolean requirementsAreMet()
	{
		if (ManageroidService.currentLocation == null) return false;

		Location currentLocation = ManageroidService.currentLocation;
		float distance[] = new float[1];
		Location.distanceBetween(y, x, currentLocation.getLatitude(), currentLocation.getLongitude(), distance);

		return distance[0] < radius;
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
			x = json.getDouble(longtigude);
			y = json.getDouble(latitude);
			radius = json.getDouble(radiusStr);
		} catch (JSONException e) {
			// TODO:Logger something
			// @author:Kiril @date:06/02/11
		}
	}

}
