package com.jkt.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

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
                mTDialog.setItemTextColorAt(0,getResources().getColor(R.color.colorPrimary));
                mTDialog.setItemTextColorAt(2,getResources().getColor(R.color.colorAccent));
                mTDialog.setItemTextColorAt(10,getResources().getColor(R.color.colorAccent));
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
                        "取消监听", "点击外部区域可取消", this);
                mTDialog.setDismissListener(this);
                mTDialog.show();
                break;
            case R.id.main_btn7:
                mTDialog = new TDialog(MainActivity.this, TDialog.Style.Center, contentArray,
                        "更改边距", "通过外边距更改宽度", this);
                mTDialog.setMargin(0,0,0,50);
                mTDialog.setDismissListener(this);
                mTDialog.show();
                break;
        }
    }

    @Override
    public void onItemClick(Object object, int position) {
        if (object == mTDialog) {
            Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDismissClick(Object object) {
        if (object == mTDialog) {
            Toast.makeText(this, "消失", Toast.LENGTH_SHORT).show();
        }
    }
}
