/**
 * 
 */
package com.manageroid.application.views.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.manageroid.application.R;
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
	 * 
	 * @param tasks
	 *            {@link ArrayList} of {@link ManageroidTask} objects - fix that
	 *            stupid comment
	 * 
	 */
	public TaskAdapter(Context con, List<ManageroidTask> tasks) {
		this.context = con;
		this.tasks = tasks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return tasks.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return tasks.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		// XXX: what this is supposed to be
		return position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final ViewWrapper viewWrapper;
		
		View view = convertView;

		if (view == null) {
			LayoutInflater li = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			view = li.inflate(R.layout.task, null);
			
			viewWrapper = new ViewWrapper(view);
			view.setTag(viewWrapper);

		}
		else
		{
			viewWrapper = (ViewWrapper) view.getTag();
		}

		viewWrapper.getTitle().setText(((ManageroidTask)getItem(position)).description);
		viewWrapper.getIcon().setImageResource(R.drawable.globe_icon);
		
		return view;
	}

	class ViewWrapper {
		private View view;
		private ImageButton icon;
		private TextView title;

		ViewWrapper(View v) {
			view = v;
		}

		TextView getTitle() {
			if (title == null) {
				title = (TextView) view.findViewById(R.id.task_description);
			}
			return title;
		}

		ImageButton getIcon() {
			if (icon == null) {
				icon = (ImageButton) view.findViewById(R.id.task_icon);
			}
			return icon;
		}
	}

}
