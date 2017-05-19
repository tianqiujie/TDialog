package com.jkt.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TDialog mTDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view) {
        mTDialog = new TDialog(this, TDialog.Style.Center, new String[]{"222", "333","444"}
                , "测试", "啦啦啦");
        mTDialog.show();
    }
}
