package com.sexample.emily.myapplication.ormlite.dao;

/**
 * Created by Emily on 2017/4/26.
 */

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.sexample.emily.myapplication.ormlite.Bean.Login;
import com.sexample.emily.myapplication.ormlite.db.DBHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import android.widget.TextView;

public class UserDao {
    private Context context;
    private Dao<Login, String> userDaoOpe;
    private TextView mTextView;
    private DBHelper helper;

    public UserDao(Context context) {
        this.context = context;

        try {
            this.helper = DBHelper.getHelper(context);
            this.userDaoOpe = this.helper.getDao(Login.class);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public void add(Login user) {
        try {
            this.userDaoOpe.create(user);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }
//
//    public Login get(String id) {
//        try {
//            return (Login)this.userDaoOpe.queryForId(id);
//        } catch (SQLException var3) {
//            var3.printStackTrace();
//            return null;
//        }
//    }
    /**
     * 按照id查询user
     *
     * @param id
     * @return
     */
    public Login searchByUUuid(String UUID)
    {
        try
        {
            // 查询的query 返回值是一个列表
            // 类似 select * from User where 'UUuid' = UUID;
            List<Login> users = userDaoOpe.queryBuilder().where().eq("UUuid", UUID).query();
            if (users.size() > 0)
                return users.get(0);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按照指定的id 与 username 删除一项
     *
     * @param id
     * @param username
     * @return 删除成功返回true ，失败返回false
     */
    public int deleteByUUuid(String UUID)
    {
        try
        {
            // 删除指定的信息，类似delete User where 'id' = id ;
            DeleteBuilder<Login, String> deleteBuilder = userDaoOpe.deleteBuilder();
            deleteBuilder.where().eq("UUuid", UUID);

            return deleteBuilder.delete();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * 查询所有记录
     * @return
     */
    public List<Login> queryForAll() {
        List<Login> Users = new ArrayList<Login>();
        try {
            Users = userDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Users;
    }
    /**
     * 删除全部
     */
    public void deleteAll()
    {
        try {
            userDaoOpe.delete(queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 显示所有的
     */
    public void display()
    {
        List<Login> users = queryForAll();
        for (Login user : users)
        {
            mTextView.append(user.toString());
        }
    }


}
