package com.jkt.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by 天哥哥 at 2017/5/19 16:51
 */
public class TFrameLayout extends FrameLayout {
    public TFrameLayout(Context context) {
        super(context);
    }

    public TFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
