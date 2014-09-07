package com.example.androidcordova;

import org.apache.cordova.Config;
import org.apache.cordova.CordovaActivity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends CordovaActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 super.init();
	        // Set by <content src="index.html" /> in config.xml
	        super.loadUrl(Config.getStartUrl());
	}

	
}
