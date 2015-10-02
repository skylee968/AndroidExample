package com.orangestudio.mobilereader.Utils;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class RepeatSafeToast {

	private static final int DURATION = 4000;

	private static final Map<Object, Long> lastShown = new HashMap<Object, Long>();

	private static boolean isRecent(Object obj) {
		Long last = lastShown.get(obj);
		if (last == null) {
			return false;
		}
		long now = System.currentTimeMillis();
		if (last + DURATION < now) {
			return false;
		}
		return true;
	}

	public static synchronized void show(Context context, int resId) {
		if (isRecent(resId)) {
			return;
		}
		Toast msg = Toast.makeText(context, resId, Toast.LENGTH_LONG);
		msg.setGravity(Gravity.CENTER, Gravity.CENTER_HORIZONTAL,
				Gravity.CENTER_VERTICAL);
		msg.show();
		lastShown.put(resId, System.currentTimeMillis());
	}

	public static synchronized void show(Context context, String msg) {
		if (isRecent(msg)) {
			return;
		}
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		lastShown.put(msg, System.currentTimeMillis());
	}
}
