package com.jkt.dialog;

import android.app.Activity;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 天哥哥 at 2017/5/19 9:58
 */
public class TDialog {

    private TextView mCancelTV;
    private DialogAdapter mAdapter;

    public enum Style {
        Center, DownSheet
    }

    private ViewGroup mDecorView;
    private ViewGroup mRootView;
    private ViewGroup mContentView;
    private TextView mTitleTV;
    private TextView mMsgTV;
    private Activity mActivity;
    private Style mStyle;
    private List<String> mList;
    private String mTitle;
    private String mMsg;
    private onItemClickListener mItemClickListener;
    private onDismissListener mDismissListener;
    private boolean mCancelable;


    public TDialog(@NonNull Activity activity, Style style, String[] contentArray, String title, String msg, onItemClickListener onItemClickListener) {
        initParams(activity, style, Arrays.asList(contentArray), title, msg, onItemClickListener);
        initViews();
        initContentView();
    }

    private void initParams(Activity activity, Style style, List<String> contentList, String title, String msg, onItemClickListener anInterface) {
        mActivity = activity;
        mStyle = style == null ? Style.Center : style;
        mList = contentList;
        mTitle = title;
        mMsg = msg;
        mItemClickListener = anInterface;
        mCancelable = mStyle == Style.Center;

    }

    private void initViews() {
        mDecorView = (ViewGroup) mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
        mRootView = (ViewGroup) mActivity.getLayoutInflater().inflate(R.layout.dialog_root, mDecorView, false);
        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCancelable) {
                    dismiss(true);
                }
            }
        });
    }

    private void initContentView() {
        switch (mStyle) {
            case Center:
                initCenterLayout();
                initCenterView();
                break;
            case DownSheet:
                initDownSheetLayout();
                initDownSheetView();
                break;
        }
    }

    private void initCenterLayout() {
        mContentView = (ViewGroup) mRootView.findViewById(R.id.dialog_root_content_fl);
        int marginLR = mActivity.getResources().getDimensionPixelSize(R.dimen.margin_center_left_right);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        lp.gravity = Gravity.CENTER;
        lp.setMargins(marginLR, 0, marginLR, 0);
        mContentView.setLayoutParams(lp);

    }

    private void initDownSheetLayout() {
        mContentView = (ViewGroup) mRootView.findViewById(R.id.dialog_root_content_fl);
        int marginLR = mActivity.getResources().getDimensionPixelSize(R.dimen.margin_downSheet_left_right);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
        lp.gravity = Gravity.BOTTOM;
        lp.setMargins(marginLR, 0, marginLR, 0);
        mContentView.setLayoutParams(lp);
    }


    private void initCenterView() {
        mContentView = (ViewGroup) mActivity.getLayoutInflater().inflate(R.layout.dialog_content_conter, mContentView);
        initHeaderView(R.id.center_title_tv, R.id.center_msg_tv);
        initRecyclerView(R.id.center_content_rv);
    }


    private void initDownSheetView() {
        mContentView = (ViewGroup) mActivity.getLayoutInflater().inflate(R.layout.dialog_content_downsheet, mContentView);
        initHeaderView(R.id.downSheet_title_tv, R.id.downSheet_msg_tv);
        initRecyclerView(R.id.downSheet_content_rv);
    }

    private void initHeaderView(@IdRes int titleId, @IdRes int msgId) {
        mTitleTV = (TextView) mRootView.findViewById(titleId);
        mMsgTV = (TextView) mRootView.findViewById(msgId);
        mTitleTV.setText(TextUtils.isEmpty(mTitle) ? "默认标题" : mTitle);
        if (TextUtils.isEmpty(mMsg)) mMsgTV.setVisibility(View.GONE);
        else mMsgTV.setText(mMsg);
    }

    private void initRecyclerView(@IdRes int rvId) {
        RecyclerView rv = (RecyclerView) mRootView.findViewById(rvId);
        LinearLayout ll = (LinearLayout) mRootView.findViewById(R.id.center_content_ll);
        if (mStyle.equals(Style.Center) && mList != null && mList.size() == 2) {
            twoItemWork(rv, ll);
        } else {
            otherItemWork(rv, ll);
        }
    }


    private void twoItemWork(RecyclerView rv, LinearLayout ll) {
        rv.setVisibility(View.GONE);
        for (int i = 0; i < 2; i++) {
            View item = mActivity.getLayoutInflater().inflate(R.layout.dialog_item, null);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) item.getLayoutParams();
            TextView textView = (TextView) item.findViewById(R.id.dialog_item_tv);
            textView.setText(mList.get(i));
            ll.addView(item, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            if (i == 0) {
                textView.setBackgroundResource(R.drawable.bg_left);
                View divider = new View(mActivity);
                divider.setBackgroundColor(mActivity.getResources().getColor(R.color.bgColor_divier));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        (int) mActivity.getResources().getDimension(R.dimen.size_divider_line),
                        LinearLayout.LayoutParams.MATCH_PARENT);
                ll.addView(divider, params);
            }
            if (i == 1) {
                textView.setBackgroundResource(R.drawable.bg_right);
            }
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(TDialog.this, finalI);
                    }
                    dismiss(false);
                }
            });

        }
    }

    private void otherItemWork(RecyclerView rv, LinearLayout ll) {
        if (mStyle == Style.Center) {
            ll.setVisibility(View.GONE);
        }
        rv.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new DialogAdapter(mActivity);
        mAdapter.setList(mList);
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        rv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new DialogAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(TDialog.this, position);
                }
                dismiss(false);
            }
        });
        if (mStyle == Style.DownSheet) {
            mCancelTV = (TextView) mContentView.findViewById(R.id.downSheet_Cancel_tv);
            mCancelTV.setText("取消");
            mCancelTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss(true);
                }
            });
        }
    }

    public void show() {
        mDecorView.addView(mRootView);
    }

    public void dismiss(boolean cancelListener) {
        mDecorView.removeView(mRootView);
        if (mDismissListener != null&&cancelListener) {
            mDismissListener.onDismissClick(this);
        }
    }

    public void setDismissListener(onDismissListener dismissListener) {
        mDismissListener = dismissListener;
    }

    public void setCancelable(boolean cancelable) {
        mCancelable = cancelable;
    }


    public interface onItemClickListener {
        void onItemClick(Object object, int position);
    }

    public interface onDismissListener {
        void onDismissClick(Object object);
    }

    //--------------------------------样式扩展--------------------------------------------------------

    public void setTitleColor(@ColorInt int color) {
        mTitleTV.setTextColor(color);
    }
    public void setTitleSize(int dp) {
        mTitleTV.setTextSize(dp);
    }
    public void setMsgColor(@ColorInt int color) {
        mMsgTV.setTextColor(color);
    }
    public void setMsgSize(int dp) {
        mMsgTV.setTextSize(dp);
    }
    public void setContentColor(@ColorInt int color) {
        if (mAdapter!=null) {
            mAdapter.setTextColor(color);
        }
    }
    public void setContentSize(int dp) {
        if (mAdapter!=null) {
            mAdapter.setTextSize(dp);
        }
    }
    public void setCancelTextColor(@ColorInt int color) {
        if (mCancelTV!=null) {
            mCancelTV.setTextColor(color);
        }
    }

    public void setCancelTextSize(int dp) {
        if (mCancelTV != null) {
            mCancelTV.setTextSize(dp);
        }
    }

}
