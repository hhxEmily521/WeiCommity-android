package com.sexample.emily.myapplication.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sexample.emily.myapplication.R;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    TextView tvTarget;
    TextView tvScore;
    TextView tvIndex;
    SeekBar sbbulsseye;
    Button btnOK;
    Button btnHelp;
    Button btnReplay;
    int randomScore;
    int finalScore;
    int  Index=1;
    Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main1);
        mContext=this;
        findView();    //按住alt+enter能出现各种提示
        randomOfScore();
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Index++;
               int currentScore= sbbulsseye.getProgress();
              int Score=100-Math.abs(currentScore-randomScore);
                finalScore+=Score;
                SetView();
                randomOfScore();
            }

        });
        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalScore=0;
                Index=1;
                SetView();
            }

        });
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(mContext);//AlertDialog有两个版本support V7.app兼容包，23支持新的6.0版如果增加了新的控件，如果想在4.0也用那个控件那么就用这个支持低版本
               builder.setTitle("Help")//构建器模式，当new一个string view对象都需要构造器。设置的属性很多的时候会比较方便。比较灵活

                       .setMessage("这是帮助")
                       .setPositiveButton(R.string.app_name+"确认", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                           }
                       });


                AlertDialog dialog=builder.create();
                dialog.show();

            }
        });

    }
    private void randomOfScore() {
        Random random=new Random();
        randomScore=random.nextInt(99)+1;
        tvTarget.setText("小样进度条拖到："+randomScore);

    }

    private void SetView() {
        randomOfScore();
        tvScore.setText("分数："+finalScore);
        sbbulsseye.setProgress(0);
        tvIndex.setText("局数："+Index);
    }
    private void findView() {
        tvTarget= (TextView) findViewById(R.id.tv_target); //按住ctrl+鼠标左键进入源码
        tvIndex= (TextView) findViewById(R.id.btn_index);//了解一个控件最好的方法是查看它的源码
        tvScore= (TextView) findViewById(R.id.btn_score);
        sbbulsseye= (SeekBar) findViewById(R.id.sb_bulsseye);
        btnHelp= (Button) findViewById(R.id.btn_help);
        btnReplay= (Button) findViewById(R.id.btn_replay);
        btnOK= (Button) findViewById(R.id.btn_ok);



    }
}
