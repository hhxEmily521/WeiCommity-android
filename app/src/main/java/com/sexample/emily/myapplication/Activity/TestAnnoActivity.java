package com.sexample.emily.myapplication.Activity;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import com.sexample.emily.myapplication.R;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Emily on 2017/5/5.
 */
@EActivity(R.layout.activity_main1)
public class TestAnnoActivity extends Activity {
    @ViewById(R.id.tv_target)
    TextView upTextView;

    @ViewById(R.id.btn_replay)
    Button scoreButton;

    @Click
    void btn_replay(){
        upTextView.setText("Hello Anno");

    }
}

