package com.orangestudio.mobilereader.NavigationServe;

public interface Item {

	public static  enum ItemType{
		USER_INFO, SECTION, ENTRY
	}
	
	public ItemType itemType();

}
