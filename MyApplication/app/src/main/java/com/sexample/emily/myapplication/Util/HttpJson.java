package com.sexample.emily.myapplication.Util;

/**
 * Created by Emily on 2017/4/14.
 */
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringWriter;


/**
 * 描述：服务器封装数据传输用的类，通过这个类不同的客户端可以相互传递数据
 * 发送时：
 *      1.装配需要的变量（如果不修改默认是不发送任何类+传递正常的信息）
 *      2.调用 constractJsonString() 进行装配JsonString
 *      3.通过Servlet发送json报文
 *
 * 接收时：
 *      1.使用bool 变量来检查 resolveJsnString() 方法的结果
 *      2.无 Object需求 直接获取信息即可
 *      3.若要获取Object 先检查getClassName 看看与目标类是否符合
 *      4.符合再取调用 getClassObject 取Object，不符合请重发请求
 * Created by uryuo on 17/4/6.
 */
public class HttpJson {
    private int statusCode = 270;   //默认传递正常
    private String Message = "OK";   //默认消息是无异常
    private String className = ""; //默认没有类
    private Object classObject = null;
    private JSONObject jsonObject = new JSONObject();
    private String jsonString = ""; //json格式的串
    //封装Json
    public void constractJsonString(){



        try {
            jsonObject.put("statusCode", statusCode);
            jsonObject.put("message", Message);
            jsonObject.put("className", className);
            jsonObject.put("classObject", classObject);
        }catch(JSONException e){
            e.printStackTrace();
        }

        jsonString = jsonObject.toString();
    }


    public boolean resolveJsonString  () throws JSONException {
        boolean re = false;
        jsonObject = new JSONObject(jsonString);
        try{
            this.statusCode = jsonObject.getInt("statusCode");
            this.Message = jsonObject.getString("message");
            this.className = jsonObject.getString("className");
            this.classObject = jsonObject.get("classObject");
            re = true;
        }catch (Exception e){
            System.out.println("json 解析出错");
            e.printStackTrace();
        }finally {
            return re;
        }
    }
    public void setPara(String key ,String elem) throws JSONException {
        jsonObject.put(key,elem);
    }
    public String getPara(String key) throws JSONException {
        return jsonObject.getString(key);
    }
    public HttpJson() {
        super();
    }

    public HttpJson(int statusCode, String message) {
        super();
        this.statusCode = statusCode;
        Message = message;
    }

    public HttpJson(String jsonString) {
        super();
        this.jsonString = jsonString;
    }


    //getter & setter
    public String getJsonString(){
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className){
        this.className = className;
    }

    public Object getClassObject() {
        return classObject;
    }

    public void setClassObject(Object classObject) {
        this.classObject = classObject;
    }
}

