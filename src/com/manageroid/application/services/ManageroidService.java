/**
 * 
 */
package com.manageroid.application.services;

import com.manageroid.application.R;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import java.util.Timer;
import java.util.TimerTask;

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

        timer.scheduleAtFixedRate(
              new TimerTask()
              {
                    public void run()
                    {
                    	long currentTime = System.currentTimeMillis();

                    	DebugLog.write("in timer");
                        double currentLat = mlocListener.currentLat;
                        double currentLong = mlocListener.currentLong;
                		DebugLog.write(currentLat + " " + currentLong);

                		if (currentLat != 0 &&currentLong != 0)
                		{
                			//do stuff
                		}
                    }
              }, DELAY_INTERVAL, UPDATE_INTERVAL);
    }

    @Override
	public void onDestroy() {
		// TODO Auto-generated method stub
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
    	public double currentLat = 0;
    	public double currentLong = 0;

    	public void onLocationChanged(Location loc)
    	{
    		currentLat = loc.getLatitude();
    		currentLong = loc.getLongitude();
    	}

    	public void onProviderDisabled(String provider)
    	{
    	}

    	public void onProviderEnabled(String provider)
    	{
    	}

    	public void onStatusChanged(String provider, int status, Bundle extras)
    	{
    	}

    }/* End of Class Manageroid GPS Location Listener */
}

