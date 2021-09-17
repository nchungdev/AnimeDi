package com.chun.anime.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.WindowInsets;

import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.lang.reflect.Field;

public class SmartFitCollapsingToolbarLayout extends CollapsingToolbarLayout {

    public SmartFitCollapsingToolbarLayout(Context context) {
        this(context, null, 0);
    }

    public SmartFitCollapsingToolbarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmartFitCollapsingToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY && getFitsSystemWindows()) {
            int width = getMeasuredWidth();
            int height = getMeasuredHeight() - getTopWindowInset(); // remove extra bottom padding
            setMeasuredDimension(width, height);
        }
    }

    private int getTopWindowInset() {
        try {
            Field field = CollapsingToolbarLayout.class.getDeclaredField("lastInsets");
            field.setAccessible(true);
            WindowInsetsCompat o = (WindowInsetsCompat) field.get(this);
            return o != null ? o.getInsets(WindowInsetsCompat.Type.statusBars()).top : 0;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
