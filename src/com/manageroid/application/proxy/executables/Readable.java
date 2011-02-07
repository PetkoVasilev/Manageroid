package com.manageroid.application.proxy.executables;

import java.io.Serializable;

import org.json.JSONObject;
import org.json.JSONStringer;

/**
 * Classes implementing this interface can be serialized and deserialized to
 * {@link JSONObject}
 * 
 * @author Administrator
 * @see JSONStringer
 * @see Serializable
 */
public interface Readable {

	/**
	 * @return JSON representation of the <code>Readable</code> object
	 */
	public JSONObject getJSON();

	/**
	 * Reconstructs the <code>Readable</code> by given {@link JSONObject}
	 * 
	 * @param json
	 *            String containing the information for reconstruction of the
	 *            class
	 */
	public void reconstructObject(JSONObject json);
}
