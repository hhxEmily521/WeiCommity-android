package com.sexample.emily.myapplication.Util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;

/**
 * PackageName com.learn.uitest.Utils
 * Created by uryuo on 17/5/7.
 */
public class ShowDielog {
    public static void showAlertNormal(Context mContext,String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);//AlertDialog有两个版本support V7.app兼容包，23支持新的6.0版如果增加了新的控件，如果想在4.0也用那个控件那么就用这个支持低版本
        builder.setTitle(title)//构建器模式，当new一个string view对象都需要构造器。设置的属性很多的时候会比较方便。比较灵活

                .setMessage(Message)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public static void showChooseButtomDig(Context mContext, String[] input, View animate, final DialogDoing event){
        final ActionSheetDialog dialog = new ActionSheetDialog(mContext,input,animate);
        dialog.isTitleShow(false).show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //在外部做的事情
                event.doInDiaLog(i);
                dialog.dismiss();
            }
        });


    }
}
