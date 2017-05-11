package com.sexample.emily.myapplication.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.sexample.emily.myapplication.Adapter.CommityAdapter;
import com.sexample.emily.myapplication.Adapter.FriendAdapter;
import com.sexample.emily.myapplication.R;
import com.sexample.emily.myapplication.Util.DialogDoing;
import com.sexample.emily.myapplication.Util.GetFromServer;
import com.sexample.emily.myapplication.Util.HttpJson;
import com.sexample.emily.myapplication.Util.MyAsyncTask;
import com.sexample.emily.myapplication.Util.ShowDielog;
import com.sexample.emily.myapplication.Util.showChoosePic;
import com.sexample.emily.myapplication.ViewModel.CommityItemModel;
import com.sexample.emily.myapplication.base.StaticVar;
import com.sexample.emily.myapplication.ormlite.Bean.Login;
import com.sexample.emily.myapplication.ormlite.Bean.UserExtend;

import org.androidannotations.annotations.ViewById;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import devlight.io.library.ntb.NavigationTabBar;

import static com.sexample.emily.myapplication.base.StaticVar.thisCid;

public class CommityMainActivity extends AppCompatActivity {
    private static String appKey = "f3fc6baa9ac4";
    // 填写从短信SDK应用后台注册得到的APPSECRET
    private static String appSecret = "7f3dedcb36d92deebcb373af921d635a";
    private int NumMessages = 10;
    boolean editable = false;//
    private NavigationTabBar navigationTabBar;
    private List<UserExtend> Friends = new ArrayList<>();
    private List<CommityItemModel> commities = new ArrayList<>();
    private String imagePath;
    public showChoosePic choosePic = new showChoosePic(this, CommityMainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commity_main);
        //短信校验初始化
        SMSSDK.initSDK(this, appKey, appSecret, true);
        //初始CommityItemModel化数据
        CommityItemModel Commity1 = new CommityItemModel();
        CommityItemModel Commity2 = new CommityItemModel();
        CommityItemModel Commity3 = new CommityItemModel();
        CommityItemModel Commity4 = new CommityItemModel();
        CommityItemModel Commity5 = new CommityItemModel();
        CommityItemModel Commity6 = new CommityItemModel();
        CommityItemModel Commity7 = new CommityItemModel();
        CommityItemModel Commity8 = new CommityItemModel();
        CommityItemModel Commity9 = new CommityItemModel();
        String url = "http://172.17.81.7:8080/fileSpace/UserSpace/c015ea85-6701-4b7c-afae-860b39f59c8d/1493816825395.png";

        Commity1.setCommityItemModel(StaticVar.thisCid, "url", "一个大傻瓜", "这个社团有点儿傻", 2);
        Commity2.setCommityItemModel("452265552", "url", "er个大傻瓜", "这个社团有点儿傻", 0);
        Commity3.setCommityItemModel("52552265552", "url", "3个大傻瓜", "这个社团有点儿傻", 2);
        Commity4.setCommityItemModel("452552265552", "url", "4个大傻瓜", "这个社团有点儿傻", 2);
        Commity5.setCommityItemModel("552265552", "url", "5个大傻瓜", "这个社团有点儿傻", 1);
        Commity6.setCommityItemModel("265552", "url", "6个大傻瓜", "这个社团有点儿傻", 2);
        Commity7.setCommityItemModel("265552", "url", "7个大傻瓜", "这个社团有点儿傻", 3);
        Commity8.setCommityItemModel("552265552", "url", "8个大傻瓜", "这个社团有点儿傻", 2);
        Commity9.setCommityItemModel("11152265552", "url", "9个大傻瓜", "这个社团有点儿傻", 6);
        commities.add(Commity1);
        commities.add(Commity2);
        commities.add(Commity3);
        commities.add(Commity4);
        commities.add(Commity5);
        commities.add(Commity6);
        commities.add(Commity7);
        commities.add(Commity8);
        commities.add(Commity9);

        //初始UserExtend化数据
        UserExtend Friend1 = new UserExtend();
        UserExtend Friend2 = new UserExtend();
        UserExtend Friend3 = new UserExtend();
        UserExtend Friend4 = new UserExtend();
        UserExtend Friend5 = new UserExtend();
        UserExtend Friend6 = new UserExtend();
        //String http = "http://img4.duitang.com/uploads/item/" + "201403/27/20140327194516_SaKBX.png";
        //  String http = " http://down1.sucaitianxia.com/psd02/psd155/psds27083.jpg";

        //  String url = " http://down1.sucaitianxia.com/psd02/psd155/psds27083.jpg";
        //  String url="http://172.17.81.7:8080/fileSpace/UserSpace/c015ea85-6701-4b7c-afae-860b39f59c8d/1493816825395.png";
        Friend1.setFriendIteam("123456789", url, "小红", "个性签名小红~");
        Friend2.setFriendIteam("123456789", url, "小442", "个性签名5555小红~");
        Friend3.setFriendIteam("123456789", url, "大、小红", "个性签名小红~");
        Friend4.setFriendIteam("123456789", url, "小gacc42", "个性签名5555小红~");
        Friend5.setFriendIteam("123456789", url, "4555红", "个性签名小红~");
        Friend6.setFriendIteam("123456789", url, "黄442", "个性签名5555小红~");
        Friends.add(Friend1);
        Friends.add(Friend2);
        Friends.add(Friend3);
        Friends.add(Friend4);
        Friends.add(Friend5);
        Friends.add(Friend6);
        initUI();

    }


    private void initUI() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                switch (position) {
                    case 1:
                        final View view = LayoutInflater.from(
                                getBaseContext()).inflate(R.layout.viewpage_list, null, false);

                        final ListView listView = (ListView) view.findViewById(R.id.rv);
                        FriendAdapter adapter = new FriendAdapter(getBaseContext(), R.layout.item_friends, Friends);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                UserExtend browser = Friends.get(position);
                                Toast.makeText(CommityMainActivity.this, browser.getUUuid().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

                        listView.setAdapter(adapter);
                        container.addView(view);
                        return view;
                    case 2:

                        final View view2 = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_mian, null, false);

                        final TextView txtPage = (TextView) view2.findViewById(R.id.tv_test_nv);
                        final Button btnTest = (Button) view2.findViewById(R.id.btn_sponsor_nv);
                        btnTest.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                NumMessages++;
                                navigationTabBar.getModels().get(position).setBadgeTitle(NumMessages + "");
                                navigationTabBar.getModels().get(position).showBadge();


                                Context mContext = CommityMainActivity.this;
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);//AlertDialog有两个版本support V7.app兼容包，23支持新的6.0版如果增加了新的控件，如果想在4.0也用那个控件那么就用这个支持低版本
                                builder.setTitle("认证出错")//构建器模式，当new一个string view对象都需要构造器。设置的属性很多的时候会比较方便。比较灵活

                                        .setMessage("kandaowolebahahah")
                                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                AlertDialog dialog = builder.create();
                                dialog.show();


                            }

                        });
                        txtPage.setText(String.format("nfaknfg", position));
                        container.addView(view2);
                        return view2;
                    case 3:
                        final View view3 = LayoutInflater.from(
                                getBaseContext()).inflate(R.layout.viewpage_list, null, false);

                        final ListView listView3 = (ListView) view3.findViewById(R.id.rv);
                        CommityAdapter adapter3 = new CommityAdapter(getBaseContext(), R.layout.item_commity, commities);
                        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                CommityItemModel commity = commities.get(position);
                                final TextView NICK = (TextView) view.findViewById(R.id.tv_nickeName_item);
                                Toast.makeText(CommityMainActivity.this, commity.getCid().toString(), Toast.LENGTH_SHORT).show();
                                StaticVar.thisCid = commity.getCid().toString();


                                CommityManagerThings_.intent(CommityMainActivity.this).extra("CID", thisCid).start();
//                                final String[] menu = {"小黑屋", "正式成员", "管理员", "副社长", "社长"};
//                                ShowDielog.showChooseButtomDig(CommityMainActivity.this, menu, NICK, new DialogDoing() {
//                                    @Override
//                                    public void doInDiaLog(int positon) {
//                                        if (positon == 0) {
//                                            commity.setCintroduce(menu[positon]);
//                                        } else
//                                            commity.setCintroduce(menu[positon]);
//                                        NICK.setText(menu[positon] + "");
//                                    }
//                                });


                            }
                        });

                        listView3.setAdapter(adapter3);
                        container.addView(view3);
                        return view3;

                    case 4:
                        //UserExtend
                        UserExtend userInf = new UserExtend();
                        Login usercode = new Login();

                        //初始化

                        final View view4 = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_user_inf, null, false);
                        ImageView img_headIcon = (ImageView) view4.findViewById(R.id.iv_personal_icon);
                        final CircularProgressButton btn_submit = (CircularProgressButton) view4.findViewById(R.id.btn_userInfupdate);
                        Button btn_enableEdit = (Button) view4.findViewById(R.id.btn_userInfeditable);
                        EditText edit_nickeName = (EditText) view4.findViewById(R.id.edit_nickeName);
                        TextView tv_gender = (TextView) view4.findViewById(R.id.tv_gender);
                        EditText edit_birthday = (EditText) view4.findViewById(R.id.edit_birthday);
                        TextView tv_phone = (TextView) view4.findViewById(R.id.edit_phone);
                        EditText edit_email = (EditText) view4.findViewById(R.id.edit_email);
                        EditText edit_sign = (EditText) view4.findViewById(R.id.edit_sign);

                        img_headIcon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (editable) {
                                    choosePic.showChoosePicDialog();
                                    choosePic.setIv_personal_icon(img_headIcon);
                                    Toast.makeText(CommityMainActivity.this, imagePath, Toast.LENGTH_SHORT).show();
                                }

                            }
                        });


                        //设置可编辑
                        btn_enableEdit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                editable = true;
                                edit_nickeName.setEnabled(true);
                                edit_nickeName.setFocusable(true);
                                edit_birthday.setEnabled(true);
                                edit_email.setEnabled(true);
                                edit_sign.setEnabled(true);

                            }
                        });
                        //手机号验证
                        tv_phone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(editable) {
                                    {
                                        //new DatePickerDialog(getActivity(),mDateSetListener,1990, 6, 6).show();
                                        //打开验证页面
                                        RegisterPage registerPage = new RegisterPage();
                                        registerPage.setRegisterCallback(new EventHandler() {
                                            public void afterEvent(int event, int result, Object data) {
                                                // 解析注册结果
                                                if (result == SMSSDK.RESULT_COMPLETE) {
                                                    @SuppressWarnings("unchecked")
                                                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                                                    String country = (String) phoneMap.get("country");
                                                    String phone = (String) phoneMap.get("phone");
                                                    tv_phone.setText(phone);
                                                    Toast.makeText(CommityMainActivity.this, "注册成功"+country+phone, Toast.LENGTH_LONG).show();

                                                    // 提交用户信息（此方法可以不调用）
                                                    //  registerUser(country, phone);
                                                }
                                            }
                                        });
                                        registerPage.show(CommityMainActivity.this);

                                    }
                                }

                            }
                        });
                        //性别
                        tv_gender.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(editable) {
                                    final String[] menu = {"保密", "女", "男"};
                                    ShowDielog.showChooseButtomDig(CommityMainActivity.this, menu, tv_gender, new DialogDoing() {
                                        @Override
                                        public void doInDiaLog(int positon) {
                                            if (positon == 0) {
                                                userInf.setUSex(menu[positon]);
                                            } else
                                                userInf.setUSex(menu[positon]);
                                            tv_gender.setText(menu[positon] + "");
                                        }
                                    });
                                }

                            }
                        });
                        btn_submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                userInf.setUHeadImg(imagePath);
                                userInf.setUNackName(edit_nickeName.getText().toString());
                                userInf.setUSex(tv_gender.getText().toString());
                                userInf.setUBirthday(edit_birthday.getText().toString());
                                userInf.setUSign(edit_sign.getText().toString());
                                usercode.setUTel(tv_phone.getText().toString());
                                usercode.setUMail(edit_email.getText().toString());
                                //传输数据
                                btn_submit.setIndeterminateProgressMode(true);
                                Toast.makeText(CommityMainActivity.this, edit_nickeName.getText().toString(), Toast.LENGTH_SHORT).show();

                            }
                        });

                        container.addView(view4);
                        return view4;
                    default:
                        final View view0 = LayoutInflater.from(
                                getBaseContext()).inflate(R.layout.item_vp, null, false);

                        final TextView txtPage0 = (TextView) view0.findViewById(R.id.txt_vp_item_page);
                        txtPage0.setText(String.format("乱七八糟一团糟，嘟嘟嘟哒哒哒", position));
                        container.addView(view0);
                        return view0;
                }

            }

        });

        final String[] colors = getResources().getStringArray(R.array.default_preview);
        navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_first),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_sixth))
                        .title("日程")
                        .badgeTitle(NumMessages + "")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_second),
                        Color.parseColor(colors[1]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("好友")
                        .badgeTitle(NumMessages + NumMessages + "with")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_third),
                        Color.parseColor(colors[2]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_seventh))
                        .title("应用")
                        .badgeTitle("state")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fourth),
                        Color.parseColor(colors[3]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("社团")
                        .badgeTitle("icon")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fifth),
                        Color.parseColor(colors[4]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("我")
                        .badgeTitle("777")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 2);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                navigationTabBar.getModels().get(position).isBadgeShowed();
            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });
        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }

    //调用照片 以及获得保存的照片路径
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //调用照片 以及获得保存的照片路径
        super.onActivityResult(requestCode, resultCode, data);
        imagePath = choosePic.onActivityResult(requestCode, resultCode, data);
    }

}
