package com.orangestudio.mobilereader.Listener;


import com.orangestudio.mobilereader.Entity.RequestEntity;
import com.orangestudio.mobilereader.EntityResult.BookResultEntity;
import com.orangestudio.mobilereader.EntityResult.CategoryResultEntity;
import com.orangestudio.mobilereader.EntityResult.ServerConfigResultEntity;

import retrofit.Callback;

public interface CommonIF {

	void getCategory(Callback<CategoryResultEntity> callback);

	void getServerConfig(RequestEntity request, Callback<ServerConfigResultEntity> callback);

	void searchBookName(String bookName, Callback<BookResultEntity> callback);
}
