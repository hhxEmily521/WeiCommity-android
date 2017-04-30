package com.sexample.emily.myapplication.Util;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.sexample.emily.myapplication.Activity.Main2Activity;
import com.sexample.emily.myapplication.R;


import android.content.Intent;


/**
 * Created by Emily on 2017/4/17.
 */


public class  SplashActivity extends Activity {


    protected void initView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_splash);
    }


    protected void setViewStatus() {
        toNextPage();
    }

    private void toNextPage() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }
}
