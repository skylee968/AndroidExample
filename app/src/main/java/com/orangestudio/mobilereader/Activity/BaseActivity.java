package com.orangestudio.mobilereader.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.orangestudio.mobilereader.Entity.BookEntity;
import com.orangestudio.mobilereader.Entity.CategoryEntity;
import com.orangestudio.mobilereader.Global.AppConfig;
import com.orangestudio.mobilereader.Utils.BookUtils;

/**
 * Created by thienlm on 10/2/2015.
 */
public class BaseActivity extends AppCompatActivity {
    private BookEntity mCurrnentBook;
    private CategoryEntity mCategory;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
    public BookEntity getCurrnentBook() {
        return mCurrnentBook;
    }

    public void setCurrnentBook(BookEntity mCurrnentBook) {
        this.mCurrnentBook = mCurrnentBook;
    }

    protected void getCategoryFromIntentData() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String content = bundle.getString(AppConfig.BUNDLE_KEY.CATEGORY);
            if(content != null) {
                setCategory(BookUtils.deserializeCategoryFromJson(content));
            }
        }
    }

    public CategoryEntity getCategory() {
        return mCategory;
    }

    public void setCategory(CategoryEntity mCategory) {
        this.mCategory = mCategory;
    }
}
