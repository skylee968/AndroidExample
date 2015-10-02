package com.orangestudio.mobilereader.CustomCompoundView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.orangestudio.mobilereader.R;

import java.lang.ref.WeakReference;


public class CircleImage extends ImageView {
    private static final String LOG_TAG = CircleImage.class.getSimpleName();
    private Paint mPaint;
    private WeakReference<Bitmap> mWeakRef;

    int indicatorColor = Color.WHITE;
    float strokeWidth = 0;

    public CircleImage(Context paramContext) {
        super(paramContext);
    }

    public CircleImage(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        TypedArray a = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CircleImage);
        // get custom attrs
        indicatorColor = a.getColor(R.styleable.CircleImage_ci_borderColor, indicatorColor);
        strokeWidth = a.getDimension(R.styleable.CircleImage_ci_borderWidth, strokeWidth);
        a.recycle();
    }

    public CircleImage(Context paramContext, AttributeSet paramAttributeSet,
                       int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);

    }

    @Override
    public void invalidate() {
        this.mWeakRef = null;
        super.invalidate();
    }

    @Override
    public void invalidateDrawable(Drawable dr) {
        this.mWeakRef = null;
        super.invalidateDrawable(dr);
    }

    public static Bitmap getRoundedCornerBitmapSquare(Bitmap bitmap, int pixels, int endWidth, int endHeight) {

        Bitmap output = Bitmap.createBitmap(endWidth, endHeight, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, endWidth, endHeight);
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas paramCanvas) {
        int i = paramCanvas.saveLayer(0.0F, 0.0F, getWidth(), getHeight(), null, 31);
        try {
            if (this.mPaint == null)
                this.mPaint = new Paint();

            Bitmap localBitmap = null;
            if (this.mWeakRef != null)
                localBitmap = (Bitmap) this.mWeakRef.get();

            if ((localBitmap == null) || (localBitmap.isRecycled())) {
                Drawable localDrawable = getDrawable();
                if (localDrawable != null) {
                    Bitmap drawableBitmap = Bitmap.createBitmap(getWidth(),
                            getHeight(), Config.ARGB_8888);
                    Canvas localCanvas = new Canvas(drawableBitmap);
                    localDrawable.setBounds(0, 0, getWidth(), getHeight());
                    localDrawable.draw(localCanvas);

                    int endWidth = drawableBitmap.getWidth();
                    int endHeight = drawableBitmap.getWidth();

                    if (drawableBitmap.getWidth() > drawableBitmap.getHeight()) {
                        endWidth = drawableBitmap.getHeight();
                        endHeight = drawableBitmap.getHeight();
                    }

                    float shadowWidth = endWidth / 30;
                    float shadowX = shadowWidth / 2;
                    float shadowY = shadowWidth / 2;
                    if (strokeWidth == 0)
                        strokeWidth = shadowWidth / 2;

                    int padding = (int) ((shadowWidth * 2));


                    drawableBitmap = getRoundedCornerBitmapSquare(drawableBitmap, endWidth / 2, endWidth, endHeight);
                    // create empty bitmap for drawing
                    localBitmap = Bitmap.createBitmap(
                            Math.round((endWidth) + padding),
                            Math.round((endHeight) + padding), Config.ARGB_8888);

                    // get canvas for empty bitmap
                    Canvas canvas = new Canvas(localBitmap);
                    int width = drawableBitmap.getWidth();
                    int height = drawableBitmap.getHeight();

                    // fill the canvas with transparency
                    canvas.drawARGB(0, 0, 0, 0);

                    Paint paint = new Paint();
                    paint.setAntiAlias(true);
                    paint.setXfermode(null);
                    paint.setColor(indicatorColor);
                    paint.setStrokeWidth(strokeWidth);
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setAlpha(0x30);
                    //paint.setShadowLayer(shadowWidth, shadowX, shadowY, Color.BLACK);

                    RectF rectStroke = new RectF(padding / 2, padding / 2, width - padding, height - padding);
                    canvas.drawRoundRect(rectStroke, width, height, paint);

                    paint.setAlpha(0xFF);
                    //paint.setShadowLayer(0.0f, 0.0f, 0.0f, Color.BLACK);

                    //Rect imageRectDect = new Rect(padding/2, padding/2, width - padding, height - padding);
                    Rect imageRectSrc = new Rect(0, 0, width, height);

                    canvas.drawBitmap(drawableBitmap, imageRectSrc, rectStroke, paint);
                    //canvas.drawBitmap(drawableBitmap, padding/2, padding/2, paint);
                    canvas.drawRoundRect(rectStroke, width, height, paint);

                    this.mWeakRef = new WeakReference<Bitmap>(localBitmap);
                }
            }
            if ((localBitmap != null) && (!localBitmap.isRecycled())) {
                this.mPaint.setXfermode(null);
                paramCanvas.drawBitmap(localBitmap, 0.0F, 0.0F, this.mPaint);
            }
            return;
        } catch (Throwable localThrowable) {
            //System.gc();
            Log.e(LOG_TAG, String.format("Unable to draw. View ID = %s", Integer.toHexString(getId())));
        } finally {
            if (paramCanvas != null && !isInEditMode()) paramCanvas.restoreToCount(i);
        }
    }

}