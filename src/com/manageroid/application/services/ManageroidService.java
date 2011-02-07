/**
 * 
 */
package com.manageroid.application.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.text.format.Time;

import java.util.Timer;
import java.util.TimerTask;

import com.manageroid.application.proxy.AllTasks;
import com.manageroid.application.proxy.ManageroidTask;

/**
 * This is the main worker class of the application.
 * @author Administrator
 *
 */
public class ManageroidService extends Service {
//	private final static long UPDATE_INTERVAL = 60 * 5 * 1000; // 5 minutes
	private final static long UPDATE_INTERVAL = 10 * 1000; // seconds
	private final static long DELAY_INTERVAL = 0;

	private Timer timer = new Timer();   

	public static Time currentTime = null;
	public static Location currentLocation = null;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		DebugLog.write("service onCreate");
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		_startService();
	}

	/*
     * starting the service
     */
    private void _startService()
    {
    	LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	final ManageroidGPSLocationListener mlocListener = new ManageroidGPSLocationListener();
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
        
        // read once on service start
        AllTasks.load(getApplicationContext());

        timer.scheduleAtFixedRate(
              new TimerTask()
              {
                    public void run()
                    {
                    	boolean mustWriteTasks = false;
                    	currentTime.setToNow();

                    	DebugLog.write("in timer");
                    	currentLocation = mlocListener.currentLocation;
    					if (currentLocation != null)
    					{
	                        double currentLat = currentLocation.getLongitude();
	                        double currentLong = currentLocation.getLatitude();
	                		DebugLog.write(currentLat + " " + currentLong);
    					}

    					for (int i = AllTasks.allMyTasks.size() - 1; i >= 0;--i)
            			{
    						ManageroidTask currentTask =  AllTasks.allMyTasks.get(i);
            				if(currentTime.after(currentTask.expirationDate))
            				{
    							if (currentTask.isActive && currentTask.isRevertable())
    							{
    								currentTask.whatToRevert.undo();
    							}
        						AllTasks.allMyTasks.remove(i);
        						mustWriteTasks = true;
            					continue;
            				}

            				if (currentTask.requirementsAreMet() && !currentTask.isActive)
            				{
            					currentTask.whatToDo.exec();
            					currentTask.isActive = true;
	            				--currentTask.repeat;

	            				if(currentTask.repeat < 0)
            					{
            						AllTasks.allMyTasks.remove(i);
            					}
            					else
            					{
            						AllTasks.allMyTasks.set(i, currentTask);
            					}
	            				mustWriteTasks = true;
            				}
            				else if (!currentTask.requirementsAreMet() && currentTask.isActive)
            				{
            					if (currentTask.isRevertable())
            					{
            						currentTask.whatToRevert.undo();
            					}
            					currentTask.isActive = false;
        						AllTasks.allMyTasks.set(i, currentTask);
        						mustWriteTasks = true;
            				}
            			}

    			        // write on every change
    					if (mustWriteTasks)
    					{
    						AllTasks.archive(getApplicationContext());
    					}
                    }
              }, DELAY_INTERVAL, UPDATE_INTERVAL);
    }

    @Override
	public void onDestroy() {
		super.onDestroy();
	    _shutdownService();
	}
    
    /*
     * shutting down the service
     */
    private void _shutdownService()
    {
      if (timer != null) timer.cancel();
    }

    /* Class Manageroid GPS Location Listener */ 
    public class ManageroidGPSLocationListener implements LocationListener
    {
    	public Location currentLocation = null;

    	public void onLocationChanged(Location newLocation)
    	{
    		currentLocation = newLocation;
    	}

    	public void onProviderDisabled(String provider){}

    	public void onProviderEnabled(String provider) {}

    	public void onStatusChanged(String provider, int status, Bundle extras) {}

    }/* End of Class Manageroid GPS Location Listener */
}

