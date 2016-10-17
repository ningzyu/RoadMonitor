package com.sxhxjy.roadmonitor.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
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
    private EditText etContent;

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        View v = LayoutInflater.from(context).inflate(R.layout.my_linearlayout, this, true);

        TextView tvTitle = (TextView) v.findViewById(R.id.titleOfMy);
        tvContent = (TextView) v.findViewById(R.id.contentOfMy);
        etContent = (EditText) v.findViewById(R.id.editText);
        View divider = v.findViewById(R.id.divider);
        ImageView ivArrow = (ImageView) v.findViewById(R.id.iv_arrow);
        ivArrow.setColorFilter(getResources().getColor(R.color.default_color));
        ImageView decoration = (ImageView)v.findViewById(R.id.decoration);


        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyLinearLayout);
        String title = a.getString(R.styleable.MyLinearLayout_titleOfMyLinearLayout);
        String content = a.getString(R.styleable.MyLinearLayout_contentOfMyLinearLayout);
        boolean dividerShow = a.getBoolean(R.styleable.MyLinearLayout_dividerShowOfMyLinearLayout, true);
        boolean expanded = a.getBoolean(R.styleable.MyLinearLayout_expandedOfMyLinearLayout, true);
        Drawable drawable = a.getDrawable(R.styleable.MyLinearLayout_decoratedDrawableOfMyLinearLayout);

        tvTitle.setText(title);
        tvContent.setText(content);
        if (!dividerShow) divider.setVisibility(INVISIBLE);
        if (!expanded) ivArrow.setVisibility(GONE);
        decoration.setImageDrawable(drawable);
        decoration.setColorFilter(getResources().getColor(R.color.colorPrimary));

        if (a.getBoolean(R.styleable.MyLinearLayout_isEditText, false)) {
            etContent.setVisibility(VISIBLE);
            ivArrow.setVisibility(GONE);
        } else {
            etContent.setVisibility(GONE);
        }

        if (a.getBoolean(R.styleable.MyLinearLayout_isPsw, false)) {
            etContent.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }

        a.recycle();
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setContent(CharSequence content) {
        tvContent.setText(content);
    }
}
