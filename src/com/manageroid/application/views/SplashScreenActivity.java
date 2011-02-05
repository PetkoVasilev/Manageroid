package com.manageroid.application.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.manageroid.application.R;

/**
 * The class responsible for the splash screen
 * 
 * @author Administrator
 * 
 */
public class SplashScreenActivity extends Activity implements OnClickListener {

	/**
	 * The {@link ImageView} of the logo of the application
	 */
	private ImageView logo;

	/**
	 * The {@link Handler} object of this <code>Activity</code>
	 */
	private Handler handler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		setLogo((ImageView) findViewById(R.id.logo));
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		handler.postDelayed(fadeInAnimation, 500);
		handler.postDelayed(startMainActivity, 3000);
	}

	@Override
	protected void onDestroy() {
		super.onPause();

		// XXX:
		handler.removeCallbacks(fadeInAnimation);
		handler.removeCallbacks(startMainActivity);
	}

//	@Override
	public void onClick(View v) {
		handler.removeCallbacks(fadeInAnimation);
		handler.removeCallbacks(startMainActivity);

		// XXX: possible nondeterministic behavior here
		//Kiril
		handler.post(startMainActivity);
	}

	/**
	 * Task for fade in animation of the logo
	 */
	private Runnable fadeInAnimation = new Runnable() {

//		@Override
		public void run() {
			Animation fadeIn = new AlphaAnimation(0.0f, 1.0f);
			fadeIn.setDuration(1500);

			getLogo().setVisibility(View.VISIBLE);
			getLogo().startAnimation(fadeIn);
			// add code here
		}
	};

	/**
	 * Task for stopping the current activity and starting the {@link MainActivity}
	 */
	private Runnable startMainActivity = new Runnable() {

//		@Override
		public void run() {
			// TODO Auto-generated method stub
			// new intent(main activity)
			final Intent MAIN_MENU_ACTIVITY_INTENT = new Intent(
					ActivityNames.mainMenActivity);

			SplashScreenActivity.this.startActivity(MAIN_MENU_ACTIVITY_INTENT
					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
							| Intent.FLAG_ACTIVITY_SINGLE_TOP));

			SplashScreenActivity.this.finish();
		}
	};

	/**
	 * Getter
	 * @return
	 * The {@link ImageView} of the logo of the application
	 */
	public ImageView getLogo() {
		return logo;
	}

	/**
	 * Setter
	 * @param imageView
	 * 		{@link ImageView} of the application logo
	 */
	public void setLogo(ImageView imageView) {
		logo = imageView;
	}
}