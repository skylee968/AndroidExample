package com.orangestudio.mobilereader.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


public abstract class OSBaseAdapter<T> extends BaseAdapter {
	protected Activity activity;
	protected LayoutInflater inflater = null;
	protected List<T> mListData;
	public OSBaseAdapter(Activity _activity) {
		activity = _activity;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	public void updateData(List<T> _listData){
		mListData.clear();
		mListData.addAll(_listData);
		notifyDataSetChanged();
	}
	
	public void insetData(List<T> _listData){
		mListData.addAll(_listData);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		if(mListData==null){
			return 0;
		}
		return mListData.size();
	}

	@Override
	public Object getItem(int position) {
		if(mListData != null && mListData.size()>position){
			return mListData.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		return null;
	}
}
