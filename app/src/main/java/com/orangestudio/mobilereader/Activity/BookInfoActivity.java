package com.orangestudio.mobilereader.Activity;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.orangestudio.mobilereader.R;

public class BookInfoActivity extends BaseActivity {

    private CollapsingToolbarLayout mCollapsingToolbar = null;
    private ImageView ivBookCover = null;
    private Toolbar mToolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mr__activity_book_info);

        mToolbar = (Toolbar) findViewById(R.id.mr__abi_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.mr__abi_collapsing_tool_bar);
        mCollapsingToolbar.setTitle("My Toolbar Tittle");
        mCollapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
        mCollapsingToolbar.setExpandedTitleColor(Color.WHITE);
        mCollapsingToolbar.setContentScrimColor(getResources().getColor(R.color.os__primary_color));
        mCollapsingToolbar.setExpandedTitleTextAppearance(R.style.mr__ExpandedAppbarStyle);
        mCollapsingToolbar.setCollapsedTitleTextAppearance(R.style.mr__CollapsedAppbarStyle);

        initViews();
    }

    private void initViews() {

        ivBookCover = (ImageView) findViewById(R.id.header);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
