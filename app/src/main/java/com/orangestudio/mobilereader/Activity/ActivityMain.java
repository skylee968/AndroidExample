package com.orangestudio.mobilereader.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.orangestudio.mobilereader.Adapter.NavAdapter;
import com.orangestudio.mobilereader.Fragment.HomeFragment;
import com.orangestudio.mobilereader.NavigationServe.Constants;
import com.orangestudio.mobilereader.NavigationServe.EntryItem;
import com.orangestudio.mobilereader.NavigationServe.Item;
import com.orangestudio.mobilereader.R;
import com.orangestudio.mobilereader.Utils.RepeatSafeToast;

public class ActivityMain extends AppCompatActivity {

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    private Toolbar mToolbar;
    private ListView mDrawerList = null;
    private NavAdapter mDrawerAdapter = null;
    private DrawerLayout mDrawer = null;
    private ActionBarDrawerToggle mDrawerToggle;
    private View mUserInfoView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__main);
        initViews();
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.os__base_tb_toolbar);
        setSupportActionBar(mToolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.os__base_dl_drawerlayout);
        mDrawerList = (ListView) findViewById(R.id.os__base_lv_left_drawer);
        mUserInfoView = getLayoutInflater().inflate(R.layout.mr__menu__item_user_info, null);
        mDrawerList.addHeaderView(mUserInfoView);

        mDrawerAdapter = new NavAdapter(this, Constants.getInstance(this).getMenuData());
        mDrawerList.setAdapter(mDrawerAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        };
        mDrawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Item item = (Item) parent.getItemAtPosition(position);

            if (item == null) {
                return;
            }

            if (item.itemType() == Item.ItemType.ENTRY) {
                selectItem((EntryItem) item, position);
            }

        }

    }


    private void selectItem(EntryItem item, int pos) {
        mDrawer.closeDrawers();

        Toast.makeText(this.getApplicationContext(), "Click at " + pos, Toast.LENGTH_LONG).show();

        if (item.getClassType() == 1) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Fragment temp = null;

            switch (pos) {
                case 1:
                    temp = new HomeFragment();
                    break;
                case 2:
                    temp = new HomeFragment();
                    break;
                default:
                    temp = new HomeFragment();
                    break;
            }


            transaction.replace(R.id.os__base_frame, temp);
            transaction.commit();
        } else if (item.getClassType() == 2) {
            Class<?> navigationClass = item.getClassName();

            if (navigationClass != null) {
                startActivity(new Intent(this, navigationClass));
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.os__base_menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.os__base_mn_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            RepeatSafeToast.show(this, R.string.mr__mess_double_click_exit);
        }

        mBackPressed = System.currentTimeMillis();
    }
}
