package com.manageroid.application.facade;

import org.puremvc.java.multicore.patterns.facade.Facade;

/**
 * Concrete <code>Facade</code> for this application.
 * @author Administrator
 *
 */
public class ManageroidFacade extends Facade {

	public final static String KEY = "facade";

	/**
	 * Construct the <code>Facade</code> multiton instance for this
	 * <code>Activity</code>.
	 * 
	 * @param multitonKey1
	 *            Multiton key for this <code>Facade</code> multiton instance.
	 */
	private ManageroidFacade(String multitonKey1) {
		super(multitonKey1);
	}
	
	/**
	 * <code>ApplicationFacade</code> singleton fabrication method
	 * implementation.
	 *
	 * @param multitonKey
	 * 		Multiton key of the facade instance to get.
	 */
	public static synchronized ManageroidFacade getInstance( String multitonKey )
	{
		if( instanceMap.get(multitonKey) == null )
			instanceMap.put( multitonKey, new ManageroidFacade( multitonKey ) );

		return (ManageroidFacade) instanceMap.get(multitonKey);
	}

	@Override
	protected void initializeController() {
		super.initializeController();

		//registerCommand(NotificationNames.STARTUP_MAINMENU, new PrepareMainMenu());
		//registerCommand(NotificationNames.REFRESH_MAINMENU, new RefreshTasksCommand());
		//registerCommand(NotificationNames.CREATE_NEW_TASK, new CreateNewTaskCommand());
	}

}
