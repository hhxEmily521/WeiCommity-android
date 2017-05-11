package com.sexample.emily.myapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sexample.emily.myapplication.R;
import com.sexample.emily.myapplication.Util.LoadImagesTask;
import com.sexample.emily.myapplication.ormlite.Bean.UserExtend;

import java.util.List;

/**
 * Created by Emily on 2017/5/9.
 */

//自定义适配器，继承自ArrayAdapter
public class FriendAdapter extends ArrayAdapter<UserExtend> {
    //resourceID指定ListView的布局方式
    private int resourceID;
    //重写BrowserAdapter的构造器
    public FriendAdapter(Context context, int textViewResourceID , List<UserExtend> objects){
        super(context,textViewResourceID,objects);
        resourceID = textViewResourceID;
    }
    //定义ViewHolder
    public  class ViewHolder extends RecyclerView.ViewHolder {
        //  tv_nickeName_item tv_sign_item

        public ImageView Imgv_Icon;
        public TextView tv_nickeName;
        public TextView tv_sign_item;
        public ViewHolder(final View itemView) {
            super(itemView);
            Imgv_Icon= (ImageView) itemView.findViewById(R.id.imgv_icon_item);
            tv_nickeName = (TextView) itemView.findViewById(R.id.tv_nickeName_item);
            tv_sign_item = (TextView) itemView.findViewById(R.id.tv_sign_item);
        }
    }
    //自定义item资源的解析方式
    //  convertView参数，这个参数用于将之前加载好的布局进行缓存，以便之后可以进行重用
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //获取当前friend实例
        UserExtend friend = getItem(position);
        View view;
        //convertView为空则加载布局,不为空则重用
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceID,null);
            viewHolder = new ViewHolder(view);
            //将ViewHolder存储在View中
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.tv_nickeName.setText(friend.getUNackName());//昵称
        viewHolder.tv_sign_item.setText(friend.getUSign());//个性签名
        new LoadImagesTask(viewHolder.Imgv_Icon).execute(friend.getUHeadImg());   //启动异步任务，加载网络图片

        return view;

    }

}
