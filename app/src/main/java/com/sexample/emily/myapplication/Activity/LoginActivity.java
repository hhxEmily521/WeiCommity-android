package com.sexample.emily.myapplication.Activity;

/**
 * Created by Emily on 2017/4/16.
 */

        import java.io.ByteArrayOutputStream;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.net.URLEncoder;

        import android.app.Activity;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AlertDialog;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.sexample.emily.myapplication.R;
        import com.sexample.emily.myapplication.Util.GetFromServer;
        import com.sexample.emily.myapplication.Util.HttpJson;
        import com.sexample.emily.myapplication.Util.MyAsyncTask;

        import org.json.JSONException;

public class LoginActivity extends Activity {
    // 声明控件对象
    private EditText et_name, et_pass;
    // 声明显示返回数据库的控件对象
    private TextView tv_result;
     Button btn_login ,btn_register;
    private HttpURLConnection client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //SMSSDK.initSDK(this, "您的appkey", "您的appsecret");//
        // 设置显示的视图
        setContentView(R.layout.activity_login);
        // 通过 findViewById(id)方法获取用户名的控件对象
        et_name = (EditText) findViewById(R.id.et_name);
        // 通过 findViewById(id)方法获取用户密码的控件对象
        et_pass = (EditText) findViewById(R.id.et_pass);

        btn_login= (Button) findViewById(R.id.btn_login);
        btn_register= (Button) findViewById(R.id.btn_register);

        // 通过 findViewById(id)方法获取显示返回数据的控件对象
        tv_result = (TextView) findViewById(R.id.tv_result);



////字体
//        Typeface iconfont = Typeface.createFromAsset(getAssets(), "icomoon.ttf");
//        TextView textview = (TextView)findViewById(R.id.text1);
//        textview.setTypeface(iconfont);
//        textview.setText("北京市发布霾黄色预警，外出携带好口罩");
//        Spannable span = new SpannableString(textview.getText());
//        span.setSpan(new AbsoluteSizeSpan(58), 11, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        span.setSpan(new ForegroundColorSpan(Color.RED), 11, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        span.setSpan(new BackgroundColorSpan(Color.YELLOW), 11, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        textview.setText(span);

//end
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            login(v);


            }

        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LoginActivity.this,RegistActivity.class);
                startActivity(intent);
                finish();

            }

        });


    }

    /**
     * 通过android:onClick="login"指定的方法 ， 要求这个方法中接受你点击控件对象的参数v
     *
     * @param v
     */

    public void login(View v) {
        // 获取点击控件的id
        
        int id = v.getId();
        // 根据id进行判断进行怎么样的处理
        switch (id) {
            // 登陆事件的处理
            case R.id.btn_login:
                // 获取用户名
                final String userName = et_name.getText().toString();
                // 获取用户密码
                final String userPass = et_pass.getText().toString();
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPass)) {
                    Toast.makeText(this, "用户名或者密码不能为空", Toast.LENGTH_LONG).show();
                } else {
                    // 开启子线程
                    new Thread() {
                        public void run() {
                            //loginByPost(userName, userPass); // 调用loginByPost方法
                            try {
                               // Toast.makeText(LoginActivity.this, "postJson", Toast.LENGTH_LONG).show();
                                HttpJson send =new HttpJson() ;
                                send.setClassName("form:login");
                                send.setPara("username",userName);
                                send.setPara("password",userPass);
                                send.setPara("style","A");// 以后判断
                                send.constractJsonString();
                                sendJsonToServer(send.getJsonString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        };
                    }.start();
                }
                break;
        }


    }

    public void sendJsonToServer(final String json ) throws JSONException{
       //final String json=formatDataAsJson();
//       // Toast.makeText(this, "postJson", Toast.LENGTH_LONG).show();
//        new AsyncTask<Void ,Void, HttpJson>(){
//
//            @Override
//            protected HttpJson doInBackground(Void... params) {
//
//            }
//
//
//        }.execute();
        MyAsyncTask asyncTask = (MyAsyncTask) new MyAsyncTask(new MyAsyncTask.AsyncResponse() {
            @Override
            public void processFinish(HttpJson output) {
                if( output.getStatusCode() != 270) {
                    if (output.getStatusCode() == 400) {
                        String thisUUid = (String) output.getClassObject();
                        //tym
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                    } else {
                        Context mContext = LoginActivity.this;
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);//AlertDialog有两个版本support V7.app兼容包，23支持新的6.0版如果增加了新的控件，如果想在4.0也用那个控件那么就用这个支持低版本
                        builder.setTitle("认证出错")//构建器模式，当new一个string view对象都需要构造器。设置的属性很多的时候会比较方便。比较灵活

                                .setMessage(output.getMessage())
                                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });


                        AlertDialog dialog = builder.create();
                        dialog.show();

                    }
                }else {
                    Toast.makeText(getBaseContext(), "网络异常，未连接到服务器", Toast.LENGTH_LONG).show();
                }
            }

        }) {
            @Override
            protected Object doInBackground(Object[] params) {
                return GetServerResponse(json);
            }
        }.execute();






//        MyAsyncTask asyncTask1 = (MyAsyncTask) new MyAsyncTask(new MyAsyncTask.AsyncResponse() {
//            //结果
//            @Override
//            public void processFinish(HttpJson output) {
//                try {
//                    //1.抛状态码异常
//                    if (output.getStatusCode() != 400)
//                        throw new Exception();
//                    //2.抛获取类异常
//                    else if (!output.getClassName().equals("String"))
//                        throw new Exception();
//
//                }catch (Exception e){
//                    //1.弹框提示
//
//                    //2.重发请求
//
//                }
//
//                //做动作 调页面
//
//
//            }
//        }) {
//            //做得东西
//            @Override
//            protected Object doInBackground(Object[] params) {
//                HttpJson send = new HttpJson();
//                //0.请求的servlet地址的构造
//                String targetIP = "";
//                String servlet = "";
//                //1.装配 从testView获取值
//
//                //2.封装
//                send.setClassName("form:regist");
//
//                //3.转换
//                send.constractJsonString();
//                //4.发送
//                return new GetFromServer().execute(targetIP+servlet,send.getJsonString());
//            }
//        }.execute();


    }

    private HttpJson GetServerResponse(String Json) {
        HttpJson re = new HttpJson();
        String urlString=getResources().getString(R.string.Connection);

        // R.string.Connection+"";
        urlString+="login";
        return new GetFromServer().execute(urlString,Json);

    }





//    public void loginByPost(String userName, String userPass) {
//
//                String retSrc = "";
//                String path = "http://www.27442.com/login";
//                JSONObject jsonObject = new JSONObject();
//                HttpPost httpPost = new HttpPost(path);
//            httpPost.setHeader("Content-Type",
//                    "application/x-www-form-urlencoded; charset=utf-8");
//
//            try {
//            //Post参数
//                    jsonObject.put("userName","zhangsan");
//                    jsonObject.put("passWord","123456");
//
//                    List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
//                    nameValuePair.add(new BasicNameValuePair("param", jsonObject.toString()));
//                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair,HTTP.UTF_8));
//            //获取HttpClient对象
//                    HttpClient httpClient = new DefaultHttpClient();
//            // 获取HttpResponse实例
//                    HttpResponse httpResp = httpClient.execute(httpPost);
//                    retSrc = EntityUtils.toString(httpResp.getEntity());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//          //  return retSrc;
//
//
//
//    }






















//
//
//
//    public void sendJsonToServer() {
//        HttpGet httpClient = new DefaultHttpClient();
//        try {
//
//            HttpPost httpPost = new HttpPost(constant.url);
//            HttpParams httpParams = new BasicHttpParams();
//            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
//            Gson gson = new Gson();
//            String str = gson.toJson(initData());
//            nameValuePair.add(new BasicNameValuePair("jsonString", URLEncoder
//                    .encode(str, "utf-8")));
//            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
//            httpPost.setParams(httpParams);
//            Toast.makeText(Main.this, "发送的数据：\n" + str.toString(),
//                    Toast.LENGTH_SHORT).show();
//            httpClient.execute(httpPost);
//            HttpResponse response = httpClient.execute(httpPost);
//            StatusLine statusLine = response.getStatusLine();
//            if (statusLine != null && statusLine.getStatusCode() == 200) {
//                HttpEntity entity = response.getEntity();
//                if (entity != null) {
//                    Toast.makeText(
//                            Main.this,
//                            "服务器处理返回结果：" + readInputStream(entity.getContent()),
//                            Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(Main.this, "没有返回相关数据", Toast.LENGTH_SHORT)
//                            .show();
//                }
//            } else {
//                Toast.makeText(Main.this, "发送失败，可能服务器忙，请稍后再试",
//                        Toast.LENGTH_SHORT).show();
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static String readInputStream(InputStream is) throws IOException {
//        if (is == null)
//            return null;
//        ByteArrayOutputStream bout = new ByteArrayOutputStream();
//        int len = 0;
//        byte[] buf = new byte[1024];
//        while ((len = is.read(buf)) != -1) {
//            bout.write(buf, 0, len);
//        }
//        is.close();
//        return URLDecoder.decode(new String(bout.toByteArray()), "utf-8");
//    }
    /**
     * POST请求操作
     *
     * @param userName
     * @param userPass
     */
    public void loginByPost(String userName, String userPass) {
        try {
            Toast.makeText(this, "用户名userName，密码userPass", Toast.LENGTH_LONG).show();
            // 请求的地址
            String spec = "http://172.16.237.200:8080/api/login";
            // 根据地址创建URL对象
            URL url = new URL(spec);
            // 根据URL对象打开链接
            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();
            // 设置请求的方式
            urlConnection.setRequestMethod("POST");
            // 设置请求的超时时间
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            // 传递的数据
            String data = "username=" + URLEncoder.encode(userName, "UTF-8")
                    + "&userpass=" + URLEncoder.encode(userPass, "UTF-8");
            // 设置请求的头
            urlConnection.setRequestProperty("Connection", "keep-alive");
            // 设置请求的头
            urlConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            // 设置请求的头
            urlConnection.setRequestProperty("Content-Length",
                    String.valueOf(data.getBytes().length));
            // 设置请求的头
            urlConnection
                    .setRequestProperty("User-Agent",
                            "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");

            urlConnection.setDoOutput(true); // 发送POST请求必须设置允许输出
            urlConnection.setDoInput(true); // 发送POST请求必须设置允许输入
            //setDoInput的默认值就是true
            //获取输出流
            OutputStream os = urlConnection.getOutputStream();
            os.write(data.getBytes());
            os.flush();
            if (urlConnection.getResponseCode() == 200) {
                // 获取响应的输入流对象
                InputStream is = urlConnection.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    baos.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                baos.close();
                // 返回字符串
                final String result = new String(baos.toByteArray());

                // 通过runOnUiThread方法进行修改主线程的控件内容
                LoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 在这里把返回的数据写在控件上 会出现什么情况尼
                        tv_result.setText(result);
                    }
                });

            } else {
                System.out.println("链接失败.........");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}