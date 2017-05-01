package com.sexample.emily.myapplication.ormlite.db;

/**
 * Created by Emily on 2017/4/26.
 */
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sexample.emily.myapplication.ormlite.Bean.Login;
import com.sexample.emily.myapplication.ormlite.Bean.Project;
import com.sexample.emily.myapplication.ormlite.Bean.UserExtend;
import com.sexample.emily.myapplication.ormlite.Bean.UserProject;
import com.sexample.emily.myapplication.ormlite.Bean.UserTFWork;

public class DBHelper extends OrmLiteSqliteOpenHelper {

    /**
     * 数据库名字
     */
    private static final String DB_NAME = "Weicommity.db";

    /**
     * 数据库版本
     */
    private static final int DB_VERSION = 1;
    private PreparedQuery<Project> projectsForUserQuery = null;
    private PreparedQuery<Login> usersForProjectQuery = null;

    /**
     * 包含两个泛型:
     * 第一个泛型表DAO操作的类
     * 第二个表示操作类的主键类型
     */
    private RuntimeExceptionDao<Login, String> simpleRuntimeUserDao = null;
    private RuntimeExceptionDao<Project, String> simpleRuntimeProjectDao = null;
    private RuntimeExceptionDao<UserProject, String> simpleRuntimeUserProjectDao = null;


    /**
     * 用来存放Dao的Map集合，之所以创建Map集合，是为了方便对数据库资源的管理
     */
    private Map<String, Dao> daos = new HashMap<String, Dao>();

/**
 * 数据库连接对象
 */
private AndroidConnectionSource connectionSource;

    /**
     * 单例对象
     */
    private static DBHelper instance;

    /**
     * <p>Title: DBHelper</p>
     * <p>Description: 构造方法</p>
     * @param context   上下文资源
     * @param databaseName  数据库名称
     * @param factory  游标工厂
     * @param databaseVersion  数据库版本
     */
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    /**
     * @Title: getHelper
     * @Description: TODO(使用单例模式获取数据库对象)
     * @param  context
     * @return DBHelper
     */
    public static synchronized DBHelper getHelper(Context context) {
        if (instance == null) {
            synchronized (DBHelper.class) {
                if (instance == null) {
                    instance = new DBHelper(context);
                }
            }
        }
        return instance;
    }
    /**
     * @Title: getDao
     * @Description: TODO(通过类名获得指定的Dao，并将取得的Dao放入Map集合)
     * @param  cls
     * @return dao
     */
    public synchronized Dao getDao(Class cls) throws SQLException {
        Dao dao = null;
        // 获得类名
        String className = cls.getSimpleName();
        // 判断类名是否为空
        if (!className.equals("") || !className.equals(null)) {
            // 如果不为空则获取Dao对象
            dao = super.getDao(cls);
            // 将Dao对象放入Map集合
            daos.put(className, dao);
        }else {
            return null;
        }
        return dao;
    }
    /* (non-Javadoc)
	 * @see com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase, com.j256.ormlite.support.ConnectionSource)
	 */
    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        // 创建操作
        try {
            // 创建用户登录表
            TableUtils.createTable(connectionSource, Login.class);
            // 创建用户扩展信息表
            TableUtils.createTable(connectionSource, UserExtend.class);
            //创建常用工种表
            TableUtils.createTable(connectionSource, UserTFWork.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /* (non-Javadoc)
	 * @see com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase,
                com.j256.ormlite.support.ConnectionSource, int, int)
	 */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion,
                          int newVersion) {
        // 更新操作
        try {
            // 更新用户登录表
            TableUtils.dropTable(connectionSource, Login.class, true);
            // 更新用户扩展信息表
            TableUtils.dropTable(connectionSource, UserExtend.class, true);
            //更新常用工种表
            TableUtils.dropTable(connectionSource, UserTFWork.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /***************************************以下为用户项目关联操作******************************************/
    /**
     * 查询所有的用户信息
     * @return
     */
    public List<Login> findAllUser(){
        RuntimeExceptionDao<Login, String> dao = getSimpleDataUserDao();
        return dao.queryForAll();
    }

    public RuntimeExceptionDao<Login, String> getSimpleDataUserDao() {
        if (simpleRuntimeUserDao == null) {
            simpleRuntimeUserDao = getRuntimeExceptionDao(Login.class);
        }
        Log.i("test", "simpleRuntimeDao ======= "+simpleRuntimeUserDao);
        return simpleRuntimeUserDao;
    }


    /**/
    /****************以下为对项目的操作*********************/

    public RuntimeExceptionDao<Project, String> getSimpleDataProjectDao() {
        if (simpleRuntimeProjectDao == null) {
            simpleRuntimeProjectDao = getRuntimeExceptionDao(Project.class);
        }
        Log.i("test", "simpleRuntimeDaodeptdept ======= "+simpleRuntimeProjectDao);
        return simpleRuntimeProjectDao;
    }

    public Project findByDeptId(String projectId){
        RuntimeExceptionDao<Project, String> dao = getSimpleDataProjectDao();
        return dao.queryForId(projectId);
    }
/*用户项目*/
public RuntimeExceptionDao<UserProject, String> getSimpleDataUserProjectDao() {
    if (simpleRuntimeUserProjectDao == null) {
        simpleRuntimeUserProjectDao = getRuntimeExceptionDao(UserProject.class);
    }
    Log.i("test", "simpleRuntimeDaodeptdept ======= "+simpleRuntimeUserProjectDao);
    return simpleRuntimeUserProjectDao;
}
    /**
            * 插入一条项目数据
     */
    public void insertProject(Project project){
        RuntimeExceptionDao<Project, String> dao = getSimpleDataProjectDao();
        //通过实体对象创建在数据库中创建一条数据，成功返回1，说明插入了一条数据
        int returnValue = dao.create(project);
        Log.i("test", "插入数据后返回值："+returnValue);
    }
    public List<Project> lookupProjectsForUser(Login user) throws SQLException {
        RuntimeExceptionDao<Project, String> dao = getSimpleDataProjectDao();
        if (projectsForUserQuery == null) {
            projectsForUserQuery = makePostsForUserQuery();
        }
        projectsForUserQuery.setArgumentHolderValue(0, user);
        return dao.query(projectsForUserQuery);
    }
    /**
     * 查询某个用户所对应的项目
     */
    private PreparedQuery<Project> makePostsForUserQuery() throws SQLException {
        RuntimeExceptionDao<UserProject, String> userProjectDao = getSimpleDataUserProjectDao();
        RuntimeExceptionDao<Project, String> projectDao = getSimpleDataProjectDao();
        //创建一个内关联查询用户项目表
        QueryBuilder<UserProject, String> userProject = userProjectDao.queryBuilder();
        //查询关联表tb_user_project时返回“project_id”如果没有该语句，即返回该表所有字段，相当于“select * from 表名”
        //拼成sql语句：select project_id from tb_user_project
        userProject.selectColumns(UserProject.PROJECT_ID_FIELD_NAME);
        //这相当于一个可变的参数，相当于SQL语句中的“？”,这个参数会在后面的操作中指明
        SelectArg userSelectArg = new SelectArg();
        //设置条件语句（where user_id=?）
        userProject.where().eq(UserProject.USER_ID_FIELD_NAME, userSelectArg);
        //创建外部查询项目表
        QueryBuilder<Project, String> postQb = projectDao.queryBuilder();
        //设置查询条件（where project_id in()）;
        postQb.where().in(Project.ID_FIELD_NAME, userProject);
        /**
         * 这里返回时完整的sql语句为
         * "SELECT * FROM `tb_project`
         *      WHERE `project_id` IN (
         *          SELECT `project_id` FROM `tb_user_project` WHERE `user_id` = ?
         *      ) "
         */
        return postQb.prepare();
    }
    public List<Login> lookupUsersForProject(Project project) throws SQLException {
        RuntimeExceptionDao<Login, String> dao = getSimpleDataUserDao();
        if (usersForProjectQuery == null) {
            usersForProjectQuery = makeUsersForProjectQuery();
        }
        usersForProjectQuery.setArgumentHolderValue(0, project);
        return dao.query(usersForProjectQuery);
    }
        /**
         * 查询某个项目的所有负责人
         */
        private PreparedQuery<Login> makeUsersForProjectQuery() throws SQLException {
            RuntimeExceptionDao<UserProject, String> userProjectDao = getSimpleDataUserProjectDao();
            RuntimeExceptionDao<Login, String> userDao = getSimpleDataUserDao();
            QueryBuilder<UserProject, String> userProject = userProjectDao.queryBuilder();
            userProject.selectColumns(UserProject.USER_ID_FIELD_NAME);
            SelectArg userSelectArg = new SelectArg();
            userProject.where().eq(UserProject.PROJECT_ID_FIELD_NAME, userSelectArg);
            QueryBuilder<Login, String> postQb = userDao.queryBuilder();
            postQb.where().in(Login.ID_FIELD_NAME, userProject);
            return postQb.prepare();
        }



    /**
             * @Title: close
             * @Description: TODO(释放资源)
             */
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            // 释放Dao资源
            dao = null;
        }
    }


}