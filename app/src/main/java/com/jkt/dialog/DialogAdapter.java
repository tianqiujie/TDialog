package com.jkt.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

/**
 * Created by 天哥哥 at 2017/5/19 14:06
 */
public class DialogAdapter extends BaseAdapter<String> {
    public DialogAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_item;
    }

    @Override
    public void onBindItemHolder(BaseViewHolder holder, int position) {
        TextView contentTV = holder.getView(R.id.dialog_item_tv);
        String text = mList.get(position);
        contentTV.setText(TextUtils.isEmpty(text) ? "" : text);
    }
}
