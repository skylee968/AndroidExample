package com.orangestudio.mobilereader.Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orangestudio.mobilereader.Application.MRApplicationContext;
import com.orangestudio.mobilereader.Database.SimpleStoreIF;
import com.orangestudio.mobilereader.Entity.BookEntity;
import com.orangestudio.mobilereader.Global.DBConfig;
import com.orangestudio.mobilereader.Listener.UserBookDetailIF;
import com.orangestudio.mobilereader.Utils.StoreUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserBookDetailModel extends BaseModel implements UserBookDetailIF {
	
	private static UserBookDetailIF _instance;
	private static final Lock createLock = new ReentrantLock();

	private static final int STORE_EXPIRE = -1; // never expired
	

	public static UserBookDetailIF getInstance() {
		if (_instance == null) {
			createLock.lock();
			_instance = new UserBookDetailModel();
			createLock.unlock();
		}
		return _instance;
	}

	private UserBookDetailModel() {

	}

	@Override
	SimpleStoreIF getStoreAdapter() {
		return StoreUtils.getStoreAdapter(DBConfig.Cache.USER_BOOK_DETAIL_TABLE,
				MRApplicationContext.getContext(), DBConfig.Cache.USER_BOOK_DETAIL_TABLE_SIZE);
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
	public List<BookEntity> getListBookDetail(String requestUrl, List<Long> ids) {
		return null;
	}

	@Override
	public BookEntity getBookDetail(String requestUrl, long id) {
		return null;
	}
	private List<BookEntity> getListBookDetailFromIds(String requestUrl,List<Long> ids){
		List<BookEntity> result = null;
		return result;
	}
	private BookEntity convertJsonStringToBookDetail(String json) {
		if (json == null || json.equals("")) {
			return null;
		}
		BookEntity result = new BookEntity();
		Gson gson = new Gson();
		Type typeClass = new TypeToken<BookEntity>() {
		}.getType();
		result = (BookEntity) gson.fromJson(json, typeClass);
		return result;
	}
	private void storeBookDetail(BookEntity book){
		Gson gson = new Gson();
		String data = gson.toJson(book);
		this.getStoreAdapter().put(String.valueOf(book.getId()), data);
	}
}
