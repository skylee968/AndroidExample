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
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orangestudio.mobilereader.Adapter.NavAdapter;
import com.orangestudio.mobilereader.CustomCompoundView.UserInfoControl;
import com.orangestudio.mobilereader.Entity.UserEntity;
import com.orangestudio.mobilereader.Fragment.AboutFragment;
import com.orangestudio.mobilereader.Fragment.CategoryFragment;
import com.orangestudio.mobilereader.Fragment.GuideFragment;
import com.orangestudio.mobilereader.Fragment.HomeFragment;
import com.orangestudio.mobilereader.Fragment.RatingFragment;
import com.orangestudio.mobilereader.Fragment.SuggestBookFragment;
import com.orangestudio.mobilereader.Model.UserModel;
import com.orangestudio.mobilereader.NavigationServe.Constants;
import com.orangestudio.mobilereader.NavigationServe.EntryItem;
import com.orangestudio.mobilereader.NavigationServe.Item;
import com.orangestudio.mobilereader.R;
import com.orangestudio.mobilereader.Social.Auth;
import com.orangestudio.mobilereader.Social.FacebookAuth;
import com.orangestudio.mobilereader.Social.GoogleSocialAuth;
import com.orangestudio.mobilereader.Social.SocialProfile;
import com.orangestudio.mobilereader.Utils.RepeatSafeToast;
import com.orangestudio.mobilereader.Utils.UserUtils;

public class HomeActivity extends BaseActivity implements Auth.OnAuthListener {

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    private Toolbar mToolbar;
    private ListView mDrawerList = null;
    private NavAdapter mDrawerAdapter = null;
    private DrawerLayout mDrawer = null;
    private ActionBarDrawerToggle mDrawerToggle;
    private View mUserInfoView = null;
    private UserInfoControl mUserInfoControl;

    /* Social login*/
    public static final String USER_AUTHENTICATED   = "user_authenticated"; //value is a Boolean
    public static final String USER_SOCIAL          = "user_social"; //value is a String and means user is logged with Social.FACEBOOK or Social.GOOGLE
    public static final String PROFILE_NAME         = "profile_name";  //value is a String
    public static final String PROFILE_EMAIL        = "profile_email";
    public static final String PROFILE_IMAGE        = "profile_image";  //value is a String
    public static final String PROFILE_COVER        = "profile_cover"; //value is a String

    private GoogleSocialAuth mGoogleAuth;
    private FacebookAuth mFacebookAuth;
    private String mSocialNetwork;
    /* End Social login*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__main);
        initViews();
        initListener();
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.os__base_tb_toolbar);
        setSupportActionBar(mToolbar);

        mDrawer             = (DrawerLayout) findViewById(R.id.os__base_dl_drawerlayout);
        mDrawerList         = (ListView) findViewById(R.id.os__base_lv_left_drawer);
        mUserInfoView       = getLayoutInflater().inflate(R.layout.mr__menu__item_user_info, null);
        mUserInfoControl    = (UserInfoControl) mUserInfoView.findViewById(R.id.mr__mn_item_uiv_user_profile);
        mDrawerList.addHeaderView(mUserInfoView);

        mDrawerAdapter      = new NavAdapter(this, Constants.getInstance(this).getMenuData());
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

        /* init social*/
        mGoogleAuth = new GoogleSocialAuth(this, this);
        mFacebookAuth = new FacebookAuth(this, this);

    }
    private void initListener() {
        mUserInfoControl.setOnUserInfoListener(new UserInfoControl.OnUserInfoListener() {
            @Override
            public void onLoginFbClick() {
                mSocialNetwork  = SocialProfile.FACEBOOK;
                mFacebookAuth.login();
            }

            @Override
            public void onLoginGoogleClick() {
                mSocialNetwork  = SocialProfile.GOOGLE;
                mGoogleAuth.login();
            }
        });
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
            Fragment fragment = null;

            switch (pos) {
                case 1:
                    fragment = new SuggestBookFragment();
                    break;
                case 2:
                    fragment = new HomeFragment();
                    break;
                case 3:
                    fragment = new CategoryFragment();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    fragment = new RatingFragment();
                    break;
                case 7:
                    fragment = new GuideFragment();
                    break;
                case 8:
                    fragment = new AboutFragment();
                    break;
                default:
                    fragment = new HomeFragment();
                    break;
            }

            transaction.replace(R.id.os__base_frame, fragment);
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
    protected void onResume() {
        super.onResume();
        checkUserInfo();
    }
    private void checkUserInfo() {
        mUserInfo = UserUtils.getUserEntity();
        if(mUserInfo != null) {
            RepeatSafeToast.show(this, R.string.common_txt_login);
        }
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
    /* Social login*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //disconnect google client api
        mGoogleAuth.logout();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(mSocialNetwork != null) {
            if(mSocialNetwork.equals(SocialProfile.GOOGLE) && requestCode == GoogleSocialAuth.GOOGLE_SIGN_IN){
                if(resultCode == RESULT_OK) {
                    //call connect again because google just authorized app
                    mGoogleAuth.login();
                } else {
                    onLoginCancel();
                }
            }
            if(mSocialNetwork.equals(SocialProfile.FACEBOOK)) {
                mFacebookAuth.getFacebookCallbackManager().onActivityResult(requestCode, resultCode, data);
            }
        }
    }
    @Override
    public void onLoginSuccess(SocialProfile profile) {
        Gson gson = new Gson();
        Log.e("USER PROFILE", gson.toJson(profile));
        saveAuthenticatedUser(profile);
        checkUserInfo();
//        Intent intent = new Intent(this, HomeActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
    }

    @Override
    public void onLoginError(String message) {
        Log.e("teste", message);
    }

    @Override
    public void onLoginCancel() {}

    @Override
    public void onRevoke() {}

    private void saveAuthenticatedUser(SocialProfile profile){
        UserEntity user = new UserEntity();
        user.setName(profile.getName());
        user.setEmail(profile.getEmail());
        user.setAvatar(profile.getImage());

        com.orangestudio.mobilereader.Global.Constants.mUserInfo = user;
        UserModel.getInstance().saveUserEntity(user);
    }
    /* End social login*/
}
