package com.uit.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.uit.R;





public class WelcomeScreen extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_splash_screen);
		
		 MainCountDown countdown = new MainCountDown(3000, 3000);
	     countdown.start();
		
	}
	
	public class MainCountDown extends CountDownTimer
    {
		public MainCountDown(long millisInFuture, long countDownInterval)
		{
			super(millisInFuture, countDownInterval);
		}
		
		@Override
		public void onFinish()
		{
			startMain();
		}
		@Override
		public void onTick(long millisUntilFinished) {}
    }
    
    private void startMain(){
    	Intent intent = new Intent(getApplicationContext(), BaseActivity.class);
		startActivity(intent);
		finish();
    }
	
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        System.gc();
    }
}