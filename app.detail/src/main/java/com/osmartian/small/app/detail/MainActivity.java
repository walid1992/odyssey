package com.osmartian.small.app.detail;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import net.wequick.small.Small;

/**
 * @Author :  walid
 * @Data : 2017-03-09  22:46
 * @Describe : 详情入口activity
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Uri uri = Small.getUri(this);
        if (uri != null) {
            String params = uri.getQueryParameter("params");
            if (params != null) {
                TextView tvFrom = (TextView) findViewById(R.id.tvFrom);
                tvFrom.setText("我收到的参数是：" + params);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("Hello", "Small");
        super.onSaveInstanceState(outState);
    }
}
