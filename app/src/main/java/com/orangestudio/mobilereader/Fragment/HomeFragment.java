package com.orangestudio.mobilereader.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangestudio.mobilereader.R;

public class HomeFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView == null) {
            mView = inflater.inflate(R.layout.fragment_home, container, false);
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
}
