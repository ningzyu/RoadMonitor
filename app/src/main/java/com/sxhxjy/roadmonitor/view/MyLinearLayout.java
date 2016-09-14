package com.sxhxjy.roadmonitor.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;


/**
 * 2016/4/28
 *
 * @author Michael Zhao
 */
public class MyLinearLayout extends LinearLayout {

    private TextView tvContent;

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        View v = LayoutInflater.from(context).inflate(R.layout.my_linearlayout, this, true);

        TextView tvTitle = (TextView) v.findViewById(R.id.titleOfMy);
        tvContent = (TextView) v.findViewById(R.id.contentOfMy);
        View divider = v.findViewById(R.id.divider);
        View ivArrow = v.findViewById(R.id.iv_arrow);
        ImageView decoration = (ImageView)v.findViewById(R.id.decoration);


        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyLinearLayout);
        String title = a.getString(R.styleable.MyLinearLayout_titleOfMyLinearLayout);
        String content = a.getString(R.styleable.MyLinearLayout_contentOfMyLinearLayout);
        boolean dividerShow = a.getBoolean(R.styleable.MyLinearLayout_dividerShowOfMyLinearLayout, true);
        boolean expanded = a.getBoolean(R.styleable.MyLinearLayout_expandedOfMyLinearLayout, true);
        Drawable drawable = a.getDrawable(R.styleable.MyLinearLayout_decoratedDrawableOfMyLinearLayout);

        tvTitle.setText(title);
        tvContent.setText(content);
        if (!dividerShow) divider.setVisibility(GONE);
        if (!expanded) ivArrow.setVisibility(GONE);
        decoration.setImageDrawable(drawable);
        decoration.setColorFilter(getResources().getColor(R.color.colorPrimary));

        a.recycle();
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setContent(CharSequence content) {
        tvContent.setText(content);
    }
}
