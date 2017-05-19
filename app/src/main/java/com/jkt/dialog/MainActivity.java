package com.jkt.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TDialog.onItemClickListener, TDialog.onDismissListener {

    private TDialog mTDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] contentArray = {"111","22","33"};
        mTDialog = new TDialog(MainActivity.this, TDialog.Style.DownSheet, contentArray,
                "自定义Dialog", "嘿嘿嘿", this);
        mTDialog.setDismissListener(this);
        mTDialog.setTitleColor(getResources().getColor(R.color.bgColor_overlay));
        mTDialog.setMsgSize(18);
        mTDialog.setContentColor(getResources().getColor(R.color.bgColor_overlay));
        mTDialog.setCancelTextColor(getResources().getColor(R.color.colorAccent));
    }

    public void onClick(View view) {
        mTDialog.show();
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
