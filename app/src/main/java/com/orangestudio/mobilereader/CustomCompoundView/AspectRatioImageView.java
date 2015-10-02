// 20120503: created Nguyen Hoang Tuan
// tuannh2@vng.com.vn

package com.orangestudio.mobilereader.CustomCompoundView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class AspectRatioImageView extends ImageView {

    private int mWidth = -1;
    private int mHeight = -1;
    
    public static final int ASPECT_OPT = 0;
    public static final int FULL_OPT = 1;
    public static final int SQUARE_OPT = 2;
    
    private int scaleOption;
    private boolean isDimImageOnTouch = false;
    
    public AspectRatioImageView(Context context) 
    {
        super(context);
        this.scaleOption = 1;
    }

    public AspectRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AspectRatioImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    public void setScaleOption(int scaleOption)
    {
        this.scaleOption = scaleOption;
    }
    
    public void setDimImageOnTouch(boolean isDimImageOnTouch)
    {
        this.isDimImageOnTouch = isDimImageOnTouch;
    }
    
    public void refreshState()
    {
        setColorFilter(Color.TRANSPARENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
    {
        if(getDrawable() != null)
        {
            mWidth = MeasureSpec.getSize(widthMeasureSpec);
            try 
            {
                int originalWidth = getDrawable().getIntrinsicWidth();
                int originalHeight = getDrawable().getIntrinsicHeight();
                
                if(scaleOption == FULL_OPT)
                {
                    if(originalWidth > 0)
                        mHeight = mWidth * originalHeight / originalWidth;
                    else
                        mHeight = mWidth;
                    
                    this.setScaleType(ScaleType.FIT_CENTER);
                }
                else if(scaleOption == ASPECT_OPT)
                {
                    if(originalWidth > 0)
                        mHeight = mWidth * originalHeight / originalWidth;
                    else
                        mHeight = mWidth;
                    
                    this.setScaleType(ScaleType.FIT_CENTER);
                    
                    if(mHeight > mWidth)
                    {
                        mHeight = mWidth;
                        this.setScaleType(ScaleType.CENTER_CROP);
                    }
                }
                else if(scaleOption == SQUARE_OPT)
                {
                    mHeight = mWidth;
                    this.setScaleType(ScaleType.CENTER_CROP);
                }
                
                setMeasuredDimension(mWidth, mHeight);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
                setMeasuredDimension(mWidth, mHeight);
            }
        }
        else
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) 
    {
        try 
        {
            if(isDimImageOnTouch)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN) 
                    getDrawable().setColorFilter(0x33000000, PorterDuff.Mode.SRC_OVER );
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    getDrawable().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_OVER );
                else if (event.getAction() == MotionEvent.ACTION_OUTSIDE)
                    getDrawable().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_OVER );
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return super.onTouchEvent(event);
    }
}