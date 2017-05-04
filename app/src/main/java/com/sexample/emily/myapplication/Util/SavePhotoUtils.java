package com.sexample.emily.myapplication.Util;


import android.graphics.*;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.ContextCompat;

import com.sexample.emily.myapplication.Activity.RegistActivity;
import com.sexample.emily.myapplication.R;
import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * Created by Emily on 2017/4/24.
 */


public class SavePhotoUtils {
    public SavePhotoUtils() {
    }

    public static String savePhoto(Bitmap photoBitmap, String path, String photoName) {
        String localPath = null;
        if(Environment.getExternalStorageState().equals("mounted")) {
            File dir = new File(path);
            try {
            if(!dir.exists()) {
                    FileUtils.forceMkdir(dir);
            }
            //File photoFile = new File(path, photoName + ".png");
            File photoFile = new File(path,photoName+".png");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            photoBitmap.compress(CompressFormat.PNG, 100, out);
                FileUtils.writeByteArrayToFile(photoFile,out.toByteArray());
                localPath = photoFile.getPath();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }

        return localPath;
    }

    public static Bitmap toRoundBitmap(Bitmap bitmap, Uri tempUri) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left;
        float top;
        float right;
        float bottom;
        float dst_left;
        float dst_top;
        float dst_right;
        float dst_bottom;
        if(width <= height) {
            roundPx = (float)(width / 2);
            left = 0.0F;
            top = 0.0F;
            right = (float)width;
            bottom = (float)width;
            height = width;
            dst_left = 0.0F;
            dst_top = 0.0F;
            dst_right = (float)width;
            dst_bottom = (float)width;
        } else {
            roundPx = (float)(height / 2);
            float output = (float)((width - height) / 2);
            left = output;
            right = (float)width - output;
            top = 0.0F;
            bottom = (float)height;
            width = height;
            dst_left = 0.0F;
            dst_top = 0.0F;
            dst_right = (float)height;
            dst_bottom = (float)height;
        }
        Bitmap output1 = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(output1);
        int color = -12434878;
        //int color= ContextCompat.getColor(getBaseContext(), R.color.gray);
        Paint paint = new Paint();
        Rect src = new Rect((int)left, (int)top, (int)right, (int)bottom);
        Rect dst = new Rect((int)dst_left, (int)dst_top, (int)dst_right, (int)dst_bottom);
        new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(0xff424242);
        canvas.drawCircle(roundPx, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output1;
    }
}
