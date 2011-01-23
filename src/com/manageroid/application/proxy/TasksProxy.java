/**
 * 
 */
package com.manageroid.application.proxy;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

/**
 * @author Administrator
 *
 */
public class TasksProxy extends AndroidProxy {

	//TODO: must be task class, not String
	private List<String> taskElementsList;
	
	/**
	 * @param name
	 * @param con
	 */
	public TasksProxy(String name, Context con) {
		super(name, con);

		setTaskElementsList(new ArrayList<String>());
		
		getTaskElementsList().add("one");
		getTaskElementsList().add("two");
	}

	public void setTaskElementsList(List<String> value) {
		this.taskElementsList = value;
	}

	public List<String> getTaskElementsList() {
		return taskElementsList;
	}

}
