package com.manageroid.application.proxy.requirements;

import org.json.JSONObject;

/**
 * Marks given class can accomplish its requirements
 * 
 * @author Administrator
 * 
 */
public interface Accomplishable {
	/**
	 * @return
	 * 		True, if all requirements of the class are met.
	 * 		False, otherwise.
	 */
	public boolean requirementsAreMet();
	
	/**
	 * @return
	 * 		JSON representation of the <code>Accomplishable</code> object
	 */
	public JSONObject getJSON();
	
	/**
	 * Reconstructs the <code>Accomplishable</code> by given {@link JSONObject}
	 * @param json
	 * 		String containing the information for reconstruction of the class
	 * @return
	 * 		Accomplishable instance, for the concrete class
	 */
	public void reconstructObject(JSONObject json);
}
