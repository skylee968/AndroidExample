package com.orangestudio.mobilereader.Listener;


import com.orangestudio.mobilereader.Entity.RequestEntity;
import com.orangestudio.mobilereader.EntityResult.BookResultEntity;

import retrofit.Callback;

public interface BookIF{
	void getBooks(RequestEntity request, Callback<BookResultEntity> callback);
	void getBookEntityById(RequestEntity request, Callback<BookResultEntity> callback);
}
