package com.orangestudio.mobilereader.Model;

import com.google.gson.Gson;
import com.orangestudio.mobilereader.Application.MRApplicationContext;
import com.orangestudio.mobilereader.Database.SimpleStoreIF;
import com.orangestudio.mobilereader.Entity.BookLikedEntity;
import com.orangestudio.mobilereader.Entity.RequestEntity;
import com.orangestudio.mobilereader.Entity.UserEntity;
import com.orangestudio.mobilereader.EntityResult.BookLikedResultEntity;
import com.orangestudio.mobilereader.EntityResult.BookResultEntity;
import com.orangestudio.mobilereader.EntityResult.ListBookLikedResultEntity;
import com.orangestudio.mobilereader.EntityResult.LoginResultEntity;
import com.orangestudio.mobilereader.EntityResult.RegisterResultEntity;
import com.orangestudio.mobilereader.EntityResult.UserResultEntity;
import com.orangestudio.mobilereader.Global.Constants;
import com.orangestudio.mobilereader.Global.DBConfig;
import com.orangestudio.mobilereader.HttpRequest.RestClient;
import com.orangestudio.mobilereader.Listener.UserBookIF;
import com.orangestudio.mobilereader.Utils.CommonUtils;
import com.orangestudio.mobilereader.Utils.StoreUtils;
import com.orangestudio.mobilereader.Utils.UserUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UserModel extends BaseModel implements UserBookIF {

	private static UserBookIF _instance;
	private static final Lock createLock 			= new ReentrantLock();
	
	private static final int STORE_EXPIRE 			= 3*24*60*60; //3 day
	private static final int STORE_EXPIRE_5_DAYS 	= 5*24*60*60; //5 day

	
	public static UserBookIF getInstance(){
		if(_instance==null){
			createLock.lock();
			_instance = new UserModel();
			createLock.unlock();
		}
		return _instance;
	}
	private UserModel(){
		
	}
	@Override
	SimpleStoreIF getStoreAdapter() {
		return StoreUtils.getStoreAdapter(DBConfig.Cache.USER_TABLE, MRApplicationContext.getContext(), DBConfig.Cache.USER_TABLE_SIZE);
	}

	@Override
	void setStore(String key, String value) {
		this.getStoreAdapter().put(key, value, STORE_EXPIRE);
	}

	@Override
	void setStore(String key, String value, int expiredTime) {
		this.getStoreAdapter().put(key, value, expiredTime);
	}

	public List<Integer> getUserBookLikedIdsAsync() {
		List<Integer> likedIds = null;
		likedIds = getLikedIdsFromStore("userid");
		if(likedIds != null && likedIds.size() > 0){
			return likedIds;
		}
		//likedIds = getUserBookLikedIdsFromAPI();
		return likedIds;
	}
	private List<Integer> getLikedIdsFromStore(String key){
		List<Integer> result = null;
		String json = getStoreAdapter().get(key);
		if(json != null && !json.equals("")){
			result = CommonUtils.deserializeListIntegerFromJson(json);
		}
		return result;
	}
	

	public void updateUserLiked(List<Integer> list) {
		if(list != null && list.size() > 0){
			Gson gs = new Gson();
			String temp = gs.toJson(list);
			getStoreAdapter().put("", temp);
		}
		
	}

	@Override
	public void getBook(RequestEntity request, Callback<BookResultEntity> callback) {

	}

	@Override
	public void getBookById(RequestEntity request, Callback<BookResultEntity> callback) {

	}

	@Override
	public void addBook(RequestEntity request, Callback<BookResultEntity> callback) {

	}

	@Override
	public void getUserInfo(RequestEntity request, final Callback<UserResultEntity> callback) {
		RestClient.get().getUserInfo(String.valueOf(request.userId), new Callback<UserResultEntity>() {
			@Override
			public void success(UserResultEntity userResultEntity, Response response) {
				callback.success(userResultEntity, response);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}
		});
	}
	@Override
	public void saveUserEntity(UserEntity user) {
		if (user == null) {
			return;
		}
		Gson gs = new Gson();
		String data = gs.toJson(user);
		getStoreAdapter().put(DBConfig.CacheKey.USER_INFO_KEY, data);
	}
	@Override
	public UserEntity getUserAsync() {
		String json = getStoreAdapter().get(DBConfig.CacheKey.USER_INFO_KEY);
//		if(json != null) {
//			Log.e("USER INFO:", json);
//		}
		if(json != null && json.trim().length() > 1) {
			return CommonUtils.deserializeStringToUserEntity(json);
		}
		return null;
	}

	@Override
	public void loginUser(RequestEntity request, final Callback<LoginResultEntity> callback) {
		RestClient.get().loginUser(request.username, request.passwords, request.signKey, new Callback<LoginResultEntity>() {
			@Override
			public void success(LoginResultEntity loginResultEntity, Response response) {
				if (loginResultEntity != null && loginResultEntity.data != null) {
					saveUserInfoIntoStore(loginResultEntity.data);
					Constants.mUserInfo = loginResultEntity.data;

					// Get user liked
					RequestEntity request = new RequestEntity();
					request.userId = Constants.mUserInfo.getId();
					UserModel.getInstance().getBookLiked(request, null);
				}
				callback.success(loginResultEntity, response);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}
		});
	}

	@Override
	public void registerUser(RequestEntity request, final Callback<RegisterResultEntity> callback) {
		RestClient.get().registerUser(request.username, request.email, request.passwords, request.imeiNumber, request.registerId, new Callback<RegisterResultEntity>() {
			@Override
			public void success(RegisterResultEntity registerResultEntity, Response response) {
				callback.success(registerResultEntity, response);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}
		});
	}

	@Override
	public void getCoinUser(RequestEntity request, Callback<UserResultEntity> callback) {

	}

	@Override
	public List<BookLikedEntity> getBookLikedFromStore() {
		if(Constants.mUserInfo == null) {
			return null;
		}
		String temp = getStoreAdapter().get(DBConfig.CacheKey.USER_BOOK_LIKED + Constants.mUserInfo.getId());
		if(temp != null && temp.trim().length() > 0) {
			return UserUtils.deserializeBookLikedFromJson(temp);
		}
		return null;
	}
	@Override
	public void getBookLiked(final RequestEntity request, final Callback<ListBookLikedResultEntity> callback) {
		if (request.userId != null) {
			List<BookLikedEntity> result = getBookLikedFromStore();
			if(result != null) {
				ListBookLikedResultEntity temp 	= new ListBookLikedResultEntity();
				temp.data 						= result;
				callback.success(temp, null);
				return;
			}
		}
		RestClient.get().getBookLiked(request.userId, new Callback<ListBookLikedResultEntity>() {
			@Override
			public void success(ListBookLikedResultEntity result, Response response) {
				if (result != null && result.data != null && result.data.size() > 0 && request.userId != null) {
					Constants.mUserBookLiked = result.data;
					Gson gson = new Gson();
					String data = gson.toJson(result.data);
					setStore(DBConfig.CacheKey.USER_BOOK_LIKED + request.userId, data, STORE_EXPIRE_5_DAYS);
				}
				if (callback != null) {
					callback.success(result, response);
				}
			}

			@Override
			public void failure(RetrofitError error) {
				if (callback != null) {
					callback.failure(error);
				}
			}
		});
	}
	@Override
	public void setBookLiked(RequestEntity request, final Callback<BookLikedResultEntity> callback) {
		UserUtils.loadUserBookLiked();
		if(Constants.mUserBookLiked == null) {
			Constants.mUserBookLiked = new ArrayList<BookLikedEntity>();
		}

		BookLikedEntity liked = new BookLikedEntity();
		liked.bookId = request.id;
		liked.likeDate = String.valueOf(System.currentTimeMillis());
		Constants.mUserBookLiked.add(liked);

		RestClient.get().setBookLiked(request.userId, request.id, new Callback<BookLikedResultEntity>() {
			@Override
			public void success(BookLikedResultEntity bookLikedResultEntity, Response response) {
				callback.success(bookLikedResultEntity, response);
			}

			@Override
			public void failure(RetrofitError error) {
				callback.failure(error);
			}
		});
	}

	@Override
	public void removeBookLiked(RequestEntity request, Callback<BookLikedResultEntity> callback) {

	}

	@Override
	public void logout() {
		this.getStoreAdapter().remove(DBConfig.CacheKey.USER_INFO_KEY);
	}

	private void saveUserInfoIntoStore(UserEntity user) {
		if(user == null) {
			return;
		}
		Gson gson 		= new Gson();
		String result 	= gson.toJson(user);
		setStore(DBConfig.CacheKey.USER_INFO_KEY, result, -1);
	}
}
