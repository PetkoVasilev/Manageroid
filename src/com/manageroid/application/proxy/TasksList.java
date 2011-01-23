package com.manageroid.application.proxy;

import java.util.ArrayList;
import java.util.List;

public class TasksList {

	private static TasksList instance;

	private List<String> listElements;

	private TasksList() {
		setListElements(new ArrayList<String>());
	}

	public static TasksList getInstance() {
		if (instance == null)
			instance = new TasksList();
		return instance;
	}

	public void setListElements(List<String> list) {
		this.listElements = list;
	}

	public List<String> getListElements() {
		return listElements;
	}

	
}
