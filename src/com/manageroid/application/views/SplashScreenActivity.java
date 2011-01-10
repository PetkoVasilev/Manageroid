package com.manageroid.application.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.manageroid.application.R;

public class SplashScreenActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void invokeButton(View view)
    {
    	Toast.makeText(this, "it is working", Toast.LENGTH_LONG).show();
    }
}