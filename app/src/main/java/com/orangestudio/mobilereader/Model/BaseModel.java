package com.orangestudio.mobilereader.Model;


import com.orangestudio.mobilereader.Database.SimpleStoreIF;

public abstract class BaseModel{
	abstract SimpleStoreIF getStoreAdapter();
	abstract void setStore(String key, String value);
	abstract void setStore(String key, String value, int expiredTime);
}
