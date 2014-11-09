package com.example.browserservice;



import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.Menu;
import android.widget.Toast;


	public class BrowserIntentService extends Service {

		private SensorManager mSensorManager;

		private ShakeEventListener mSensorListener;
		@Override
		public IBinder onBind(Intent intent) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public void onCreate() {
			// TODO Auto-generated method stub
			super.onCreate();
			
			//Toast.makeText(this, "Hello", 4000).show();	
			 mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		        mSensorListener = new ShakeEventListener();   

		        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

		          public void onShake() {
		        	Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.pravar.webbrowserpravar");
		  			startActivity(LaunchIntent);
		          }
		        });
		        
		        mSensorManager.registerListener(mSensorListener,
		   			 mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
		   				        SensorManager.SENSOR_DELAY_UI);
		}
		
		@Override
		public int onStartCommand(Intent intent, int flags, int startId) {
			// TODO Auto-generated method stub
			
			//Toast.makeText(this, "Hello", 4000).show();	
			  
			 mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		        mSensorListener = new ShakeEventListener();   

		        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

		          public void onShake() {
		        	Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.pravar.webbrowserpravar");
		  			startActivity(LaunchIntent);
		          }
		        });
		        mSensorManager.registerListener(mSensorListener,
		   			 mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
		   				        SensorManager.SENSOR_DELAY_UI);
			return super.onStartCommand(intent, flags, startId);
		}
		


	}