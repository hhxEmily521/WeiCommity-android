package com.sexample.emily.myapplication.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sexample.emily.myapplication.R;
import com.sexample.emily.myapplication.Util.GetFromServer;
import com.sexample.emily.myapplication.Util.HttpJson;
import com.sexample.emily.myapplication.Util.MyAsyncTask;
import com.sexample.emily.myapplication.Util.showChoosePic;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;

public class ExRegistActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private int mYear;
    private int mMonth;
    private int mDay;
    private String imagePath;
    public showChoosePic choosePic = new showChoosePic(this, ExRegistActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ex_regist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ex_regist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {

                View rootView = inflater.inflate(R.layout.fragment_sub_regist_music, container, false);
                return rootView;
            }
           if(getArguments().getInt(ARG_SECTION_NUMBER) == 2)
           {
               //页面初始化
               View rootView = inflater.inflate(R.layout.fragment_sub_regist_normal, container, false);
               TextView tv_nickName = (TextView) rootView.findViewById(R.id.tv_nickname);
               TextView tv_sex = (TextView) rootView.findViewById(R.id.write_sex);
               TextView tv_birth = (TextView) rootView.findViewById(R.id.tv_brithday);
               TextView tv_signature = (TextView) rootView.findViewById(R.id.tv_signature);
               RadioButton radioButton_boy,radioButton_girl;
               RadioGroup radioGroup=(RadioGroup)rootView.findViewById(R.id.radioGroup_sex_id);
               radioButton_boy=(RadioButton)rootView.findViewById(R.id.boy_id);
               radioButton_girl=(RadioButton)rootView.findViewById(R.id.girl_id);
               Button btn_submit = (Button) rootView.findViewById(R.id.btn_submitbaseIfo);
               ImageButton ChoosePic = (ImageButton) rootView.findViewById(R.id.iv_personal_icon);
               ExRegistActivity thisActivity = (ExRegistActivity) getActivity();


               //日期监听事件
               DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                   public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                       String msg = year + "-" + monthOfYear++ + "-" + dayOfMonth;
                       tv_birth.setText(msg);
                       if (year + monthOfYear + "" != null)
                           btn_submit.setText("提交我的基本信息");
                   }
               };
               //性别单选按钮监听事件
               RadioGroup.OnCheckedChangeListener listen=new RadioGroup.OnCheckedChangeListener() {
                   @Override
                   public void onCheckedChanged(RadioGroup group, int checkedId) {
                       int id=	group.getCheckedRadioButtonId();
                       switch (group.getCheckedRadioButtonId()) {
                           case R.id.girl_id:
                               tv_sex.setText("女");
                               btn_submit.setText(R.string.btn_submitBaseIfo);
                               break;
                           case R.id.boy_id:
                               tv_sex.setText("男");
                               btn_submit.setText(R.string.btn_submitBaseIfo);
                               break;
                       }

                   }
               };
               //性别单选按钮监听事件
               radioGroup.setOnCheckedChangeListener(listen);
               //生日datepicker监听事件
               rootView.findViewById(R.id.tv_brithday).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   new DatePickerDialog(getActivity(), mDateSetListener, 1990, 6, 6).show();

               }
           });
               //相机选照片
               ChoosePic.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       btn_submit.setText(R.string.btn_submitBaseIfo);
                       thisActivity.choosePic.showChoosePicDialog();
                       thisActivity.choosePic.setIv_personal_icon(ChoosePic);

                   }
               });


               //提交数据
               rootView.findViewById(R.id.btn_submitbaseIfo).setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {


                       tv_nickName.setError(null);
                       tv_signature.setError(null);
                       //数据获取
                       boolean pass = true;

                       String nickName = tv_nickName.getText().toString(); //昵称
                       String gender = tv_sex.getText().toString();//性别
                       String brithday = tv_birth.getText().toString(); //生日
                       String sign = tv_signature.getText().toString();//个性签名
                       // boolean FormIsEmpty=false;
                       if (nickName.length() > 10) {
                           pass = false;
                           tv_nickName.setError("昵称请在10字以内");
                       }

                       if (sign.length() > 30) {
                           pass = false;
                           tv_signature.setError("个性签名请在30字以内");
                       }
                       if (pass) {
                           if (thisActivity.imagePath == null && nickName == null && gender == "保密" && brithday == null && sign == null) {
                               MyAsyncTask SubmitbseIfo = (MyAsyncTask) new MyAsyncTask(new MyAsyncTask.AsyncResponse() {
                                   @Override
                                   public void processFinish(HttpJson output) {
                                       if (output.getStatusCode() == 400) {
                                           Toast.makeText(getActivity(), "注册成功,去补充信息吧", Toast.LENGTH_LONG).show();
                                           // String thisUUid = (String) output.getClassObject();
                                           //tym
                                           Intent intent = new Intent(getActivity(), MainActivity.class);
//                        intent.putExtra("email", email);
//                        intent.putExtra("thisUUid", thisUUid);
                                           startActivity(intent);

                                       } else {
//                                              Context mContext =getContext();
//                                               AlertDialog.Builder builder=new AlertDialog.Builder(mContext);//AlertDialog有两个版本support V7.app兼容包，23支持新的6.0版如果增加了新的控件，如果想在4.0也用那个控件那么就用这个支持低版本
//                                               builder.setTitle("注册失败")//构建器模式，当new一个string view对象都需要构造器。设置的属性很多的时候会比较方便。比较灵活
//
//                                                       .setMessage(output.getMessage())
//                                                       .setPositiveButton("确认"+getResources().getString(R.string.Connection), new DialogInterface.OnClickListener() {
//                                                           @Override
//                                                           public void onClick(DialogInterface dialog, int which) {
//                                                               dialog.dismiss();
//                                                           }
//                                                       });
//                                               AlertDialog dialog=builder.create();
//                                               dialog.show();
                                           // showProgress(false);

                                       }


                                   }
                               }) {
                                   @Override
                                   protected Object doInBackground(Object... params) {
                                       //0.请求的servlet地址的构造
                                       String targetIP = getResources().getString(R.string.Connection);
                                       //   String servlet = "regist/common";第一步
                                       String servlet = "regist/uextend";
                                       //1.装配 从testView获取值
                                       HttpJson json = new HttpJson();
                                       //2.封装
                                       try {
                                           json.setPara("UHeadImg", thisActivity.imagePath);

                                           File re = new File(thisActivity.imagePath);
                                           //传 文件时一定要用ISO-8859-1"
                                           String send1 = null;
                                           send1 = FileUtils.readFileToString(re, "ISO-8859-1");
                                           json.setClassObject(send1);
                                           // json.setPara("username", nickName);
                                           json.setClassName("String[ISO-8859-1]:regist-userExtend");
                                           //json.setPara("password", password);
                                           //json.setPara("style", mRegistType);
                                           json.setPara("UUuid", "c015ea85-6701-4b7c-afae-860b39f59c8d");
                                           json.setPara("UBirthday", brithday);
                                           json.setPara("UNackName", nickName);
                                           json.setPara("USex", gender);
                                           json.setPara("sign", sign);
                                           //转换
                                           json.constractJsonString();
                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       } catch (IOException e) {
                                           e.printStackTrace();
                                       }

//
                        /*end*/

                                       //3.转换

                                       //4.发送
                                       return new GetFromServer().execute(targetIP + servlet, json.getJsonString());
                                   }
                               }.execute();


                           }


                           Intent intent = new Intent(getActivity(), MainActivity.class);
//                        intent.putExtra("email", email);
//                        intent.putExtra("thisUUid", thisUUid);
                           startActivity(intent);

                       } else {

                       }


                   }
               });



               return rootView;
           }else {
                View rootView = inflater.inflate(R.layout.fragment_sub_regist_normal, container, false);
                return rootView;
            }


        }

    }

    //调用照片 以及获得保存的照片路径
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //调用照片 以及获得保存的照片路径
        super.onActivityResult(requestCode, resultCode, data);
        imagePath = choosePic.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "圈里人基本信息";
                case 1:
                    return "我的基本信息";

            }
            return null;
        }




    }
}
