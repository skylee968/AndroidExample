package com.orangestudio.mobilereader.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.orangestudio.mobilereader.R;

public class RatingFragment extends BaseFragment {
	private Button mBtnRating;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(mView == null) {
			mView = inflater.inflate(R.layout.fragment_rating, container, false);
			initView();
			initListener();
		} else if(mView.getParent() != null) {
			((ViewGroup) mView.getParent()).removeView(mView);
		}
		return mView;
	}
	private void initView() {
		mBtnRating = (Button) mView.findViewById(R.id.btn_rating);
	}
	private void initListener() {
		mBtnRating.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		if(view.getId() == R.id.btn_rating) {
		}
	}
}
