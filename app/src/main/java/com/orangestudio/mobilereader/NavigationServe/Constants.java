package com.orangestudio.mobilereader.NavigationServe;

import android.content.Context;
import android.support.v4.content.ContextCompat;


import com.orangestudio.mobilereader.Activity.BookInfoActivity;
import com.orangestudio.mobilereader.Fragment.ContentFragment;
import com.orangestudio.mobilereader.R;

import java.util.ArrayList;


public class Constants {

    private static Constants instance;
    private  ArrayList<Item> items = null;

    public static  Constants getInstance(Context ctx){
        if(instance == null){
            instance = new Constants(ctx);
        }

        return instance;
    }

    private Constants(Context ctx){
        items = new ArrayList<Item>();
        items.add(new EntryItem(ctx.getString(R.string.mr__mn_home), ContextCompat.getDrawable(ctx, R.drawable.mr__ic_home), ContentFragment.class, 1));
        items.add(new EntryItem(ctx.getString(R.string.mr__mn_my_book), ContextCompat.getDrawable(ctx, R.drawable.mr__ic_my_book), ContentFragment.class, 1));
        items.add(new EntryItem(ctx.getString(R.string.mr__mn_book_cate), ContextCompat.getDrawable(ctx, R.drawable.mr__ic_book_cate), ContentFragment.class, 1));

        items.add(new SectionItem());
        items.add(new EntryItem(ctx.getString(R.string.mr__mn_settings), null, BookInfoActivity.class, 2));
        items.add(new EntryItem(ctx.getString(R.string.mr__mn_rating_app), null, BookInfoActivity.class, 2));
        items.add(new EntryItem(ctx.getString(R.string.mr__mn_guide), null, BookInfoActivity.class, 2));
        items.add(new EntryItem(ctx.getString(R.string.mr__mn_about), null, BookInfoActivity.class, 2));

    }



    public  ArrayList<Item> getMenuData() {
        return items;
    }

    public Item getItemNameInPosition(int iPosition) {
        return items != null ? items.get(iPosition) : null;
    }

}
