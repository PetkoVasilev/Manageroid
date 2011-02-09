/**
 * 
 */
package com.manageroid.application.views.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.manageroid.application.proxy.ManageroidTask;

/**
 * @author Administrator
 *
 */
public class TaskAdapter extends BaseAdapter {

	private List<ManageroidTask> tasks;
	private Context context;
	/**
	 * Default constructor
	 * @param tasks 
	 * 		{@link ArrayList} of {@link ManageroidTask} objects - fix that stupid comment
	 * 
	 */
	public TaskAdapter(Context con, List<ManageroidTask> tasks) {
		this.context = con;
		this.tasks = tasks;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return tasks.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return tasks.get(position);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		//XXX: what this is supposed to be
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Button btn = new Button(context);
		
		btn.setText(((ManageroidTask)getItem(position)).toString());
		return btn;
	}

}
