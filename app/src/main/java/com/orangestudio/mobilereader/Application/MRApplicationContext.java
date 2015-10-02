package com.orangestudio.mobilereader.Application;

import android.content.Context;

public class MRApplicationContext {
	private String applicationName = "MRApplicationContext";
	public Context context = null;
	private static MRApplicationContext _instance;

	public static MRApplicationContext getInstance() {
		if (_instance == null) {
			_instance = new MRApplicationContext();
		}
		return _instance;
	}

	public static void setApplicationName(String appName) {
		getInstance().applicationName = appName;
	}

	public static String getApplicationName() {
		return getInstance().applicationName;
	}

	public static void setContext(Context context) {
		getInstance().context = context;
	}
	public static Context getContext(){
		return getInstance().context;
	}
}
