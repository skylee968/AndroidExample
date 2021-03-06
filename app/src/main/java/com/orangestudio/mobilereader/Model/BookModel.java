package com.orangestudio.mobilereader.Model;


import com.orangestudio.mobilereader.Application.MRApplicationContext;
import com.orangestudio.mobilereader.Database.SimpleStoreIF;
import com.orangestudio.mobilereader.Entity.RequestEntity;
import com.orangestudio.mobilereader.EntityResult.BookResultEntity;
import com.orangestudio.mobilereader.Global.DBConfig;
import com.orangestudio.mobilereader.HttpRequest.RestClient;
import com.orangestudio.mobilereader.Listener.BookIF;
import com.orangestudio.mobilereader.Utils.StoreUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class BookModel extends BaseModel implements BookIF {
	
	private static BookIF _instance;
	private static final Lock createLock = new ReentrantLock();
	
	private static final int STORE_EXPIRE = 3*24*60*60; //3 day

	public static BookIF getInstance(){
		if(_instance==null){
			createLock.lock();
			_instance = new BookModel();
			createLock.unlock();
		}
		return _instance;
	}
	private BookModel(){
		
	}
	@Override
	SimpleStoreIF getStoreAdapter() {
		return StoreUtils.getStoreAdapter(DBConfig.Cache.BOOK_TABLE, MRApplicationContext.getContext(), DBConfig.Cache.BOOK_TABLE_SIZE);
	}

	@Override
	void setStore(String key, String value) {
		this.getStoreAdapter().put(key, value, STORE_EXPIRE);
	}

	@Override
	void setStore(String key, String value, int expiredTime) {
		this.getStoreAdapter().put(key, value, expiredTime);
	}
	@Override
	public void getBooks(RequestEntity request, final Callback<BookResultEntity> callback) {
		switch (request.requestType) {
			case BOOKS:
				getBooksAsync(request, callback);
				break;
			case SUGGEST_BOOKS:
				getSuggestBooksAsync(request, callback);
				break;
			case NEWS_BOOKS:
				getNewBooksAsync(request, callback);
				break;
			case SUGGEST_BOOK_CATEGORY:
				getSuggestBooksCategoryAsync(request, callback);
				break;
			case ALL_BOOK_CATEGORY:
				getAllBooksCategoryAsync(request, callback);
				break;
			case RELATIVE_BOOK:
				getRelativeBooksAsync(request, callback);
				break;
			case USER_FAVORITE_BOOK:
				getUserBookLiked(request, callback);
				break;
			case USER_BOOK:
				getNewBooksAsync(request, callback);
				break;
			default:
				if(callback != null) {
					callback.success(null, null);
				}
				break;
		}

	}
	private void getUserBookLiked(RequestEntity request, final Callback<BookResultEntity> callback) {
		RestClient.get().getUserBookLiked(request.userId, new Callback<BookResultEntity>() {
			@Override
			public void success(BookResultEntity bookResultEntity, Response response) {
				callback.success(bookResultEntity, response);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}
		});
	}
	private void getBooksAsync(RequestEntity request, final Callback<BookResultEntity> callback) {
		RestClient.get().getBooks(request.id, request.cateId, String.valueOf(request.pageIndex), request.pageNum, new Callback<BookResultEntity>() {
			@Override
			public void success(BookResultEntity bookResultEntity, Response response) {
				callback.success(bookResultEntity, response);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}
		});
	}
	private void getNewBooksAsync(RequestEntity request, final Callback<BookResultEntity> callback) {
		RestClient.get().getNewBooks(request.id, request.cateId, String.valueOf(request.pageIndex), request.pageNum, new Callback<BookResultEntity>() {
			@Override
			public void success(BookResultEntity bookResultEntity, Response response) {
				callback.success(bookResultEntity, response);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}
		});
	}
	private void getRelativeBooksAsync(RequestEntity request, final Callback<BookResultEntity> callback) {
		RestClient.get().getRelativeBooks(request.id, request.cateId, String.valueOf(request.pageIndex), request.pageNum, new Callback<BookResultEntity>() {
			@Override
			public void success(BookResultEntity bookResultEntity, Response response) {
				callback.success(bookResultEntity, response);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}
		});
	}
	private void getSuggestBooksAsync(RequestEntity request, final Callback<BookResultEntity> callback) {
		RestClient.get().getSuggestBooks(request.id, request.cateId, String.valueOf(request.pageIndex), request.pageNum, new Callback<BookResultEntity>() {
			@Override
			public void success(BookResultEntity bookResultEntity, Response response) {
				callback.success(bookResultEntity, response);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}
		});
	}
	private void getSuggestBooksCategoryAsync(RequestEntity request, final Callback<BookResultEntity> callback) {
		RestClient.get().getSuggestBookCategory(request.cateId, String.valueOf(request.pageIndex), request.pageNum, new Callback<BookResultEntity>() {
			@Override
			public void success(BookResultEntity bookResultEntity, Response response) {
				callback.success(bookResultEntity, response);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}
		});
	}
	private void getAllBooksCategoryAsync(RequestEntity request, final Callback<BookResultEntity> callback) {
		RestClient.get().getAllBookCategory(request.cateId, String.valueOf(request.pageIndex), request.pageNum, new Callback<BookResultEntity>() {
			@Override
			public void success(BookResultEntity bookResultEntity, Response response) {
				callback.success(bookResultEntity, response);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}
		});
	}
	@Override
	public void getBookEntityById(RequestEntity request, Callback<BookResultEntity> callback) {

	}
}
