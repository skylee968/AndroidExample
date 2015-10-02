package com.orangestudio.mobilereader.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.orangestudio.mobilereader.Activity.BookInfoActivity;
import com.orangestudio.mobilereader.Adapter.GridViewBookAdapter;
import com.orangestudio.mobilereader.Entity.BookEntity;
import com.orangestudio.mobilereader.Entity.RequestEntity;
import com.orangestudio.mobilereader.EntityResult.BookResultEntity;
import com.orangestudio.mobilereader.Global.AppConfig;
import com.orangestudio.mobilereader.Model.BookModel;
import com.orangestudio.mobilereader.R;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public abstract class BaseGridViewFragment extends BaseFragment implements AdapterView.OnItemClickListener{
	protected RequestEntity mRequest;
	protected GridViewBookAdapter mAdapter;
	protected GridView mGridView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(mView == null) {
			mView = inflater.inflate(R.layout.fragment_book_grid, container, false);
			initView();
			initListener();
		} else {
			if(mView.getParent() != null) {
				((ViewGroup) mView.getParent()).removeView(mView);
			}
		}
		return mView;
	}
	private void initView() {
		mGridView			= (GridView) mView.findViewById(R.id.gridview_book);
		mAdapter 			= new GridViewBookAdapter(getActivity());
		mGridView.setAdapter(mAdapter);
		initData();
	}
	private void initListener() {
		mGridView.setOnItemClickListener(this);
	}
	abstract void initData();

	protected void loadData() {
		BookModel.getInstance().getBooks(mRequest, new Callback<BookResultEntity>() {
			@Override
			public void success(BookResultEntity bookResultEntity, Response response) {
				if (bookResultEntity != null && bookResultEntity.data != null) {
					mAdapter.updateData(bookResultEntity.data);
				}
			}

			@Override
			public void failure(RetrofitError error) {

			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		BookEntity book  = (BookEntity) mAdapter.getItem(i);
		if(book != null) {
			Gson gson	  	= new Gson();
			String content 	= gson.toJson(book);
			Intent intent 	= new Intent(getActivity(), BookInfoActivity.class);
			intent.putExtra(AppConfig.BUNDLE_KEY.BOOK_DETAIL, content);
			startActivity(intent);
		}
	}
}
