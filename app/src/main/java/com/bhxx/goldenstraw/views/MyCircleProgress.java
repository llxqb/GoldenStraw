package com.bhxx.goldenstraw.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.provider.CalendarContract;
import android.util.AttributeSet;
import android.view.View;

import com.bhxx.goldenstraw.R;

/**
 * @author Administrator
 */
public class MyCircleProgress extends View {

    /**
     * 水波移动的速度
     */
    private static final int WAVE_TRANS_SPEED = 4;

    /**
     * 圆环的颜色
     */
    private int roundColor;
    /**
     * 圆环的宽度
     */
    private float roundWidth;
    /**
     * 文字的大小
     */
    private float textSize;
    /**
     * 文字的颜色
     */
    private int textColor;
    /**
     * 圆弧的颜色
     */
    private int arcColor;

    /**
     * 中心点坐标
     */
    private int center;

    private Paint mBitmapPaint, paint;
    /**
     * 整个图的高度和宽度
     */
    private int mTotalWidth, mTotalHeight;
    /**
     * 画图波浪的中间间距
     */
    private int mCenterX;
    /**
     * 进度值
     */
    private int progress;

    /**
     * 水波的图片
     */
    private Bitmap mSrcBitmap1;
    private Bitmap mSrcBitmap2;
    /**
     * 要绘制的图的那一部分，截取图的对应部分来进行绘制
     */
    private Rect mSrcRect;
    /**
     * 要绘制的位置，该图要绘制的位置
     */
    private Rect mDestRect;

    private PorterDuffXfermode mPorterDuffXfermode;
    /**
     * 一个圆圈的图片
     */
    private Bitmap mMaskBitmap;
    private Rect mMaskSrcRect, mMaskDestRect;

    /**
     * 当前位置
     */
    private int mCurrentPosition;

    // 刷新线程，在这里，进行图片的滚动
    private RefreshProgressRunnable mRefreshProgressRunnable;

    public MyCircleProgress(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();
        initBitmap();
        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

        TypedArray myTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.myCircle);
        roundColor = myTypedArray.getColor(R.styleable.myCircle_roundColor,
                Color.GREEN);
        roundWidth = myTypedArray.getDimension(R.styleable.myCircle_roundWidths,
                5);
        textSize = myTypedArray.getDimension(R.styleable.myCircle_textSize, 20);
        textColor = myTypedArray.getColor(R.styleable.myCircle_textColor,
                Color.parseColor("#FF623E"));

        arcColor = myTypedArray.getColor(R.styleable.myCircle_arcColor,
                Color.GRAY);


        myTypedArray.recycle();
    }

    public MyCircleProgress(Context context) {
        this(context, null);

    }

    public MyCircleProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 从canvas层面去除锯齿
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
                | Paint.FILTER_BITMAP_FLAG));

		/*
         * 将绘制操作保存到新的图层
		 */
        int sc = canvas.saveLayer(0, 0, mTotalWidth, mTotalHeight, null,
                Canvas.ALL_SAVE_FLAG);

        mBitmapPaint.setAntiAlias(true);

        // 设定要绘制的波纹部分
        mSrcRect.set(mCurrentPosition, 0, mCurrentPosition + mCenterX,
                mTotalHeight);
        // 通过改变要画的位置的高度，进而改变水波的高度。绘图高度从10到mTotalHeight - 10。总的水波的高度（mTotalWidth - 20）除以100，则是每个progress的高度
        mDestRect.set(10, mTotalHeight - 10 - progress * (mTotalWidth - 20)
                / 100, mTotalWidth - 10, mTotalHeight - 10);

        // 绘制波纹部分
        canvas.drawBitmap(mSrcBitmap1, mSrcRect, mDestRect, mBitmapPaint);

        canvas.drawBitmap(mSrcBitmap2, mSrcRect, mDestRect, mBitmapPaint);

        // 设置图像的混合模式
        mBitmapPaint.setXfermode(mPorterDuffXfermode);
        // 绘制遮罩圆
        canvas.drawBitmap(mMaskBitmap, mMaskSrcRect, mMaskDestRect,
                mBitmapPaint);
        // 取消混合模式
        mBitmapPaint.setXfermode(null);
        canvas.restoreToCount(sc);

        // 画最外层的圆环
        paint = new Paint();
        // 设置空心
        paint.setStyle(Paint.Style.STROKE);
        // 设置圆环的宽度
        paint.setStrokeWidth(5);
        // 设置圆环的颜色
        paint.setColor(Color.parseColor("#FF623E"));//roundColor
        // 圆环的半径,圆环的半径是内圆的半径
        int radius = (int) (center - roundWidth / 2);
        canvas.drawCircle(center, center, radius, paint);

        // 画圆弧的进度
//        paint.setColor(arcColor);
        paint.setStyle(Paint.Style.STROKE);
        //这里+2的原因是，避免有视觉效果上有锯齿
//        paint.setStrokeWidth(roundWidth + 1);  //不画圆弧
        RectF rectF = new RectF(center - radius, center - radius, center
                + radius, center + radius);
        //-90是从0点方向开始
        canvas.drawArc(rectF, -90, progress * 360 / 100, false, paint);

        // 画进度百分比数字
        paint.setStrokeWidth(5);
        paint.setColor(textColor);
        paint.setTextSize(150);
        paint.setStyle(Paint.Style.FILL);
        String text = "6";
        float width = paint.measureText(text);
        //将数字写在中间位置
        canvas.drawText(text, center - width / 2, center, paint);

        paint.setStrokeWidth(5);
        paint.setColor(textColor);
        paint.setTextSize(50);
        String text1 = "天";
        canvas.drawText(text1, center - width / 2 + 100, center, paint);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mRefreshProgressRunnable = new RefreshProgressRunnable();
        post(mRefreshProgressRunnable);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(mRefreshProgressRunnable);
    }

    private class RefreshProgressRunnable implements Runnable {
        public void run() {
            synchronized (MyCircleProgress.this) {
                // 不断改变绘制的波浪的位置
                mCurrentPosition += WAVE_TRANS_SPEED;
                if (mCurrentPosition >= mSrcBitmap1.getWidth()) {
                    mCurrentPosition = 0;
                }
                if (mCurrentPosition >= mSrcBitmap2.getWidth()) {
                    mCurrentPosition = 0;
                }

                postInvalidate();
                // 16ms更新一次
                postDelayed(this, 16);
            }
        }
    }

    // 初始化bitmap
    private void initBitmap() {
        //使用drawable获取的方式，全局只会生成一份，并且系统会进行管理，而BitmapFactory.decode()出来的则decode多少次生成多少张，务必自己进行recycle
        mSrcBitmap1 = ((BitmapDrawable) getResources().getDrawable(
                R.mipmap.wave1)).getBitmap();
        mSrcBitmap2 = ((BitmapDrawable) getResources().getDrawable(
                R.mipmap.wave2)).getBitmap();
        mMaskBitmap = ((BitmapDrawable) getResources().getDrawable(
                R.mipmap.circle_500)).getBitmap();
    }

    // 初始化画笔paint
    private void initPaint() {
        mBitmapPaint = new Paint();
        // 防抖动
        mBitmapPaint.setDither(true);
        // 开启图像过滤
        mBitmapPaint.setFilterBitmap(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
        mCenterX = mTotalWidth / 2;
        center = w / 2;

        mSrcRect = new Rect();
        mDestRect = new Rect();

        int maskWidth = mMaskBitmap.getWidth();
        int maskHeight = mMaskBitmap.getHeight();
        mMaskSrcRect = new Rect(0, 0, maskWidth, maskHeight);
        mMaskDestRect = new Rect(10, 10, mTotalWidth - 10, mTotalHeight - 10);
    }

    /**
     * 不能在子线程里调用此方法
     *
     * @param progress 进度值
     */
    public void setProgress(int progress) {
        this.progress = progress;
    }

}