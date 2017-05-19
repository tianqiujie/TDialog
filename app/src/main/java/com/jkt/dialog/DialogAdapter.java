package com.jkt.dialog;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 天哥哥 at 2017/5/19 14:06
 */
public class DialogAdapter extends BaseAdapter<String> {
    private onItemClickListener mListener;
    private
    @ColorInt
    int mTextColor;
    private int mTextSize;

    public DialogAdapter(Context context) {
        super(context);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_item;
    }

    @Override
    public void onBindItemHolder(BaseViewHolder holder, final int position) {
        TextView contentTV = holder.getView(R.id.dialog_item_tv);
        if (mTextColor != 0) {
            contentTV.setTextColor(mTextColor);
        }
        if (mTextSize != 0) {
            contentTV.setTextSize(mTextSize);
        }
        if (position == getItemCount() - 1)
            contentTV.setBackgroundResource(R.drawable.bg_bottom);
        else contentTV.setBackgroundResource(R.drawable.bg_item);
        String text = mList.get(position);
        contentTV.setText(TextUtils.isEmpty(text) ? "" : text);
        contentTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        });
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }
    //------------------------------------样式扩展---------------------------------

    public void setTextColor(int textColor) {
        mTextColor = textColor;
        notifyDataSetChanged();
    }

    public void setTextSize(int textSize) {
        mTextSize = textSize;
        notifyDataSetChanged();
    }
}
