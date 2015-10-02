package com.orangestudio.mobilereader.CustomCompoundView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orangestudio.mobilereader.Entity.UserEntity;
import com.orangestudio.mobilereader.R;


/**
 * Created by thienlm on 8/18/2015.
 */
public class UserInfoControl extends RelativeLayout {

    private Context mContext;
    private ViewGroup mRootView;
    private ImageView mCover = null;
    private View mUserInfoLayout = null;
    private View mLoginLayout = null;

    //----------- User Info
    private CircleImage ciAvatar;
    private CircleImage ciNotify;
    private Button btnAction;
    private TextView tvUserName;
    private TextView tvCoin;

    //----------- Login Layout
    private ImageButton ibFaceLogin;
    private ImageButton ibGoolgeLogin;

    private UserEntity mUserInfo = null;
    private OnUserInfoListener mOnUserInfoListener;

    public interface OnUserInfoListener {
        void onLoginClick();
    }
    public UserInfoControl(Context context) {
        super(context);
        initView(context);
    }

    public UserInfoControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public UserInfoControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @SuppressLint("NewApi")
    public UserInfoControl(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }
    private void initView(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView = (ViewGroup) inflater.inflate(R.layout.mr__cpv_user_info, this, true);

        mCover = (ImageView)mRootView.findViewById(R.id.mr__cpv_uic_iv_cover);
        mUserInfoLayout = findViewById(R.id.mr__cpv_uic_ll_user_info_layout);
        mLoginLayout = findViewById(R.id.mr__cpv_uic_ll_login_layout);

        ciAvatar = (CircleImage) mRootView.findViewById(R.id.mr__cpv_uic_ci_user_avatar);
        ciNotify = (CircleImage) mRootView.findViewById(R.id.mr__cpv_uic_ci_user_notify);
        btnAction = (Button) mRootView.findViewById(R.id.mr__cpv_uic_btn_logout);
        tvUserName = (TextView) mRootView.findViewById(R.id.mr__cpv_uic_tv_user_name);
        tvCoin = (TextView) mRootView.findViewById(R.id.mr__cpv_uic_tv_user_coin);

        ibFaceLogin = (ImageButton) mRootView.findViewById(R.id.mr__cpv_uic_fb_login);
        ibGoolgeLogin = (ImageButton) mRootView.findViewById(R.id.mr__cpv_uic_google_login);

    }



}
