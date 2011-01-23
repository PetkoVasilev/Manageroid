package com.manageroid.application.facade;

import org.puremvc.java.multicore.patterns.facade.Facade;

import android.app.Activity;

import com.manageroid.application.command.NotificationNames;
import com.manageroid.application.command.PrepareMainMenu;
import com.manageroid.application.command.RefreshTasksCommand;

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

		registerCommand(NotificationNames.STARTUP_MAINMENU, new PrepareMainMenu());
		registerCommand(NotificationNames.REFRESH_MAINMENU, new RefreshTasksCommand());
	}
	
	/**
	 * Start the <code>Activity</code> initialization sequence.
	 * 
	 * @param activity
	 * 		A reference to the <code>Activity</code> to initialize.
	 */
	public void startup( Activity activity )
	{
		sendNotification(  NotificationNames.STARTUP_MAINMENU, activity  );
	}

}
