package com.sxhxjy.roadmonitor.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;

/**
 * 2016/10/8
 *
 * @author Michael Zhao
 */

public class DeleteView extends LinearLayout implements View.OnClickListener {
    private LinearLayout mContainer;
    private TextView textView;

    public DeleteView(Context context, CharSequence charSequence, LinearLayout container) {
        super(context);
        View v = LayoutInflater.from(context).inflate(R.layout.delete_view, this, true);
        findViewById(R.id.imageView).setOnClickListener(this);
        setClickable(true);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setColorFilter(getResources().getColor(R.color.colorPrimary));
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(charSequence);
        mContainer = container;
    }

    public DeleteView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public DeleteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View v) {
        mContainer.removeView(this);
    }
}
