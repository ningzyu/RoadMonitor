package com.sxhxjy.roadmonitor.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.sxhxjy.roadmonitor.R;


/**
 * 2016/5/30
 *
 * @author Michael Zhao
 */
public class SuccessView extends View {
    private Paint mPaint;
    private float progressArc;
    private float progressLine2;
    private float progressLine;
    private RectF rectF;
    private boolean arcComplete;
    private boolean lineOneComplete;
    private boolean lineTwoComplete;
    private float x;
    private float y;
    private float dx;
    private float dy;

    public SuccessView(Context context) {
        super(context);
    }

    public SuccessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(context.getResources().getColor(R.color.colorPrimary));
        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setStrokeWidth(6);
        rectF = new RectF();

    }

    public SuccessView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float offset = 5;
        rectF.left = 0 + offset;
        rectF.top = 0 + offset;
        rectF.right = getWidth() - offset;
        rectF.bottom = getHeight() - offset;

        x = getWidth() / 4;
        y = getHeight() / 2;
        dx = getWidth() / 4;
        dy = getHeight() / 4;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!arcComplete) {
            if (progressArc < 100) {
                progressArc += 4;
            } else {
                arcComplete  = true;
            }
        }

        canvas.drawArc(rectF, 0, progressArc / 100f * 360, false, mPaint);

        if (arcComplete) {
            canvas.translate(-20, 0);
            if (progressLine < 100)
                progressLine += 5;
            else
                lineOneComplete = true;

            canvas.drawLine(x, y, x + progressLine / 100f * dx, y + progressLine / 100f * dy, mPaint);

        }

        if (lineOneComplete) {
            if (progressLine2 < 100)
                progressLine2 += 5;
            else
                lineTwoComplete = true;
            canvas.drawLine(x + dx, y + dy, x + dx + progressLine2 / 100f * dx * 1.5f, y + dy - progressLine2 / 100f * dy * 1.5f, mPaint);
        }


        if (!lineTwoComplete)
            postInvalidateDelayed(2);
    }
}
