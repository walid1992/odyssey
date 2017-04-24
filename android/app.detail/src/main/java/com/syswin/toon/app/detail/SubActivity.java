package com.syswin.toon.app.detail;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * @Author :  walid
 * @Data : 2017-03-09  22:46
 * @Describe :
 */
public class SubActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
