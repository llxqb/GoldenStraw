package com.bhxx.goldenstraw.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.bhxx.goldenstraw.utils.DensityUtils;

/**
 * 折扣优惠券卷布局
 */
public class CouponsView extends LinearLayout {

    private Paint mPaint;
    /**
     * 圆间距
     */
    private float gap = 8;
    /**
     * 半径
     */
    private float radius = 10;
    /**
     * 圆数量
     */
    private int circleNum;

    private float remain;
    private Context context;


    public CouponsView(Context context) {
        super(context);
        this.context = context;
    }

    public CouponsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain == 0) {
            remain = (int) (w - gap) % (2 * radius + gap);
        }
        circleNum = (int) ((w - gap) / (2 * radius + gap));
//        circleNum = (int) (w / (2 * radius));
    }


    public CouponsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float px = DensityUtils.dp2px(context,100);
        for (int i = 0; i < circleNum; i++) {
            float y = gap + radius + remain / 2 + ((gap + radius * 2) * i);
//            float y = (2 * i + 1) * radius;
            canvas.drawCircle(0, y, radius, mPaint);

            if(i==0){
                canvas.drawCircle(px, 0, radius, mPaint);
                canvas.drawCircle(px, px, radius, mPaint);
            }
        }
    }
}
