package com.sexample.emily.myapplication.base;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Emily on 2017/4/21.
 */

public class IconFontTextView extends android.support.v7.widget.AppCompatTextView {
    public IconFontTextView(Context context) {
        this(context,null);
    }

    public IconFontTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IconFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        Typeface iconfont = Typeface.createFromAsset(context.getAssets(), "icomoon.ttf");
        this.setTypeface(iconfont);
    }
}