package com.orangestudio.mobilereader.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.orangestudio.mobilereader.R;

public class GuideFragment extends BaseFragment {
	private WebView mWebView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(mView == null) {
			mView = inflater.inflate(R.layout.fragment_guide, container, false);
			initView();
			initListener();
		} else if(mView.getParent() != null) {
			((ViewGroup) mView.getParent()).removeView(mView);
		}
		return mView;
	}
	private void initView() {
		mWebView  = (WebView) mView.findViewById(R.id.guide_web_view);
		mWebView.loadUrl("http://google.com");
	}
	private void initListener() {
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
	}
}
