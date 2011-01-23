/**
 * 
 */
package com.manageroid.application.command;

import java.util.List;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

import com.manageroid.application.mediator.MainMenuActivityMediator;
import com.manageroid.application.mediator.MediatorNames;
import com.manageroid.application.proxy.ProxyNames;
import com.manageroid.application.proxy.TasksProxy;

/**
 * @author Administrator
 *
 */
public class RefreshTasksCommand extends SimpleCommand {

	/**
	 * 
	 */
	public RefreshTasksCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(INotification notification) {
		// TODO Auto-generated method stub
		super.execute(notification);
		
		//TODO: first register 
       	TasksProxy tasksProxy = (TasksProxy) getFacade().retrieveProxy( ProxyNames.TASKS_PROXY);
		List<String> tasks = tasksProxy.getTaskElementsList();
    	
		//TODO: first register
		MainMenuActivityMediator mainMenuActivityMediator = (MainMenuActivityMediator) getFacade().retrieveMediator(MediatorNames.MAIN_MENU_ACTIVITY);
		mainMenuActivityMediator.update();
	}
}
