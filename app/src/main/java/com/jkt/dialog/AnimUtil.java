package com.jkt.dialog;

import android.support.annotation.AnimRes;

/**
 * Created by 天哥哥 at 2017/5/22 14:25
 */
public class AnimUtil {
    @AnimRes
    public static int getAnimRes(TDialog.Style style,boolean inAnimation){
        int ret=-1;
        switch (style) {
            case Center:
                ret=inAnimation?R.anim.fade_in_center:R.anim.fade_out_center;
                break;
            case DownSheet:
                ret=inAnimation?R.anim.slide_in_bottom:R.anim.slide_out_bottom;
                break;
        }
        return ret;
    }
}
