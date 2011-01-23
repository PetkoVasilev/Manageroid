package com.manageroid.application.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.manageroid.application.R;
import com.manageroid.application.facade.ManageroidFacade;
import com.manageroid.application.proxy.TasksList;

/**
 * @author Administrator
 * 
 */
public class MainActivity extends Activity {

	private ListView tasks;
	private Button delete;
	private Button modify;

	private ArrayAdapter<String> teskList;

	private int itemSelected = -1;

	/**
	 * 
	 */
	public MainActivity() {
		// TODO Auto-generated constructor stub
	}

	protected void startApp()
	{
		/*ManageroidFacade.removeCore("org.puremvc.java.demos.android.currencyconverter.about.AboutActivity");

		facade = AboutActivityFacade.getInst( ActivityNames.MAIN_MENU );		
		facade.startup(this);*/
		ManageroidFacade.getInstance(ManageroidFacade.KEY).startup(this);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		setTasks((ListView) findViewById(R.id.tasks));
		setModify((Button) findViewById(R.id.modifybutton));
		setDelete((Button) findViewById(R.id.deletebutton));

		TasksList.getInstance().getListElements().add("one");
		TasksList.getInstance().getListElements().add("two");

		teskList = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, TasksList.getInstance()
						.getListElements());

		getTasks().setAdapter(teskList);

		getTasks().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// refresh selection
				refreshButtons();
			}
		});

		startApp();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// save, save
		Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
	}

	public void refreshButtons() {
		getModify().setVisibility(View.VISIBLE);
		getDelete().setVisibility(View.VISIBLE);
	}

	public void createNewTask(View view) {
		TasksList.getInstance().getListElements().add("three");
	}

	public void modification(View view) {
		Toast.makeText(this, "modification", Toast.LENGTH_LONG).show();
	}

	public void deleteOldTask(View view) {
		TasksList.getInstance().getListElements().remove(itemSelected);
	}
	
	public void updateList()
	{
		teskList.notifyDataSetChanged();
	}

	public ListView getTasks() {
		return tasks;
	}

	public void setTasks(ListView value) {
		this.tasks = value;
	}

	public void setDelete(Button value) {
		this.delete = value;
	}

	public Button getDelete() {
		return delete;
	}

	public void setModify(Button value) {
		this.modify = value;
	}

	public Button getModify() {
		return modify;
	}
}
