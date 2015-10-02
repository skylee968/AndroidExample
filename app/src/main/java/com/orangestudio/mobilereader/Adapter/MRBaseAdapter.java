package com.orangestudio.mobilereader.Adapter;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.orangestudio.mobilereader.Entity.BookEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thienlm on 8/1/2015.
 */
public class MRBaseAdapter extends OSBaseAdapter<BookEntity> {
    protected class ViewHolder{
        public TextView title;
        public TextView author;
        public ImageView imageCover;
    }
    public MRBaseAdapter(Activity _activity) {
        super(_activity);
        mListData = new ArrayList<BookEntity>();
    }
    public void updateData(List<BookEntity> listData) {
        if(mListData == null) {
            mListData = new ArrayList<BookEntity>();
        }
        mListData.clear();
        mListData.addAll(listData);
        notifyDataSetChanged();
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
}
