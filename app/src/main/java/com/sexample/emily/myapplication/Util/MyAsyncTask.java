package com.sexample.emily.myapplication.Util;

import android.os.AsyncTask;

/**
 * Created by Emily on 2017/4/18.
 */

public abstract class MyAsyncTask extends AsyncTask<Object,Void,Object>{
    public interface AsyncResponse {
        void processFinish(HttpJson output);
    }
    //interface 相当于模板类
    //interface 里面还可以成员方法
    public AsyncResponse delegate = null;

    public MyAsyncTask(AsyncResponse delegate){//HttpJson
        this.delegate = delegate;
    }

    @Override
    protected void onPostExecute(Object json) {
        delegate.processFinish((HttpJson) json);
    }

}

