package com.orangestudio.mobilereader.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.orangestudio.mobilereader.R;


public class AboutFragment extends BaseFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(mView == null) {
			mView = inflater.inflate(R.layout.fragment_about, container, false);
			initView();
			initListener();
		} else if(mView.getParent() != null) {
			((ViewGroup) mView.getParent()).removeView(mView);
		}
		return mView;
	}
	private void initView() {
	}
	private void initListener() {
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
	}
}
