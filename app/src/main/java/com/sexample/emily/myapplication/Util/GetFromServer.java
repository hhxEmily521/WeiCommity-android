package com.sexample.emily.myapplication.Util;

import android.app.Activity;
import android.widget.TextView;

import com.sexample.emily.myapplication.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Emily on 2017/4/18.
 */

public class GetFromServer {
    private  String method = "POST";
    private  HttpURLConnection client;
    private URL url;

    public HttpJson execute(String urlString,String jsonString,String method){
        HttpJson re = new HttpJson();
        try{
            url = new URL(urlString);
            client = (HttpURLConnection) url.openConnection();
            client.setDoOutput(true);
            client.setDoInput(true);
            client.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            this.method = method;
            client.setRequestMethod(this.method);
            client.connect();

            OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
            String output = jsonString;
            writer.write(output);
            writer.flush();
            writer.close();

            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;

            while((line = reader.readLine())!=null){
                result.append(line);
            }

            re = new HttpJson(result.toString());
            re.resolveJsonString();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            client.disconnect();
        }
        return re;

    }

    public HttpJson execute(String urlString, String jsonString){
        return this.execute(urlString,jsonString,"POST");
    }


}
