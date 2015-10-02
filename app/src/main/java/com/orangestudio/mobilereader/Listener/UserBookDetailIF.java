package com.orangestudio.mobilereader.Listener;

import com.orangestudio.mobilereader.Entity.BookEntity;

import java.util.List;


public interface UserBookDetailIF {
	List<BookEntity> getListBookDetail(String requestUrl, List<Long> ids);
	BookEntity getBookDetail(String requestUrl, long id);
}
