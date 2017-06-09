package com.jkt.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.jkt.tdialog.TDialog;

public class MainActivity extends AppCompatActivity implements TDialog.onItemClickListener, TDialog.onDismissListener {

    TDialog mTDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        //当条目为2时候中间弹出方式改为横向两个条目
        //中间弹出方式默认为点击外部区域可取消,底部弹出方式默认为不可取消
        //各种字体颜色大小和对应的布局margin都可以设置.每一个item也可以分别设置
        //最后动画可以自定义,如果想取消掉自己设置的动画,将setAnim再次调用,参数传空即可.
        String[] contentArray = {"111", "22", "33"};
        switch (view.getId()) {
            case R.id.main_btn:
                String[] array = {"取消", "确认"};
                mTDialog = new TDialog(MainActivity.this, TDialog.Style.Center, array,
                        "中间对话框", "点击外部区域不可取消", this);
                mTDialog.setCancelable(false);
                mTDialog.show();
                break;
            case R.id.main_btn1:
                mTDialog = new TDialog(MainActivity.this, TDialog.Style.DownSheet, contentArray,
                        "底部对话框", "点击外部区域不可取消", this);
                mTDialog.show();
                break;
            case R.id.main_btn2:
                mTDialog = new TDialog(MainActivity.this, TDialog.Style.Center, contentArray,
                        "中间对话框", "点击外部区域可取消", this);
                mTDialog.show();
                break;
            case R.id.main_btn3:
                mTDialog = new TDialog(MainActivity.this, TDialog.Style.DownSheet, contentArray,
                        "底部对话框", "点击外部区域可取消", this);
                mTDialog.setCancelable(true);
                mTDialog.show();
                break;
            case R.id.main_btn4:
                String[] array4 = {"取消", "确认"};
                mTDialog = new TDialog(MainActivity.this, TDialog.Style.Center, array4,
                        "自定义样式,中间弹出", "点击外部区域可取消", this);
                mTDialog.setItemTextColor(getResources().getColor(R.color.bgColor_overlay));
                mTDialog.setMsgTextColor(getResources().getColor(R.color.colorAccent));
                mTDialog.setItemTextColorAt(1, getResources().getColor(R.color.colorPrimary));
                mTDialog.setItemTextColorAt(2, getResources().getColor(R.color.colorAccent));
                mTDialog.setItemTextColorAt(10, getResources().getColor(R.color.colorAccent));
                mTDialog.show();
                break;
            case R.id.main_btn5:
                mTDialog = new TDialog(MainActivity.this, TDialog.Style.DownSheet, contentArray,
                        "自定义样式,底部弹出", "点击外部区域不可取消", this);
                mTDialog.setTitleTextColor(getResources().getColor(R.color.colorAccent));
                mTDialog.setItemTextColor(getResources().getColor(R.color.colorAccent));
                mTDialog.setMsgTextColor(getResources().getColor(R.color.bgColor_overlay));
                mTDialog.show();
                break;
            case R.id.main_btn6:
                mTDialog = new TDialog(MainActivity.this, TDialog.Style.Center, contentArray,
                        "消失监听", "点击外部区域可取消并监听到消失事件", this);
                mTDialog.setDismissListener(this);
                mTDialog.show();
                break;
            case R.id.main_btn7:
                mTDialog = new TDialog(MainActivity.this, TDialog.Style.Center, contentArray,
                        "更改边距", "通过外边距更改宽度", this);
                mTDialog.setMargin(
                        0, 0, 0, 50);
                mTDialog.show();
                break;
            case R.id.main_btn8:
                mTDialog = new TDialog(MainActivity.this, TDialog.Style.DownSheet, contentArray,
                        "更改动画", "自定义动画,进行设置", this);
                mTDialog.setInAnim(AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom1));
                mTDialog.setOutAnim(AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom1));
                mTDialog.show();
                break;
            case R.id.main_btn9:
                String[] array9 = {"取消", "确认"};
                View inflate = LayoutInflater.from(this).inflate(R.layout.alertext_from, null);
                final EditText et = (EditText) inflate.findViewById(R.id.from_et);
                mTDialog = new TDialog(this, TDialog.Style.Center, array9, "添加View", "一行代码搞定"
                        , new TDialog.onItemClickListener() {
                    @Override
                    public void onItemClick(Object object, int position) {
                            closeSoftInput(et);
                    }
                });
                mTDialog.setItemTextColorAt(0, getResources().getColor(R.color.bgColor_overlay));
                mTDialog.addView(inflate);
                mTDialog.show();
                break;

        }
    }

    @Override
    public void onItemClick(Object object, int position) {
        if (object == mTDialog) {
            Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
            if (position == 0) {
                //立即消失对话框（dismiss在动画执行完毕后消失）
                mTDialog.dismissImmediately();
            }
        }
    }

    @Override
    public void onDismissClick(Object object) {
        if (object == mTDialog) {
            Toast.makeText(this, "消失", Toast.LENGTH_SHORT).show();
        }
    }

    private void closeSoftInput(EditText editText) {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager.isActive()) {
            manager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }
}
