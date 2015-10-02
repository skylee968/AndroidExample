package com.orangestudio.mobilereader.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;

import com.orangestudio.mobilereader.Activity.BaseActivity;
import com.orangestudio.mobilereader.Entity.BookEntity;
import com.orangestudio.mobilereader.Entity.CategoryEntity;

/**
 * Created by thienlm on 7/5/2015.
 */
public class BaseFragment extends Fragment implements View.OnClickListener {
    protected View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    protected void setCurrentBook(BookEntity book) {
        ((BaseActivity)getActivity()).setCurrnentBook(book);
    }
    protected BookEntity getCurrentBook() {
        return ((BaseActivity)getActivity()).getCurrnentBook();
    }
    protected CategoryEntity getCurrentCategory() {
        return ((BaseActivity)getActivity()).getCategory();
    }

    @Override
    public void onClick(View v) {

    }
}
