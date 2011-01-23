/**
 * 
 */
package com.manageroid.application.command;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

import android.app.Activity;
import android.widget.ListView;

import com.manageroid.application.R;
import com.manageroid.application.mediator.MainMenuActivityMediator;
import com.manageroid.application.mediator.MediatorNames;
import com.manageroid.application.mediator.TaskListMediator;

/**
 * @author Administrator
 * 
 */
public class PrepareMainMenu extends SimpleCommand {

	@Override
	public void execute(INotification notification) {
		// TODO Auto-generated method stub
		super.execute(notification);
		
		Activity activity = (Activity) notification.getBody();
		MainMenuActivityMediator mainMenuActivityMediator = new MainMenuActivityMediator( MediatorNames.MAIN_MENU_ACTIVITY, activity );
		getFacade().registerMediator( mainMenuActivityMediator );
		
       /*	Loading loading = (Loading) activity.findViewById(R.id.LoadingLayout);
       	Converter converter = (Converter) activity.findViewById(R.id.ConverterLayout);

       	ConverterActivityMediator converterActivityMediator = new ConverterActivityMediator( MediatorNames.CONVERTER_ACTIVITY, activity );

       	LoadingMediator loadingMediator = new LoadingMediator( MediatorNames.LOADING, loading );
       	ConverterMediator converterMediator = new ConverterMediator( MediatorNames.CONVERTER, converter );
*/
		
		ListView taskList = (ListView) activity.findViewById(R.id.tasks);
		TaskListMediator loadingMediator = new TaskListMediator( MediatorNames.TASKS_LIST_VIEW, taskList ); 	
        getFacade().registerMediator( loadingMediator );
        
        //getFacade().registerMediator( converterMediator );
	}

}
