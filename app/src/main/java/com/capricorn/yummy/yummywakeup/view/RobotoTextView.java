package com.capricorn.yummy.yummywakeup.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.capricorn.yummy.yummywakeup.R;

/**
 * TODO: document your custom view class.
 */
public class RobotoTextView extends TextView {

    public RobotoTextView(Context context) {
        super(context);
        style(context);
    }

    public RobotoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        style(context);
    }

    public RobotoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        style(context);
    }

    private void style(Context context) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                "font/Roboto-Regular.ttf");
        setTypeface(tf);
    }
}
