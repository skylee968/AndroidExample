package com.orangestudio.mobilereader.Utils;

import android.content.Context;
import com.orangestudio.mobilereader.Database.SQLiteStore;
import com.orangestudio.mobilereader.Database.SimpleStoreIF;
import com.orangestudio.mobilereader.Global.DBConfig;

import java.io.File;


public final class StoreUtils {

	private StoreUtils(){
		
	}
	public static SimpleStoreIF getStoreAdapter(String name, Context context, int items){
		return SQLiteStore.getInstance(name, context, DBConfig.DBVERSION, items);
	}
}
