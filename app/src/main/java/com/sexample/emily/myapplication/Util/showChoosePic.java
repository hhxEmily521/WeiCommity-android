package com.sexample.emily.myapplication.Util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;

/**
 * PackageName com.sexample.emily.myapplication.Util
 * Created by emily on 17/5/3.
 */
public class showChoosePic  {
    protected Context thisContext;
    protected Activity thisactivity;
    protected String imagePath;
    private ImageView iv_personal_icon;
    private int requestCode;
    private int resultCode;
    private Intent data;
    Uri tempUri;

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Intent getData() {
        return data;
    }

    public void setData(Intent data) {
        this.data = data;
    }

    public void setIv_personal_icon(ImageView iv_personal_icon) {
        this.iv_personal_icon = iv_personal_icon;
    }

    public ImageView getIv_personal_icon() {
        return iv_personal_icon;
    }

    public showChoosePic(Context thisContext,Activity thisactivity) {
        this.thisContext = thisContext;
        this.thisactivity = thisactivity;

    }

    public void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(thisContext);
        builder.setTitle("设置头像");
        String[] items = new String[]{"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", (DialogInterface.OnClickListener)null);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch(which) {
                    case 0:
                        Intent openAlbumIntent = new Intent("android.intent.action.GET_CONTENT");
                        openAlbumIntent.setType("image/*");
                        thisactivity.startActivityForResult(openAlbumIntent, 0);

                        break;
                    case 1:
                        Intent openCameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                        tempUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));
                        openCameraIntent.putExtra("output", tempUri);
                        thisactivity.startActivityForResult(openCameraIntent, 1);

                }

            }
        });
        builder.create().show();
    }
    //使用这个函数时 最后请添加 super.onActivityResult
    public String onActivityResult(int requestCode, int resultCode, Intent data) {

        String tarPhoto = null;
        if (resultCode == -1) {
            switch(requestCode) {
                case 0:
                    this.startPhotoZoom(data.getData());
                    break;
                case 1:
                    this.startPhotoZoom(tempUri);
                    break;
                case 2:
                    if(data != null) {
                        tarPhoto = this.setImageToView(data);
                    }
            }
        }

        return tarPhoto;
    }

    public void startPhotoZoom(Uri uri) {
        if(uri == null) {
            Log.i("tag", "The uri is not exist.");
        }

        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        thisactivity.startActivityForResult(intent, 2);
    }

    public String setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if(extras != null) {
            Bitmap photo = (Bitmap)extras.getParcelable("data");
            photo = SavePhotoUtils.toRoundBitmap(photo,tempUri);
            this.iv_personal_icon.setImageBitmap(photo);
            imagePath = SavePhotoUtils.savePhoto(photo, String.valueOf(thisactivity.getFilesDir()+"/picSpace"),String.valueOf(System.currentTimeMillis()));
        }
        return imagePath;
    }

}
