package com.sexample.emily.myapplication.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sexample.emily.myapplication.R;
import com.sexample.emily.myapplication.Util.GetFromServer;
import com.sexample.emily.myapplication.Util.HttpJson;
import com.sexample.emily.myapplication.Util.MyAsyncTask;
import com.sexample.emily.myapplication.Util.showChoosePic;
import com.sexample.emily.myapplication.base.Utils;
import com.sexample.emily.myapplication.ormlite.Bean.Login;
import com.sexample.emily.myapplication.ormlite.dao.UserDao;
import com.sexample.emily.myapplication.ormlite.db.DBHelper;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class RegistActivity extends AppCompatActivity{
    private String imagePath=null;
    private String mRegistType=null;
    protected static Uri tempUri;
    DBHelper helper = new DBHelper(this);
    private showChoosePic choosePic=new showChoosePic(this,RegistActivity.this);

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private ImageView iv_personal_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        Button btn_change = (Button) findViewById(R.id.btn_change);
        iv_personal_icon = (ImageView) findViewById(R.id.iv_personal_icon);
        btn_change.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {  choosePic.showChoosePicDialog();
                choosePic.setIv_personal_icon(iv_personal_icon);

            }
        });
    }


    /*showChoosePicDialog*/
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //调用照片 以及获得保存的照片路径
        super.onActivityResult(requestCode, resultCode, data);
        imagePath = choosePic.onActivityResult(requestCode,resultCode,data);
    }

    /*end*/

    private void attemptLogin() {

        mEmailView.setError(null);
        mPasswordView.setError(null);

        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if(isEmailValid(email)){
            mRegistType="M";
        }else if (isMobileNO(email))
        {
            mRegistType="T";
        }
        else if(!(TextUtils.isEmpty(email))&&email.length()>4){
            mRegistType="A";
        }else {
            mEmailView.setError(getString(R.string.error_field_TELOREMAIL));
            focusView = mEmailView;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.

            focusView.requestFocus();
        } else {
            Login User=new Login();
            User.RegistWithFlag(email,password,mRegistType);
            Context mContext =RegistActivity.this ;
            UserDao UDAO=new UserDao(mContext);
            UDAO.add(User);
            List<Login> Users = new ArrayList<Login>();
            Users=UDAO.queryForAll();

            for(int i = 0; i < Users.size(); i++)
            {

                mEmailView.setText(""+Users.get(i).getUAName());
               // UDAO.deleteByUUuid(Users.get(i).getUUuid());
               // Log.e("TAG", Users.get(i).getUAName());
                //System.out.println(list.get(i));
            }

            showProgress(true);
          //  mAuthTask = new UserLoginTask(email, password,mRegistType);
            MyAsyncTask asyncTask = (MyAsyncTask) new MyAsyncTask(new MyAsyncTask.AsyncResponse() {

                @Override
                public void processFinish(HttpJson output) {
                    if (output.getStatusCode()==400) {
                        Toast.makeText(getBaseContext(), "注册成功,去补充信息吧", Toast.LENGTH_LONG).show();
                       // String thisUUid = (String) output.getClassObject();
                        //tym
                        Intent intent = new Intent(RegistActivity.this, ExRegistActivity.class);
//                        intent.putExtra("email", email);
//                        intent.putExtra("thisUUid", thisUUid);
                        startActivity(intent);

                    }else{
                        Context mContext =RegistActivity.this ;
                        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);//AlertDialog有两个版本support V7.app兼容包，23支持新的6.0版如果增加了新的控件，如果想在4.0也用那个控件那么就用这个支持低版本
                        builder.setTitle("注册失败")//构建器模式，当new一个string view对象都需要构造器。设置的属性很多的时候会比较方便。比较灵活

                                .setMessage(output.getMessage())
                                .setPositiveButton("确认"+getResources().getString(R.string.Connection), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog dialog=builder.create();
                        dialog.show();
                        showProgress(false);

                    }
                }


            }) {
                @Override
                protected Object doInBackground(Object[] params) { // TODO: attempt authentication against a network service.

                    //0.请求的servlet地址的构造
                    String targetIP = getResources().getString(R.string.Connection);
                    //   String servlet = "regist/common";第一步
                    String servlet = "regist/uextend";
                    //1.装配 从testView获取值
                    HttpJson json = new HttpJson();
                    //2.封装
                    try {

                        /*
                        *
                        * chuawenjian
                        *
                        * */
                        if (imagePath != null) {
                            json.setPara("UHeadImg", imagePath);
                            File re = new File(imagePath);
                            //传 文件时一定要用ISO-8859-1"
                            String send1 = null;
                            send1 = FileUtils.readFileToString(re, "ISO-8859-1");
                            json.setClassObject(send1);
                        } else {
                            json.setPara("UHeadImg", "");
                        }

                        json.setPara("username", email);
                        json.setClassName("String[ISO-8859-1]:regist-userExtend");
                        json.setPara("password", password);
                        json.setPara("style", mRegistType);
                        json.setPara("UUuid", "c015ea85-6701-4b7c-afae-860b39f59c8d");
                        json.setPara("UBirthday", "1994/11/18");
                        json.setPara("UNackName", "小宝宝");
                        json.setPara("USex", "女");
                        //转换
                        json.constractJsonString();

                    } catch (IOException v) {
                        //e.printStackTrace();
                        Toast.makeText(getBaseContext(), "默认头像文件不存在", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegistActivity.this, RegistActivity.class);
//                        intent.putExtra("email", email);
//                        intent.putExtra("thisUUid", thisUUid);
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                        /*end*/

                    //3.转换

                    //4.发送
                    return new GetFromServer().execute(targetIP + servlet, json.getJsonString());
                }
            }.execute();

        }


        }
////////////////////////

    private boolean isEmailValid(String email) {
        if (null==email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }
    //电话号码校验
    public static boolean isMobileNO(String mobiles) {
    String telRegex = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[3578]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
    if (TextUtils.isEmpty(mobiles)) return false;
    else return mobiles.matches(telRegex);
}

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4&&password.length()<12;
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }




    public class UserLoginTask extends AsyncTask<Void, Void, HttpJson> {

        private final String mEmail;
        private final String mPassword ;
        private final String mRegistType;

        UserLoginTask(String email, String password ,String RegistType) {
            mEmail = email;
            mPassword = password;
            mRegistType=RegistType;

        }


        @Override
        protected HttpJson doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            HttpJson send = new HttpJson();
                //0.请求的servlet地址的构造
                String targetIP =getResources().getString(R.string.Connection);
                String servlet = "regist/common";
                //1.装配 从testView获取值

                //2.封装

            try {
                send.setPara("username",mEmail);
                send.setClassName("form:regist");
                send.setPara("password",mPassword);
                send.setPara("style",mRegistType);
                Thread.sleep(2000);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //3.转换
                send.constractJsonString();
                //4.发送
                return new GetFromServer().execute(targetIP+servlet,send.getJsonString());
        }

   }
    }

