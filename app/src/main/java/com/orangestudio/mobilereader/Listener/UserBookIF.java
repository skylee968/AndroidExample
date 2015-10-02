package com.orangestudio.mobilereader.Listener;

import com.orangestudio.mobilereader.Entity.BookLikedEntity;
import com.orangestudio.mobilereader.Entity.RequestEntity;
import com.orangestudio.mobilereader.Entity.UserEntity;
import com.orangestudio.mobilereader.EntityResult.BookLikedResultEntity;
import com.orangestudio.mobilereader.EntityResult.BookResultEntity;
import com.orangestudio.mobilereader.EntityResult.ListBookLikedResultEntity;
import com.orangestudio.mobilereader.EntityResult.LoginResultEntity;
import com.orangestudio.mobilereader.EntityResult.RegisterResultEntity;
import com.orangestudio.mobilereader.EntityResult.UserResultEntity;

import java.util.List;

import retrofit.Callback;

public interface UserBookIF {
	void saveUserEntity(UserEntity user);
	void getUserInfo(RequestEntity request, Callback<UserResultEntity> callback);
	UserEntity getUserAsync();

	void loginUser(RequestEntity request, Callback<LoginResultEntity> callback);
	void registerUser(RequestEntity request, Callback<RegisterResultEntity> callback);
	void getCoinUser(RequestEntity request, Callback<UserResultEntity> callback);

	List<BookLikedEntity> getBookLikedFromStore();
	void getBookLiked(RequestEntity request, Callback<ListBookLikedResultEntity> callback);
	void setBookLiked(RequestEntity request, Callback<BookLikedResultEntity> callback);
	void removeBookLiked(RequestEntity request, Callback<BookLikedResultEntity> callback);
	void logout();

	void getBook(RequestEntity request, Callback<BookResultEntity> callback);
	void getBookById(RequestEntity request, Callback<BookResultEntity> callback);
	void addBook(RequestEntity request, Callback<BookResultEntity> callback);


}
