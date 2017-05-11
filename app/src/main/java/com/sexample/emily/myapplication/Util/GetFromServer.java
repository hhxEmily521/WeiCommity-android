package com.sexample.emily.myapplication.Util;

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
    private static String method = "POST";
    private static HttpURLConnection client;
    private static URL url;

    public static HttpJson execute(String urlString, String jsonString, String method) {
        HttpJson re = new HttpJson();
        try{
            url = new URL(urlString);
            client = (HttpURLConnection) url.openConnection();
            client.setDoOutput(true);
            client.setDoInput(true);
            client.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            GetFromServer.method = method;
            client.setRequestMethod(GetFromServer.method);
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

    public static HttpJson execute(String urlString, String jsonString) {
        return GetFromServer.execute(urlString, jsonString, "POST");
    }


}
