/**
 * 
 */
package com.manageroid.application.mediator;

import org.puremvc.java.multicore.patterns.mediator.Mediator;

import com.manageroid.application.views.MainActivity;

/**
 * @author Administrator
 *
 */
public class MainMenuActivityMediator extends Mediator {

	/**
	 * 
	 */
	public MainMenuActivityMediator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param mediatorName1
	 * @param viewComponent1
	 */
	public MainMenuActivityMediator(String mediatorName1, Object viewComponent1) {
		super(mediatorName1, viewComponent1);
		// TODO Auto-generated constructor stub
	}
	
	public void update()
	{
		((MainActivity)getViewComponent()).updateList();		
	}

}
