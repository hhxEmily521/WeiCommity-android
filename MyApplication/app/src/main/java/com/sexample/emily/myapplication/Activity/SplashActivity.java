package com.sexample.emily.myapplication.Activity;

import android.app.ActionBar;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.sexample.emily.myapplication.R;

import com.sexample.emily.myapplication.base.BaseActivity;

/**
 * Created by emily on 17/4/14.
 *
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activiy_splash);

    }

    @Override
    protected void setViewStatus() {
        toNextPage();
    }

    private void toNextPage() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
//                PackageManager pm = getPackageManager();
//                pm.setComponentEnabledSetting(getComponentName(),
//                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
//                pm.setComponentEnabledSetting(new ComponentName(SplashActivity.this, "com.sexample.emily.myapplication.Activity.MainAliasActivity"),
//                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

            }
        }, 1500);
    }
}
