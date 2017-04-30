package com.sexample.emily.myapplication.Util;

import android.os.AsyncTask;

/**
 * Created by Emily on 2017/4/18.
 */

public abstract class MyAsyncTask extends AsyncTask {
    public interface AsyncResponse {
        void processFinish(HttpJson output);
    }
    public AsyncResponse delegate = null;

    public MyAsyncTask(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected void onPostExecute(Object json) {
        delegate.processFinish((HttpJson) json);
    }
}
