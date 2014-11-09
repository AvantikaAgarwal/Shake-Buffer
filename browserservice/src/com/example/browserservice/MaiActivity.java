package com.example.browserservice;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MaiActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_mai);
		Intent i= new Intent(this, BrowserIntentService.class);
		startService(i);
	}

	

}
