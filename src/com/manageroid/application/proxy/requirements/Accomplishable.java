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
	
}
