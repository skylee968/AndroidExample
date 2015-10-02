package com.orangestudio.mobilereader.Application;

import android.app.Application;

public class MRApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		MRApplicationContext.setContext(getApplicationContext());
	}
}
