package com.osmartian.small;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import net.wequick.small.Small;

/**
 * An example launcher activity that setUp the Small bundles and launch the main plugin.
 */
public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Small.setUp(this, new Small.OnCompleteListener() {
            @Override
            public void onComplete() {
                if (Small.openUri("main", LaunchActivity.this)) {
                    finish();
                } else {
                    Toast.makeText(LaunchActivity.this, "Open failed, see log for detail!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
