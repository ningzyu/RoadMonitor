package com.sxhxjy.roadmonitor.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.sxhxjy.roadmonitor.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * 2016/9/19
 *
 * @author Michael Zhao
 */
public class LineChartView extends View {
    private static final int DELAY = 1000;
    private static final int POINTS_COUNT = 16;
    private static final int OFFSET = 60;
    private static final int OFFSET_LEGEND = 50;

    private static final float OFFSET_SCALE = 8;
    private static final float SPLIT_TO = 5;

    private static final int ALERT_VALUE = 80;
    private Random mRandom = new Random(47);
    private int xAxisLength, yAxisLength;
    private long xStart, xEnd;
    private int yStart, yEnd;
    private float firstPointX, nextPointX, firstPointY, nextPointY;

    private long BASE_TIME = System.currentTimeMillis();

    private int[] colors = {Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN};



    private Paint mPaint;
    private Path mPath = new Path();

    private PathEffect mPathEffect = new DashPathEffect(new float[] {8, 8}, 0);

    private List<MyLine> myLines = new ArrayList<>();
    private List<MyPoint> mList = new ArrayList<>(POINTS_COUNT);

    private float[] points = new float[POINTS_COUNT * 2]; // x0, y0, x1, y1 ...


    private RectF rectF = new RectF();



    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPath.setFillType(Path.FillType.WINDING);

        // fake data
        new CountDownTimer(1000000, DELAY) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (mList.size() == POINTS_COUNT)
                    mList.remove(0);
                mList.add(new MyPoint(System.currentTimeMillis(), mRandom.nextInt(100)));

                invalidate();
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        xAxisLength = getMeasuredWidth() - 2 * OFFSET;
        yAxisLength = getMeasuredHeight() - 2 * OFFSET - OFFSET_LEGEND;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(OFFSET, getMeasuredHeight() - OFFSET - OFFSET_LEGEND);
        canvas.drawColor(getResources().getColor(R.color.white));
        xEnd = Collections.max(mList, comparatorX).time;
        xStart = Collections.min(mList, comparatorX).time;
        yEnd = (int) Collections.max(mList, comparatorY).value;
//        yStart = (int) Collections.min(mList, comparatorY).value;
        yStart = 0;


        mPaint.setTextSize(20);

        // draw point and line
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        for (MyPoint myPoint : mList) {
            firstPointX = nextPointX;
            firstPointY = nextPointY;
            nextPointX = (float) (((double)(myPoint.time - xStart)) / (xEnd - xStart) * xAxisLength);
            nextPointY = - (float) (((double)(myPoint.value - yStart)) / (yEnd - yStart) * yAxisLength);

            mPaint.setColor(getResources().getColor(R.color.colorPrimary));
            mPaint.setStrokeWidth(10);

            mPaint.setColor(getResources().getColor(R.color.colorPrimary));
            if (mList.indexOf(myPoint) != 0) // do not draw line when draw first point !
                canvas.drawLine(firstPointX, firstPointY, nextPointX, nextPointY, mPaint);

            mPaint.setStrokeWidth(24);

            if (myPoint.value > ALERT_VALUE) {
                mPaint.setColor(getResources().getColor(android.R.color.holo_red_light));
            }

            canvas.drawPoint(nextPointX, nextPointY, mPaint);


            // draw x scale
            mPaint.setColor(getResources().getColor(R.color.default_color));
            mPaint.setStrokeWidth(2);
            mPaint.setTextAlign(Paint.Align.CENTER);

            canvas.drawLine(nextPointX, 0, nextPointX, - OFFSET_SCALE, mPaint);
            mPaint.setStrokeWidth(1);
            canvas.drawText((myPoint.time - BASE_TIME) / 1000 + " s", nextPointX, OFFSET_SCALE * 4, mPaint);

        }

        // draw axis
        mPaint.setColor(getResources().getColor(R.color.default_color));
        mPaint.setStrokeWidth(2);
        canvas.drawLine(0, 0, 0, - yAxisLength, mPaint);
        canvas.drawLine(0, 0, xAxisLength, 0, mPaint);


        mPaint.setTextAlign(Paint.Align.RIGHT);


        // draw y scale
        canvas.drawText((int) yStart + "", - OFFSET_SCALE, 0, mPaint);
        for (int j = 0; j < SPLIT_TO; j++) {
            float y = yStart + (yEnd - yStart) / SPLIT_TO * (j + 1);
            float yInView = y / (yEnd - yStart) * yAxisLength;
            yInView = -yInView; // reverse

            mPaint.setStrokeWidth(1);
            canvas.drawText((int) y + "", - OFFSET_SCALE, yInView, mPaint);
            mPaint.setStrokeWidth(2);
            canvas.drawLine(0, yInView, OFFSET_SCALE, yInView, mPaint);

        }

        // draw alert line
        mPaint.setColor(getResources().getColor(android.R.color.holo_red_light));
        mPaint.setPathEffect(mPathEffect);
        mPath.reset();
        float alertY = - (float) (((double)(ALERT_VALUE - yStart)) / (yEnd - yStart) * yAxisLength);
        mPath.moveTo(0, alertY);
        mPath.lineTo(xAxisLength, alertY);

        canvas.drawPath(mPath, mPaint);
        mPaint.setPathEffect(null);

        mPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText((int) ALERT_VALUE + "", xAxisLength + OFFSET_SCALE, alertY - OFFSET_SCALE, mPaint);

        // draw legend

       /* for (MyLine myLine : myLines) {
            mPaint.setColor(myLine.);
            canvas.drawRoundRect(rectF, 2, 2, mPaint);
        }*/

//canvas.drawBitmap();


    }

    public void addPoints(List<MyPoint> points, String s, int color) {
        myLines.add(new MyLine(s, points, color));
        invalidate();
    }

    public List<MyLine> getLines() {
        return myLines;
    }

    private Comparator<MyPoint> comparatorX =  new Comparator<MyPoint>() {
        @Override
        public int compare(MyPoint lhs, MyPoint rhs) {
            return (int) (lhs.time - rhs.time);
        }
    };

    private Comparator<MyPoint> comparatorY =  new Comparator<MyPoint>() {
        @Override
        public int compare(MyPoint lhs, MyPoint rhs) {
            return (int) (lhs.value - rhs.value);
        }
    };


    public static class MyPoint {
        MyPoint(long time, long value) {
            this.time = time;
            this.value = value;
        }

        long time;
        long value;

    }

    private static class MyLine {
        MyLine(String name, List<MyPoint> points, int color) {
            this.name = name;
            this.points = points;
            this.color = color;
        }

        String name;
        int color;
        List<MyPoint> points;
    }
}
